package com.github.lakeshire.frozen;


import android.view.View;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrFrameLayout;
import lakeshire.github.com.frozenframework.fragment.BasePullFragment;
import lakeshire.github.com.frozenframework.util.ScreenUtil;
import lakeshire.github.com.frozenframework.view.focusimage.Banner;
import lakeshire.github.com.frozenframework.view.focusimage.FocusImageAdapter;
import lakeshire.github.com.frozenframework.view.focusimage.FocusImageView;

public class SubFragment extends BasePullFragment {
    private FocusImageAdapter mFocusImageAdapter;
    private FocusImageView mLayoutFocus;
    private List<Banner> mFocusImages = new ArrayList<>();


    @Override
    public int getContainerLayoutId() {
        return R.layout.fra_sub;
    }

    @Override
    public void loadData() {
        super.loadData();
    }

    @Override
    public void initUi() {
        super.initUi();

        mLayoutFocus = findView(R.id.banner);
        int screenWidth = ScreenUtil.getScreenWidth(getActivity());
        int height = (int) (screenWidth / 2.5f);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(screenWidth, height);
        mLayoutFocus.setLayoutParams(lp);

        mFocusImages.clear();
        Banner banner1 = new Banner();
        banner1.res = R.drawable.banner_1;
        Banner banner2 = new Banner();
        banner2.res = R.drawable.banner_2;
        mFocusImages.add(banner1);
        mFocusImages.add(banner2);

        mFocusImageAdapter = new FocusImageAdapter(getActivity().getApplicationContext(),
                mFocusImages, screenWidth);
        mLayoutFocus.setAdapter(mFocusImageAdapter);
    }

    @Override
    protected void onRefresh(PtrFrameLayout frame) {
        refreshComplete();
    }

    @Override
    protected boolean checkCanRefresh(PtrFrameLayout frame, View content, View header) {
        return mLayoutFocus.canOutsideRefresh();
    }

    @Override
    protected String getTitle() {
        return "子页面";
    }

    @Override
    protected int getActionRes() {
        return 0;
    }

    @Override
    protected View.OnClickListener getActionListener() {
        return null;
    }
}
