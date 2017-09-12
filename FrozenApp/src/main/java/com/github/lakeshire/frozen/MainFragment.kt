package com.github.lakeshire.frozen

import android.view.View
import lakeshire.github.com.frozenframework.fragment.IPager
import lakeshire.github.com.frozenframework.fragment.common.AbsBottomBarFragment
import java.util.*

class MainFragment : AbsBottomBarFragment() {
    override fun initUi() {
        super.initUi()
        mPager.offscreenPageLimit = 3
    }

    override fun initFragments(): List<IPager> {
        val fragments = ArrayList<IPager>()

        for (i in 0..2) {
            val fragment = MultiItemFragment()
            fragment.tabTitle = titles[i]
            fragments.add(fragment)
        }

        return fragments
    }

    override val titles: Array<String>
        get() = arrayOf("首页", "分类", "我的")

    override val icons: IntArray
        get() = intArrayOf(R.drawable.ic_home, R.drawable.ic_category, R.drawable.ic_me)

    override val activeColors: IntArray
        get() = intArrayOf(R.color.colorPrimary, R.color.orange, R.color.colorAccent)

    override fun getTitle(): String {
        return "我的应用"
    }

    override fun getActionRes(): Int {
        return R.drawable.ic_delete
    }

    override fun getActionListener(): View.OnClickListener? {
        return View.OnClickListener {
            val dialog = PlayFragment()
            dialog.show(fragmentManager, "dialog")
        }
    }

    /**
     * 主Fragment必须重写这个 表示由Activity处理后退事件
     * @return
     */
    override fun onBackPressed(): Boolean {
        return false
    }
}
