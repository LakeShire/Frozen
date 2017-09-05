package com.github.lakeshire.frozen;

import android.view.View;
import android.widget.AdapterView;

import com.github.lakeshire.frozen.manager.SubjectAdapter;

import in.srain.cube.views.ptr.PtrFrameLayout;
import lakeshire.github.com.frozenframework.adapter.BaseAdapter;
import lakeshire.github.com.frozenframework.fragment.BaseListFragment;

/**
 * Created by liuhan on 17/9/1.
 */

public class MainFragment extends BaseListFragment<String> {
//    @Override
//    public int getContainerLayoutId() {
//        return R.layout.fra_main;
//    }

    @Override
    public void loadData() {
        super.loadData();

        for (int i = 0; i < 10; i++) {
            mDataList.add("Item " + (i+1));
        }
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onRefresh(PtrFrameLayout frame) {
        refreshComplete();
    }

    @Override
    protected boolean checkCanRefresh(PtrFrameLayout frame, View content, View header) {
        return true;
    }

    @Override
    protected BaseAdapter<String> getAdapter() {
        mAdapter = new SubjectAdapter(getContext(), mDataList, R.layout.item);
        return mAdapter;
    }

    @Override
    protected void loadMoreData() {
        notifyAdapter(STATUES_OK, false);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
