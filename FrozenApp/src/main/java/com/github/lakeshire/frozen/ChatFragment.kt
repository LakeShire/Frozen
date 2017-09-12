package com.github.lakeshire.frozen

import android.support.v7.widget.RecyclerView
import android.view.View

import lakeshire.github.com.frozenframework.adapter.recyclerview.MultiItemTypeAdapter
import lakeshire.github.com.frozenframework.fragment.BaseRecyclerViewFragment
import lakeshire.github.com.frozenframework.fragment.IPager
import org.jetbrains.anko.doAsync

class ChatFragment : BaseRecyclerViewFragment<ChatMessage>(), IPager {
    override var tabTitle: String = ""

    override fun onRefresh() {
        if (mDataList.isEmpty()) {
            showLoadingLayout()
        }
        doAsync {
            try {
                Thread.sleep(3000)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
            mDataList.clear()
            initData()
            notifyAdapter(BaseRecyclerViewFragment.STATUES_OK, true)
        }
    }

    override val containerLayoutId: Int
        get() = R.layout.fra_recycler_view

    override fun loadData() {
        showLoadingLayout()
        doAsync {
            try {
                Thread.sleep(3000)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
            initData()
            notifyAdapter(BaseRecyclerViewFragment.STATUES_NETWORK_ERROR, true)
        }
    }

    override fun onItemClicked(view: View, holder: RecyclerView.ViewHolder, position: Int) {

    }

    override fun onItemLongClicked(view: View, holder: RecyclerView.ViewHolder, position: Int): Boolean {
        return false
    }

    override fun initAdapter(): MultiItemTypeAdapter<ChatMessage> {
        return ChatAdapter(context, mDataList)
    }

    override fun loadMoreData() {

    }

    override fun hasDivider(): Boolean {
        return false
    }

    private fun initData() {
        var msg: ChatMessage = ChatMessage(R.drawable.xiaohei, "xiaohei", "where are you ",
                null, false)
        mDataList.add(msg)
        msg = ChatMessage(R.drawable.renma, "renma", "where are you ", null, true)
        mDataList.add(msg)
        msg = ChatMessage(R.drawable.xiaohei, "xiaohei", "where are you ", null, false)
        mDataList.add(msg)
        msg = ChatMessage(R.drawable.renma, "renma", "where are you ", null, true)
        mDataList.add(msg)
        msg = ChatMessage(R.drawable.xiaohei, "xiaohei", "where are you ", null, false)
        mDataList.add(msg)

        msg = ChatMessage(R.drawable.xiaohei, "xiaohei", "where are you ", null, false)
        mDataList.add(msg)
        msg = ChatMessage(R.drawable.renma, "renma", "where are you ", null, true)
        mDataList.add(msg)
        msg = ChatMessage(R.drawable.xiaohei, "xiaohei", "where are you ", null, false)
        mDataList.add(msg)
        msg = ChatMessage(R.drawable.renma, "renma", "where are you ", null, true)
        mDataList.add(msg)
        msg = ChatMessage(R.drawable.xiaohei, "xiaohei", "where are you ", null, false)
        mDataList.add(msg)
        msg = ChatMessage(R.drawable.xiaohei, "xiaohei", "where are you ", null, false)
        mDataList.add(msg)
        msg = ChatMessage(R.drawable.renma, "renma", "where are you ", null, true)
        mDataList.add(msg)
        msg = ChatMessage(R.drawable.xiaohei, "xiaohei", "where are you ", null, false)
        mDataList.add(msg)
        msg = ChatMessage(R.drawable.renma, "renma", "where are you ", null, true)
        mDataList.add(msg)
        msg = ChatMessage(R.drawable.xiaohei, "xiaohei", "where are you ", null, false)
        mDataList.add(msg)
        msg = ChatMessage(R.drawable.xiaohei, "xiaohei", "where are you ", null, false)
        mDataList.add(msg)
        msg = ChatMessage(R.drawable.renma, "renma", "where are you ", null, true)
        mDataList.add(msg)
        msg = ChatMessage(R.drawable.xiaohei, "xiaohei", "where are you ", null, false)
        mDataList.add(msg)
        msg = ChatMessage(R.drawable.renma, "renma", "where are you ", null, true)
        mDataList.add(msg)
        msg = ChatMessage(R.drawable.xiaohei, "xiaohei", "where are you ", null, false)
        mDataList.add(msg)
        msg = ChatMessage(R.drawable.xiaohei, "xiaohei", "where are you ", null, false)
        mDataList.add(msg)
        msg = ChatMessage(R.drawable.renma, "renma", "where are you ", null, true)
        mDataList.add(msg)
        msg = ChatMessage(R.drawable.xiaohei, "xiaohei", "where are you ", null, false)
        mDataList.add(msg)
        msg = ChatMessage(R.drawable.renma, "renma", "where are you ", null, true)
        mDataList.add(msg)
        msg = ChatMessage(R.drawable.xiaohei, "xiaohei", "where are you ", null, false)
        mDataList.add(msg)
    }
}
