package com.github.lakeshire.frozen;

import android.view.View;
import android.widget.AdapterView;

import com.github.lakeshire.frozen.manager.SubjectAdapter;

import java.util.ArrayList;
import java.util.List;

import lakeshire.github.com.frozenframework.adapter.BaseAdapter;
import lakeshire.github.com.frozenframework.fragment.BaseListFragment;
import lakeshire.github.com.frozenframework.fragment.IPager;
import lakeshire.github.com.frozenframework.manager.PopupWindowManager;
import lakeshire.github.com.frozenframework.model.MenuItem;
import lakeshire.github.com.frozenframework.util.CustomToast;
import lakeshire.github.com.frozenframework.util.RandomUtil;

/**
 * Created by louis.liu on 2017/9/6.
 */

public class SubListFragment extends BaseListFragment<String> implements IPager {
    private String tabTitle;

    @Override
    protected void initData() {
//        for (int i = 0; i < 10; i++) {
//            mDataList.add(tabTitle + RandomUtil.getRandom(1000));
//        }
        showLoadingLayout();
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
//        PopupWindowManager.pop(getActivity(), view);
    }

    @Override
    public void initUi() {
        super.initUi();

        List<MenuItem> items = new ArrayList<>();
        items.add(new MenuItem("试听", R.drawable.ic_category, null));
        items.add(new MenuItem("下载", R.drawable.ic_home, null));
        PopupWindowManager.initPopupWindow(getActivity(), items, new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        new CustomToast(getContext()).showToast("试听");
                        break;
                    case 1:
                        new CustomToast(getContext()).showToast("下载");
                        break;
                }
            }
        });
    }


    @Override
    public void onRefresh() {
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
