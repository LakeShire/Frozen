package lakeshire.github.com.frozenframework.fragment;

import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.List;

import in.srain.cube.views.ptr.PtrFrameLayout;
import lakeshire.github.com.frozenframework.R;
import lakeshire.github.com.frozenframework.adapter.PageFragmentAdapter;
import lakeshire.github.com.frozenframework.util.ScreenUtil;
import lakeshire.github.com.frozenframework.view.PagerSlidingTabStrip;

/**
 * 有子页面的Fragment
 *
 * @author lakeshire
 */
abstract public class HolderFragment extends BasePullFragment {
    protected PagerSlidingTabStrip mTabs;
    protected ViewPager mPager;
    protected PageFragmentAdapter mAdapter;

    @Override
    public int getContainerLayoutId() {
        return R.layout.fra_holder;
    }

    @Override
    public void loadData() {
        super.loadData();
    }

    @Override
    public void initUi() {
        super.initUi();

        mTabs = findView(R.id.tab);
        mPager = findView(R.id.pager);
        initTabStyle();

        List<PagerFragment> fragments = initFragments();
        mAdapter = new PageFragmentAdapter(fragments, getChildFragmentManager());
        mPager.setAdapter(mAdapter);
        mTabs.setViewPager(mPager);
    }

    abstract protected List<PagerFragment> initFragments();

    abstract protected String[] getTitles();

    protected void initTabStyle() {
        mTabs.setDividerColor(Color.TRANSPARENT);
        mTabs.setIndicatorColor(getResources().getColor(R.color.black));
        mTabs.setUnderlineHeight(0);
        mTabs.setDividerPadding(0);
        mTabs.setShouldExpand(true);
        mTabs.setTextSize(14);
        mTabs.setIndicatorHeight(ScreenUtil.dp2px(getActivity(), 3));
        mTabs.setTabPaddingLeftRight(ScreenUtil.dp2px(getActivity(), 17));
    }

    @Override
    protected void onRefresh(PtrFrameLayout frame) {
        refreshComplete();
    }

    @Override
    protected boolean checkCanRefresh(PtrFrameLayout frame, View content, View header) {
        return false;
    }
}
