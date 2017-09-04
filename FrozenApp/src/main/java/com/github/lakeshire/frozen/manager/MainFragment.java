package com.github.lakeshire.frozen.manager;

import com.github.lakeshire.frozen.R;

import lakeshire.github.com.frozenframework.fragment.BaseFragment;

/**
 * Created by liuhan on 17/9/1.
 */

public class MainFragment extends BaseFragment {
    @Override
    public int getContainerLayoutId() {
        return R.layout.fra_main;
    }

    @Override
    public void loadData() {
        super.loadData();

        showLoadingLayout();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                showNetworkErrorLayout();
            }
        }).start();
    }
}
