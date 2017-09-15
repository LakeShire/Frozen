package com.github.lakeshire.frozen

import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fra_my.*
import lakeshire.github.com.frozenframework.fragment.BaseFragment
import java.util.*

/**
 * Created by louis.liu on 2017/9/13.
 */

class MyFragment : BaseFragment() {
    var mDataList: MutableList<String> = ArrayList()

    override val containerLayoutId: Int
        get() = R.layout.fra_my

    override fun loadData() {

    }

    override fun initUi() {
        for (i in 1..50) {
            mDataList.add("Item $i")
        }
        recycler_view!!.layoutManager = LinearLayoutManager(context)
        recycler_view.adapter = TitleAdapter(mContext, R.layout.item, mDataList)
    }
}
