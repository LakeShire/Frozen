package com.github.lakeshire.frozen;

import lakeshire.github.com.frozenframework.adapter.recyclerview.MultiItemTypeAdapter;
import lakeshire.github.com.frozenframework.fragment.BaseRecyclerViewFragment;
import lakeshire.github.com.frozenframework.fragment.IPager;

/**
 * 含RecyclerView的Fragment基础类
 *
 * @author lakeshire
 */
public class RecyclerViewFragment extends BaseRecyclerViewFragment<ChatMessage> implements IPager {
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
        super.loadData();

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
