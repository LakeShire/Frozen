package com.github.lakeshire.frozen.fragment

import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.fra_scrolling.*
import lakeshire.github.com.frozenframework.R
import lakeshire.github.com.frozenframework.fragment.BaseRecyclerViewFragment

abstract class BaseSingleScrollingFragment<T> : BaseRecyclerViewFragment<T>() {

    override val containerLayoutId: Int
        get() = R.layout.fra_single_scrolling

    override fun initUi() {
        super.initUi()
        if (activity is AppCompatActivity) {
            (activity as AppCompatActivity).setSupportActionBar(tool_bar)
        }
        tool_bar.visibility = View.VISIBLE

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

//        app_bar.addOnOffsetChangedListener { _, offset ->
//            run {
//                for (f in mFragments) {
//                    f.notifyAppBarOffset(offset)
//                }
//                if (offset < -100) {
//                    tool_bar.visibility = View.VISIBLE
//                } else {
//                    tool_bar.visibility = View.INVISIBLE
//                }
//            }
//        }
    }

    override fun isTitleVisible(): Boolean {
        return false
    }
}
