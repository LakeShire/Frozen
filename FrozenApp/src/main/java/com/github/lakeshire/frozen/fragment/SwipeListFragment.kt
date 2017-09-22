package com.github.lakeshire.frozen.fragment

import `in`.srain.cube.views.ptr.PtrFrameLayout
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.github.lakeshire.frozen.R
import com.github.lakeshire.frozen.adapter.TitleAdapter
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItemClickListener
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView
import lakeshire.github.com.frozenframework.adapter.recyclerview.MultiItemTypeAdapter
import lakeshire.github.com.frozenframework.fragment.BaseListFragment
import lakeshire.github.com.frozenframework.fragment.BaseRecyclerViewFragment
import lakeshire.github.com.frozenframework.fragment.IScrollPager
import lakeshire.github.com.frozenframework.util.CustomToast
import lakeshire.github.com.frozenframework.util.ScreenUtil

class SwipeListFragment : BaseRecyclerViewFragment<String>(), IScrollPager {

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

        (mRecyclerView as SwipeMenuRecyclerView).setSwipeMenuCreator(swipeMenuCreator)
        (mRecyclerView as SwipeMenuRecyclerView).setSwipeMenuItemClickListener(mMenuItemClickListener);
    }

    override fun checkCanRefresh(frame: PtrFrameLayout, content: View, header: View): Boolean {
        var parentCanRefresh = super.checkCanRefresh(frame, content, header)
        return parentCanRefresh && canRefresh
    }

    override fun onRefresh() {
        refreshComplete()
    }

    private val swipeMenuCreator = SwipeMenuCreator { swipeLeftMenu, swipeRightMenu, viewType ->
        val width = ScreenUtil.dp2px(mContext, 64F)
        val height = ViewGroup.LayoutParams.MATCH_PARENT

        val addItem = SwipeMenuItem(context)
                .setBackground(R.color.colorAccent)
                .setText("添加")
                .setTextColor(Color.WHITE)
                .setWidth(width)
                .setHeight(height)
        swipeRightMenu.addMenuItem(addItem)

        val deleteItem = SwipeMenuItem(context)
                .setBackground(R.color.colorPrimary)
                .setText("删除")
                .setTextColor(Color.WHITE)
                .setWidth(width)
                .setHeight(height)
        swipeRightMenu.addMenuItem(deleteItem)
    }

    private val mMenuItemClickListener = SwipeMenuItemClickListener { menuBridge ->
        menuBridge.closeMenu()

        val direction = menuBridge.direction
        val adapterPosition = menuBridge.adapterPosition
        val menuPosition = menuBridge.position

        when (direction) {
            SwipeMenuRecyclerView.RIGHT_DIRECTION -> {
                when (menuPosition) {
                    0 -> { CustomToast(mContext).showToast("添加") }
                    1 -> { CustomToast(mContext).showToast("删除") }
                }
            }
            SwipeMenuRecyclerView.LEFT_DIRECTION -> {

            }
        }
    }
}
