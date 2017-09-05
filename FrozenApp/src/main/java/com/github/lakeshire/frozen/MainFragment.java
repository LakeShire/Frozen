package com.github.lakeshire.frozen;

import java.util.ArrayList;
import java.util.List;
import lakeshire.github.com.frozenframework.fragment.HolderFragment;
import lakeshire.github.com.frozenframework.fragment.PagerFragment;

public class MainFragment extends HolderFragment {
    @Override
    protected List<PagerFragment> initFragments() {
        List<PagerFragment> fragments = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            SubFragment fragment = new SubFragment();
            fragment.setTabTitle(getTitles()[i]);
            fragments.add(fragment);
        }

        return fragments;
    }

    @Override
    protected String[] getTitles() {
        return new String[] {"音乐", "游戏", "电影"};
    }
}
