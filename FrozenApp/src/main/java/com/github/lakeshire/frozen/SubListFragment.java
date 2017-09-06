package com.github.lakeshire.frozen;

import android.view.View;
import android.widget.AdapterView;

import com.github.lakeshire.frozen.manager.SubjectAdapter;

import in.srain.cube.views.ptr.PtrFrameLayout;
import lakeshire.github.com.frozenframework.adapter.BaseAdapter;
import lakeshire.github.com.frozenframework.fragment.BaseListFragment;
import lakeshire.github.com.frozenframework.fragment.IPager;
import lakeshire.github.com.frozenframework.util.RandomUtil;

/**
 * Created by louis.liu on 2017/9/6.
 */

public class SubListFragment extends BaseListFragment<String> implements IPager {
    private String tabTitle;

    @Override
    protected void initData() {
        for (int i = 0; i < 10; i++) {
            mDataList.add(tabTitle + RandomUtil.getRandom(1000));
        }
    }

    @Override
    protected BaseAdapter<String> getAdapter() {
        return new SubjectAdapter(getContext(), mDataList, R.layout.item);
    }

    @Override
    protected void loadMoreData() {
        for (int i = 0; i < 10; i++) {
            mDataList.add(tabTitle + RandomUtil.getRandom(1000));
        }
        notifyAdapter(STATUES_OK, false);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        startFragment(SubFragment.class, null);
    }

    @Override
    protected void onRefresh(PtrFrameLayout frame) {
        mDataList.clear();
        for (int i = 0; i < 10; i++) {
            mDataList.add(tabTitle + RandomUtil.getRandom(1000));
        }
        notifyAdapter(STATUES_OK, true);
    }

    @Override
    public String getTabTitle() {
        return this.tabTitle;
    }

    @Override
    public void setTabTitle(String title) {
        this.tabTitle = title;
    }
}
