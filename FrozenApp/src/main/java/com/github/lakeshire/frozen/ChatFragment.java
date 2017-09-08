package com.github.lakeshire.frozen;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import lakeshire.github.com.frozenframework.adapter.recyclerview.MultiItemTypeAdapter;
import lakeshire.github.com.frozenframework.fragment.BaseRecyclerViewFragment;
import lakeshire.github.com.frozenframework.fragment.IPager;

public class ChatFragment extends BaseRecyclerViewFragment<ChatMessage> implements IPager {
    private String title;

    @Override
    public void onRefresh() {
        if (mDataList.isEmpty()) {
            showLoadingLayout();
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mDataList.clear();
                initData();
                notifyAdapter(STATUES_OK, true);
            }
        }).start();
    }

    @Override
    public int getContainerLayoutId() {
        return R.layout.fra_recycler_view;
    }

    @Override
    public void loadData() {
        showLoadingLayout();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                initData();
                notifyAdapter(STATUES_NETWORK_ERROR, true);
            }
        }).start();
    }

    @Override
    public void initUi() {
        super.initUi();
    }

    @Override
    protected void onItemClicked(View view, RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    protected boolean onItemLongClicked(View view, RecyclerView.ViewHolder holder, int position) {
        return false;
    }

    @Override
    protected MultiItemTypeAdapter initAdapter() {
        return new ChatAdapter(mContext, mDataList);
    }

    @Override
    protected void loadMoreData() {

    }

    @Override
    public String getTabTitle() {
        return title;
    }

    @Override
    public void setTabTitle(String title) {
        this.title = title;
    }

    @Override
    protected boolean hasDivider() {
        return false;
    }

    private void initData() {
        ChatMessage msg;
        msg = new ChatMessage(R.drawable.xiaohei, "xiaohei", "where are you ",
                null, false);
        mDataList.add(msg);
        msg = new ChatMessage(R.drawable.renma, "renma", "where are you ",
                null, true);
        mDataList.add(msg);
        msg = new ChatMessage(R.drawable.xiaohei, "xiaohei", "where are you ",
                null, false);
        mDataList.add(msg);
        msg = new ChatMessage(R.drawable.renma, "renma", "where are you ",
                null, true);
        mDataList.add(msg);
        msg = new ChatMessage(R.drawable.xiaohei, "xiaohei", "where are you ",
                null, false);
        mDataList.add(msg);

        msg = new ChatMessage(R.drawable.xiaohei, "xiaohei", "where are you ",
                null, false);
        mDataList.add(msg);
        msg = new ChatMessage(R.drawable.renma, "renma", "where are you ",
                null, true);
        mDataList.add(msg);
        msg = new ChatMessage(R.drawable.xiaohei, "xiaohei", "where are you ",
                null, false);
        mDataList.add(msg);
        msg = new ChatMessage(R.drawable.renma, "renma", "where are you ",
                null, true);
        mDataList.add(msg);
        msg = new ChatMessage(R.drawable.xiaohei, "xiaohei", "where are you ",
                null, false);
        mDataList.add(msg);
        msg = new ChatMessage(R.drawable.xiaohei, "xiaohei", "where are you ",
                null, false);
        mDataList.add(msg);
        msg = new ChatMessage(R.drawable.renma, "renma", "where are you ",
                null, true);
        mDataList.add(msg);
        msg = new ChatMessage(R.drawable.xiaohei, "xiaohei", "where are you ",
                null, false);
        mDataList.add(msg);
        msg = new ChatMessage(R.drawable.renma, "renma", "where are you ",
                null, true);
        mDataList.add(msg);
        msg = new ChatMessage(R.drawable.xiaohei, "xiaohei", "where are you ",
                null, false);
        mDataList.add(msg);
        msg = new ChatMessage(R.drawable.xiaohei, "xiaohei", "where are you ",
                null, false);
        mDataList.add(msg);
        msg = new ChatMessage(R.drawable.renma, "renma", "where are you ",
                null, true);
        mDataList.add(msg);
        msg = new ChatMessage(R.drawable.xiaohei, "xiaohei", "where are you ",
                null, false);
        mDataList.add(msg);
        msg = new ChatMessage(R.drawable.renma, "renma", "where are you ",
                null, true);
        mDataList.add(msg);
        msg = new ChatMessage(R.drawable.xiaohei, "xiaohei", "where are you ",
                null, false);
        mDataList.add(msg);
        msg = new ChatMessage(R.drawable.xiaohei, "xiaohei", "where are you ",
                null, false);
        mDataList.add(msg);
        msg = new ChatMessage(R.drawable.renma, "renma", "where are you ",
                null, true);
        mDataList.add(msg);
        msg = new ChatMessage(R.drawable.xiaohei, "xiaohei", "where are you ",
                null, false);
        mDataList.add(msg);
        msg = new ChatMessage(R.drawable.renma, "renma", "where are you ",
                null, true);
        mDataList.add(msg);
        msg = new ChatMessage(R.drawable.xiaohei, "xiaohei", "where are you ",
                null, false);
        mDataList.add(msg);
    }
}
