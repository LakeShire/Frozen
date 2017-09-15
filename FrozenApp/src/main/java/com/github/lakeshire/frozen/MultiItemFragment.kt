package com.github.lakeshire.frozen

import `in`.srain.cube.views.ptr.PtrFrameLayout
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import lakeshire.github.com.frozenframework.adapter.recyclerview.MultiItemTypeAdapter
import lakeshire.github.com.frozenframework.adapter.recyclerview.common.MultiInfo
import lakeshire.github.com.frozenframework.adapter.recyclerview.common.MultiInfoAdapter
import lakeshire.github.com.frozenframework.fragment.BaseRecyclerViewFragment
import lakeshire.github.com.frozenframework.util.CustomToast

/**
 * Created by louis.liu on 2017/9/8.
 */

class MultiItemFragment : BaseRecyclerViewFragment<MultiInfo>(), IScrollPager {
    private var canRefresh: Boolean = true

    override fun notifyAppBarOffset(offset: Int) {
        canRefresh = offset >= 0
    }

    override var tabTitle: String = ""
    var count = 0

    override fun onItemClicked(view: View, holder: RecyclerView.ViewHolder, position: Int) {
        CustomToast(context).showToast("点击了")
    }

    override fun addHeaderAndFooter() {
        val t1 = TextView(context)
        val lp = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 128)
        t1.text = "头部区域"
        t1.setTextColor(Color.BLUE)
        t1.gravity = Gravity.CENTER
        t1.layoutParams = lp
        addHeaderView(t1)

        val t2 = TextView(context)
        t2.text = "尾部区域"
        t2.setTextColor(Color.BLUE)
        t2.gravity = Gravity.CENTER
        t2.layoutParams = lp
        addFooterView(t2)
    }

    override fun onItemLongClicked(view: View, holder: RecyclerView.ViewHolder, position: Int): Boolean {
        CustomToast(context).showToast("长按了")
        return false
    }

    override fun initAdapter(): MultiItemTypeAdapter<MultiInfo> {
        return MultiInfoAdapter(context, mDataList)
    }

    override fun loadMoreData() {
        Thread(Runnable {
            try {
                Thread.sleep(2000)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }

            initData()
            notifyAdapter(BaseRecyclerViewFragment.STATUES_OK, true)
        }).start()
    }

    override fun onRefresh() {
        count = 0

        Thread(Runnable {
            try {
                Thread.sleep(2000)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }

            mDataList.clear()
            initData()
            notifyAdapter(BaseRecyclerViewFragment.STATUES_OK, true)
        }).start()
    }

    override fun loadData() {
        showLoadingLayout()
        Thread(Runnable {
            try {
                Thread.sleep(2000)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }

            initData()
            notifyAdapter(BaseRecyclerViewFragment.STATUES_OK, true)
        }).start()
    }

    private fun initData() {
        var info = MultiInfo("只有文字", "这是一个只有标题和描述的列表项", 0)
        mDataList.add(info)
        info = MultiInfo("大图", "描述", 1)
        info.images.add(R.drawable.banner_1)
        mDataList.add(info)
        info = MultiInfo("多图", "描述", 2)
        info.images.add(R.drawable.ic_me)
        info.images.add(R.drawable.ic_category)
        info.images.add(R.drawable.ic_home)
        info.images.add(R.drawable.ic_me)
        mDataList.add(info)

        info = MultiInfo("图文", "这是一个图文并茂的列表项", 3)
        info.images.add(R.drawable.renma)
        mDataList.add(info)
        info = MultiInfo("只有文字", "这是一个只有标题和描述的列表项", 0)
        mDataList.add(info)
        info = MultiInfo("只有文字", "这是一个只有标题和描述的列表项", 0)
        mDataList.add(info)
        info = MultiInfo("大图", "描述", 1)
        info.images.add(R.drawable.banner_1)
        mDataList.add(info)
        info = MultiInfo("图文", "这是一个图文并茂的列表项", 3)
        info.images.add(R.drawable.xiaohei)
        mDataList.add(info)

        count++
        mLoadMoreWrapper.setNoMore(count >= 5)
        //        info = new MultiInfo("多图", "描述", 2);
        //        info.images.add(R.drawable.ic_category);
        //        info.images.add(R.drawable.ic_home);
        //        info.images.add(R.drawable.ic_me);
        //        info.images.add(R.drawable.ic_category);
        //        info.images.add(R.drawable.ic_home);
        //        info.images.add(R.drawable.ic_me);
        //        info.images.add(R.drawable.ic_category);
        //        info.images.add(R.drawable.ic_home);
        //        info.images.add(R.drawable.ic_me);
        //        mDataList.add(info);
    }

    override fun checkCanRefresh(frame: PtrFrameLayout, content: View, header: View): Boolean {
        var parentCanRefresh = super.checkCanRefresh(frame, content, header)
        return parentCanRefresh && canRefresh
    }
}
