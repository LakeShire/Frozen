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

/**
 * Created by liuhan on 17/9/1.
 */

public class MainFragment extends BasePullFragment {
    private FocusImageAdapter mFocusImageAdapter;
    private FocusImageView mLayoutFocus;
    private List<Banner> mFocusImages = new ArrayList<>();


    @Override
    public int getContainerLayoutId() {
        return R.layout.fra_main;
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
//        mLayoutFocus.setDisallowInterceptTouchEventView(mPtrFrameLayout);
    }

    @Override
    protected void onRefresh(PtrFrameLayout frame) {
        refreshComplete();
    }

    @Override
    protected boolean checkCanRefresh(PtrFrameLayout frame, View content, View header) {
        return mLayoutFocus.canOutsideRefresh();
    }

//        for (int i = 0; i < 10; i++) {
//            mDataList.add("Item " + (i+1));
//        }
//    }

//    @Override
//    protected void initData() {
//
//    }

//    @Override
//    protected void onRefresh(PtrFrameLayout frame) {
//        refreshComplete();
//    }
//
//    @Override
//    protected boolean checkCanRefresh(PtrFrameLayout frame, View content, View header) {
//        return true;
//    }
//
//    @Override
//    protected BaseAdapter<String> getAdapter() {
//        mAdapter = new SubjectAdapter(getContext(), mDataList, R.layout.item);
//        return mAdapter;
//    }
//
//    @Override
//    protected void loadMoreData() {
//        notifyAdapter(STATUES_OK, false);
//    }
//
//    @Override
//    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//    }
}
