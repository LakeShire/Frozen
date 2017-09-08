package com.github.lakeshire.frozen;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import lakeshire.github.com.frozenframework.adapter.recyclerview.MultiItemTypeAdapter;
import lakeshire.github.com.frozenframework.adapter.recyclerview.common.MultiInfo;
import lakeshire.github.com.frozenframework.adapter.recyclerview.common.MultiInfoAdapter;
import lakeshire.github.com.frozenframework.fragment.BaseRecyclerViewFragment;
import lakeshire.github.com.frozenframework.fragment.IPager;
import lakeshire.github.com.frozenframework.util.CustomToast;

/**
 * Created by louis.liu on 2017/9/8.
 */

public class MultiItemFragment extends BaseRecyclerViewFragment<MultiInfo> implements IPager {
    private String title;
    private int count = 0;

    @Override
    protected void onItemClicked(View view, RecyclerView.ViewHolder holder, int position) {
        new CustomToast(mContext).showToast("点击了");
    }

    @Override
    protected void addHeaderAndFooter() {
        TextView t1 = new TextView(mContext);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 128);
        t1.setText("头部区域");
        t1.setTextColor(Color.BLUE);
        t1.setGravity(Gravity.CENTER);
        t1.setLayoutParams(lp);
        addHeaderView(t1);

        TextView t2 = new TextView(mContext);
        t2.setText("尾部区域");
        t2.setTextColor(Color.BLUE);
        t2.setGravity(Gravity.CENTER);
        t2.setLayoutParams(lp);
        addFooterView(t2);
    }

    @Override
    protected boolean onItemLongClicked(View view, RecyclerView.ViewHolder holder, int position) {
        new CustomToast(mContext).showToast("长按了");
        return false;
    }

    @Override
    protected MultiItemTypeAdapter initAdapter() {
        return new MultiInfoAdapter(mContext, mDataList);
    }

    @Override
    protected void loadMoreData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                initData();
                notifyAdapter(STATUES_OK, true);
            }
        }).start();    }

    @Override
    protected void onRefresh() {
        count = 0;

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
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
    public void loadData() {
        showLoadingLayout();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                initData();
                notifyAdapter(STATUES_OK, true);
            }
        }).start();
    }

    private void initData() {
        MultiInfo info = new MultiInfo("只有文字", "这是一个只有标题和描述的列表项", 0);
        mDataList.add(info);
        info = new MultiInfo("大图", "描述", 1);
        info.images.add(R.drawable.banner_1);
        mDataList.add(info);
        info = new MultiInfo("多图", "描述", 2);
        info.images.add(R.drawable.ic_category);
        info.images.add(R.drawable.ic_home);
        info.images.add(R.drawable.ic_me);
        info.images.add(R.drawable.ic_category);
        info.images.add(R.drawable.ic_home);
        info.images.add(R.drawable.ic_me);
        mDataList.add(info);

        info = new MultiInfo("图文", "这是一个图文并茂的列表项", 3);
        info.images.add(R.drawable.renma);
        mDataList.add(info);
        info = new MultiInfo("只有文字", "这是一个只有标题和描述的列表项", 0);
        mDataList.add(info);
        info = new MultiInfo("只有文字", "这是一个只有标题和描述的列表项", 0);
        mDataList.add(info);
        info = new MultiInfo("大图", "描述", 1);
        info.images.add(R.drawable.banner_1);
        mDataList.add(info);
        info = new MultiInfo("图文", "这是一个图文并茂的列表项", 3);
        info.images.add(R.drawable.xiaohei);
        mDataList.add(info);

        count++;
        mLoadMoreWrapper.setNoMore(count >= 5);
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

    @Override
    public String getTabTitle() {
        return title;
    }

    @Override
    public void setTabTitle(String title) {
        this.title = title;
    }
}
