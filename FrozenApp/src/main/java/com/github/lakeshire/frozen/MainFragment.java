package com.github.lakeshire.frozen;

import android.view.View;

import java.util.ArrayList;
import java.util.List;

import lakeshire.github.com.frozenframework.fragment.common.BottomBarFragment;
import lakeshire.github.com.frozenframework.fragment.IPager;
import lakeshire.github.com.frozenframework.util.CustomToast;

public class MainFragment extends BottomBarFragment {
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
        return new String[] {"首页", "分类", "我的"};
    }

    @Override
    protected int[] getIcons() {
        return new int[] { R.drawable.ic_home, R.drawable.ic_category, R.drawable.ic_me };
    }

    @Override
    protected int[] getActiveColors() {
        return new int[] { R.color.colorPrimary, R.color.orange, R.color.colorAccent};
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

    /**
     * 主Fragment必须重写这个
     * @return
     */
    @Override
    public boolean onBackPressed() {
        return false;
    }
}
