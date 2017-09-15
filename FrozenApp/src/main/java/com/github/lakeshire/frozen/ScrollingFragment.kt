package com.github.lakeshire.frozen

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.TabLayout
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.fra_scrolling.*
import lakeshire.github.com.frozenframework.adapter.PageFragmentAdapter
import lakeshire.github.com.frozenframework.fragment.BaseFragment
import lakeshire.github.com.frozenframework.fragment.IPager
import lakeshire.github.com.frozenframework.view.focusimage.Banner
import lakeshire.github.com.frozenframework.view.focusimage.FocusImageAdapter

class ScrollingFragment : BaseFragment() {

    override fun loadData() {

    }

    override val containerLayoutId: Int
        get() = R.layout.fra_scrolling

    var titles = arrayOf("推荐", "收藏", "热门")
    lateinit var mAdapter: PageFragmentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    var mFocusImages: MutableList<Banner> = ArrayList()

    override fun initUi() {
//        super.initUi()
        if (activity is AppCompatActivity) {
            (activity as AppCompatActivity).setSupportActionBar(tool_bar)
        }
//        tool_bar.setNavigationIcon(R.drawable.ic_back)
        tool_bar.visibility = View.INVISIBLE

        tabs.tabMode = TabLayout.MODE_FIXED
        var fragments: List<IPager> = initFragments()
        for (f in fragments) {
            tabs.addTab(tabs.newTab().setText(f.tabTitle))
        }

        mAdapter = PageFragmentAdapter(fragments, activity.supportFragmentManager)
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

//        val screenWidth = ScreenUtil.getScreenWidth(activity)
//        val height = (screenWidth / 2.5f).toInt()
//        var lp = CollapsingToolbarLayout.LayoutParams(screenWidth, height)
//        banner!!.layoutParams = lp

        mFocusImages.clear()
        val banner1 = Banner()
        banner1.res = R.drawable.banner_1
        val banner2 = Banner()
        banner2.res = R.drawable.banner_2
        mFocusImages.add(banner1)
        mFocusImages.add(banner2)

        var mFocusImageAdapter = FocusImageAdapter(activity.applicationContext,
                mFocusImages)
        banner!!.adapter = mFocusImageAdapter
    }

    private lateinit var mFragments: ArrayList<IScrollPager>

    fun initFragments(): List<IScrollPager> {
        mFragments = ArrayList<IScrollPager>()

        for (i in 0..2) {
            val fragment = MultiItemFragment()
            fragment.tabTitle = titles[i]
            mFragments.add(fragment)
        }

        return mFragments
    }

    override fun isTitleVisible(): Boolean {
        return false
    }
}
