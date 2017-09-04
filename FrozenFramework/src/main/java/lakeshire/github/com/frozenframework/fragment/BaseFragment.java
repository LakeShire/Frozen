package lakeshire.github.com.frozenframework.fragment;

import android.arch.lifecycle.LifecycleFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import lakeshire.github.com.frozenframework.R;
import lakeshire.github.com.frozenframework.activity.BaseActivity;

/**
 * Fragment基类
 */
public abstract class BaseFragment extends LifecycleFragment {

    protected ViewGroup mContainerView;
    private List<ImageView> mImageViews = new ArrayList<>();
    private View mLoadingLayout;
    private View mNetworkErrorLayout;
    private View mNoContentLayout;
    private ViewGroup.LayoutParams mLayoutParams;

    public void startFragment(Class<?> clazz) {
        ((BaseActivity) getActivity()).startFragment(clazz, null);
    }

    public void startFragment(Class<?> clazz, Bundle bundle) {
        ((BaseActivity) getActivity()).startFragment(clazz, bundle);
    }

    public void endFragment() {
        ((BaseActivity) getActivity()).endFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContainerView = (ViewGroup) inflater.inflate(getContainerLayoutId(), container, false);
        if (mContainerView instanceof FrameLayout) {
            mLayoutParams = new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            ((FrameLayout.LayoutParams) mLayoutParams).gravity = Gravity.CENTER;
        } else if (mContainerView instanceof RelativeLayout) {
            mLayoutParams = new RelativeLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            ((RelativeLayout.LayoutParams) mLayoutParams).addRule(RelativeLayout.CENTER_IN_PARENT);
        } else if (mContainerView instanceof LinearLayout) {
            mLayoutParams = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            ((LinearLayout.LayoutParams) mLayoutParams).gravity = Gravity.CENTER;
        } else {
            mLayoutParams = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
        }
        return mContainerView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initUi();
        loadData();
    }

    public void loadData() {

    }

    public void initUi() {

    }

    public void refresh() {

    }

    public abstract int getContainerLayoutId();

    public int getLoadingLayoutId() {
        return R.layout.layout_loading;
    }

    public int getNetworkErrorLayoutId() {
        return R.layout.layout_network_error;
    }

    public int getNoContentLayoutId() {
        return R.layout.layout_no_content;
    }

    public <T extends View> T findView(int resId) {
        View view = mContainerView.findViewById(resId);
        if (view instanceof ImageView) {
            mImageViews.add((ImageView) view);
        }
        return (T) view;
    }

    public boolean onBackPressed() {
        endFragment();
        return true;
    }

    @Override
    public void onDestroy() {
        for (ImageView iv : mImageViews) {
            iv.setImageBitmap(null);
        }
        super.onDestroy();
    }

    public void showLoadingLayout() {
        hideAllLayout();
        if (canUpdateUi()) {
            if (mLoadingLayout == null) {
                mLoadingLayout = View.inflate(getContext(), getLoadingLayoutId(), null);
                mContainerView.addView(mLoadingLayout, mLayoutParams);
            }
        } else {
            mLoadingLayout.setVisibility(View.VISIBLE);
        }
    }

    public void showNetworkErrorLayout() {
        hideAllLayout();
        if (canUpdateUi()) {
            if (mNetworkErrorLayout == null) {
                mNetworkErrorLayout = View.inflate(getContext(), getNetworkErrorLayoutId(), null);
                mNetworkErrorLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        refresh();
                    }
                });
                mContainerView.addView(mNetworkErrorLayout, mLayoutParams);
            }
        } else {
            mNetworkErrorLayout.setVisibility(View.VISIBLE);
        }
    }

    public void showNoContentLayout() {
        hideAllLayout();
        if (canUpdateUi()) {
            if (mNoContentLayout == null) {
                mNoContentLayout = View.inflate(getContext(), getNoContentLayoutId(), null);
                mNoContentLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        refresh();
                    }
                });
                mContainerView.addView(mNoContentLayout, mLayoutParams);
            }
        } else {
            mNoContentLayout.setVisibility(View.VISIBLE);
        }
    }

    public void hideAllLayout() {
        if (canUpdateUi()) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (mNoContentLayout != null) {
                        mNoContentLayout.setVisibility(View.GONE);
                    }
                    if (mNetworkErrorLayout != null) {
                        mNetworkErrorLayout.setVisibility(View.GONE);
                    }
                    if (mLoadingLayout != null) {
                        mLoadingLayout.setVisibility(View.GONE);
                    }
                }
            });
        }
    }

    public boolean canUpdateUi() {
        return isAdded() && !isRemoving() && !isDetached() && getActivity() != null;
    }
}

