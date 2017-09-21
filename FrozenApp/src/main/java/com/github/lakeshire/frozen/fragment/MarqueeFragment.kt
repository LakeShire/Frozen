package com.github.lakeshire.frozen.fragment

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import com.github.lakeshire.frozen.R
import com.gongwen.marqueen.SimpleMF
import com.gongwen.marqueen.SimpleMarqueeView
import lakeshire.github.com.frozenframework.adapter.recyclerview.CommonAdapter
import lakeshire.github.com.frozenframework.adapter.recyclerview.MultiItemTypeAdapter
import lakeshire.github.com.frozenframework.adapter.recyclerview.base.ViewHolder
import lakeshire.github.com.frozenframework.fragment.BaseRecyclerViewFragment

class MarqueeFragment : BaseRecyclerViewFragment<List<String>>() {
//    override fun notifyAppBarOffset(offset: Int) {
//    }
//
//    override var tabTitle: String = ""

    override fun initAdapter(): MultiItemTypeAdapter<List<String>> {
        return MarqueeAdapter(mContext, R.layout.item_marquee, mDataList)
    }

    override fun onItemClicked(view: View, holder: RecyclerView.ViewHolder, position: Int) {

    }

    override fun onItemLongClicked(view: View, holder: RecyclerView.ViewHolder, position: Int): Boolean {
        return false
    }

    override fun loadMoreData() {

    }

    override fun onRefresh() {

    }

    override fun loadData() {
        for (i in 1..10) {
            var strings = ArrayList<String>()
            strings.add("离离原上草，一岁一枯荣")
            strings.add("野火烧不尽，春风吹又生")
            strings.add("远芳侵古道，晴翠接荒城")
            strings.add("又送王孙去，萋萋满别情")
            mDataList.add(strings)
        }
        notifyAdapter(STATUES_OK, true)
    }

    internal inner class MarqueeAdapter(context: Context, layoutId: Int, data: List<List<String>>) : CommonAdapter<List<String>>(context, layoutId, data) {

        override fun convert(holder: ViewHolder, strings: List<String>, position: Int) {
            val marqueeView = holder.getView<SimpleMarqueeView>(R.id.smv)
            val marqueeFactory = SimpleMF<String>(mContext)
            marqueeFactory.setData(strings)
            marqueeView.setMarqueeFactory(marqueeFactory)
            marqueeView.startFlipping()
        }
    }

    override fun isTitleVisible(): Boolean {
        return true
    }

    override fun getTitle(): String {
        return "跑马灯"
    }
}
