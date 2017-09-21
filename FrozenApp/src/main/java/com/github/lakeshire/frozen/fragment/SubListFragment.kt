package com.github.lakeshire.frozen.fragment

import `in`.srain.cube.views.ptr.PtrFrameLayout
import android.support.v7.widget.RecyclerView
import android.view.View
import com.github.lakeshire.frozen.R
import com.github.lakeshire.frozen.adapter.TitleAdapter
import lakeshire.github.com.frozenframework.adapter.recyclerview.MultiItemTypeAdapter
import lakeshire.github.com.frozenframework.fragment.BaseListFragment
import lakeshire.github.com.frozenframework.fragment.BaseRecyclerViewFragment
import lakeshire.github.com.frozenframework.fragment.IScrollPager
import lakeshire.github.com.frozenframework.manager.PopupWindowManager
import lakeshire.github.com.frozenframework.model.MenuItem
import lakeshire.github.com.frozenframework.util.CustomToast
import java.util.*

class SubListFragment() : BaseRecyclerViewFragment<String>(), IScrollPager {

    private var canRefresh: Boolean = true

    override fun notifyAppBarOffset(offset: Int) {
        canRefresh = offset >= 0
    }

    override fun loadData() {
        for (i in 1..50) {
            mDataList.add(tabTitle + i)
        }
        notifyAdapter(STATUES_OK, true)
    }

    override fun onItemClicked(view: View, holder: RecyclerView.ViewHolder, position: Int) {
    }

    override fun onItemLongClicked(view: View, holder: RecyclerView.ViewHolder, position: Int): Boolean {
        return true
    }

    override fun initAdapter(): MultiItemTypeAdapter<String> {
        return TitleAdapter(mContext, R.layout.item, mDataList)
    }

    override var tabTitle: String = ""

    override fun loadMoreData() {
        for (i in 0..50) {
            mDataList.add(tabTitle!! + i)
        }
        notifyAdapter(BaseListFragment.STATUES_OK, false)
    }

    override fun initUi() {
        super.initUi()

        val items = ArrayList<MenuItem>()
        items.add(MenuItem("试听", R.drawable.ic_category, null))
        items.add(MenuItem("下载", R.drawable.ic_home, null))
        PopupWindowManager.initPopupWindow(activity, items) { parent, view, position, id ->
            when (position) {
                0 -> CustomToast(context).showToast("试听")
                1 -> CustomToast(context).showToast("下载")
            }
        }
    }

    override fun checkCanRefresh(frame: PtrFrameLayout, content: View, header: View): Boolean {
        var parentCanRefresh = super.checkCanRefresh(frame, content, header)
        return parentCanRefresh && canRefresh
    }

    override fun onRefresh() {
        refreshComplete()
//        mDataList.clear()
//        for (i in 0..9) {
//            mDataList.add(tabTitle!! + RandomUtil.getRandom(1000))
//        }
//        notifyAdapter(BaseListFragment.STATUES_OK, true)
    }
}
