package com.github.lakeshire.frozen.fragment

import android.support.design.widget.Snackbar
import android.support.design.widget.TabLayout
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.fra_scrolling.*
import lakeshire.github.com.frozenframework.R
import lakeshire.github.com.frozenframework.adapter.PageFragmentAdapter
import lakeshire.github.com.frozenframework.fragment.BaseFragment
import lakeshire.github.com.frozenframework.fragment.IPager
import lakeshire.github.com.frozenframework.fragment.IScrollPager

abstract class BaseScrollingFragment : BaseFragment() {

    override val containerLayoutId: Int
        get() = R.layout.fra_scrolling

    lateinit var mAdapter: PageFragmentAdapter

    override fun initUi() {
        if (activity is AppCompatActivity) {
            (activity as AppCompatActivity).setSupportActionBar(tool_bar)
        }
        tool_bar.visibility = View.INVISIBLE

        tabs.tabMode = TabLayout.MODE_FIXED
        mFragments = initFragments()
        for (f in mFragments) {
            tabs.addTab(tabs.newTab().setText(f.tabTitle))
        }

        mAdapter = PageFragmentAdapter(mFragments as List<IPager>?, activity.supportFragmentManager)
        pager.adapter = mAdapter
        tabs.setupWithViewPager(pager)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        app_bar.addOnOffsetChangedListener { _, offset ->
            run {
                for (f in mFragments) {
                    f.notifyAppBarOffset(offset)
                }
                if (offset < -100) {
                    tool_bar.visibility = View.VISIBLE
                } else {
                    tool_bar.visibility = View.INVISIBLE
                }
            }
        }
    }

    abstract fun initFragments(): MutableList<IScrollPager>

    var mFragments: MutableList<IScrollPager> = ArrayList()

    override fun isTitleVisible(): Boolean {
        return false
    }
}
