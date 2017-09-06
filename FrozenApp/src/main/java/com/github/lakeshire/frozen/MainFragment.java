package com.github.lakeshire.frozen;

import android.view.View;

import java.util.ArrayList;
import java.util.List;

import lakeshire.github.com.frozenframework.fragment.HolderFragment;
import lakeshire.github.com.frozenframework.fragment.IPager;
import lakeshire.github.com.frozenframework.util.CustomToast;

public class MainFragment extends HolderFragment {
    @Override
    protected List<IPager> initFragments() {
        List<IPager> fragments = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            SubListFragment fragment = new SubListFragment();
            fragment.setTabTitle(getTitles()[i]);
            fragments.add(fragment);
        }

        return fragments;
    }

    @Override
    protected String[] getTitles() {
        return new String[] {"音乐", "游戏", "电影"};
    }

    @Override
    protected String getTitle() {
        return "我的应用";
    }

    @Override
    protected int getActionRes() {
        return R.drawable.ic_delete;
    }

    @Override
    protected View.OnClickListener getActionListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CustomToast(getContext()).showToast("删除");
            }
        };
    }
}
