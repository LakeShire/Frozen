package com.github.lakeshire.frozen.fragment

import `in`.srain.cube.views.ptr.PtrFrameLayout
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import com.github.lakeshire.frozen.R
import com.github.lakeshire.frozen.model.Entry
import lakeshire.github.com.frozenframework.adapter.recyclerview.CommonAdapter
import lakeshire.github.com.frozenframework.adapter.recyclerview.MultiItemTypeAdapter
import lakeshire.github.com.frozenframework.adapter.recyclerview.base.ViewHolder
import org.jetbrains.anko.imageResource

class MainScrollingFragment : BaseSingleScrollingFragment<Entry>() {

    override fun onRefresh() {

    }

    override fun onItemClicked(view: View, holder: RecyclerView.ViewHolder, position: Int) {
        val clazz = mAdapter.datas[position].clazz
        startFragment(clazz)
    }

    override fun onItemLongClicked(view: View, holder: RecyclerView.ViewHolder, position: Int): Boolean {
        return false
    }

    override fun loadData() {
        mDataList.add(Entry("iOS风格对话框", IOSDialogFragment::class.java))
        mDataList.add(Entry("侧滑列表", SwipeListFragment::class.java))
        for (i in 1..20) {
            mDataList.add(Entry("跑马灯", MarqueeFragment::class.java))
        }
        notifyAdapter(STATUES_OK, true)
    }

    override fun initAdapter(): MultiItemTypeAdapter<Entry> {
        return EntryAdapter(mContext, R.layout.item, mDataList)
    }

    override fun loadMoreData() {

    }

    override fun checkCanRefresh(frame: PtrFrameLayout, content: View, header: View): Boolean {
        return false
    }

    override fun initUi() {
        super.initUi()

        var ivHeader = mContainerView.findViewById<ImageView>(R.id.iv_header)
        ivHeader.imageResource = R.drawable.banner_1
    }

    /**
     * 主Fragment必须重写这个 表示由Activity处理后退事件
     * @return
     */
    override fun onBackPressed(): Boolean {
        return false
    }

    class EntryAdapter(context: Context, layoutId: Int, data: List<Entry>) : CommonAdapter<Entry>(context, layoutId, data) {

        override fun convert(holder: ViewHolder, entry: Entry, position: Int) {
            holder.setText(com.github.lakeshire.frozen.R.id.tv_title, entry.description)
        }
    }
}
