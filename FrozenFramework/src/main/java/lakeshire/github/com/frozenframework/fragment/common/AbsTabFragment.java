package lakeshire.github.com.frozenframework.fragment.common;

import android.graphics.Color;

import java.util.List;

import lakeshire.github.com.frozenframework.R;
import lakeshire.github.com.frozenframework.fragment.AbsPageHolderFragment;
import lakeshire.github.com.frozenframework.fragment.IPager;
import lakeshire.github.com.frozenframework.util.ScreenUtil;
import lakeshire.github.com.frozenframework.view.PagerSlidingTabStrip;

/**
 * 带Tab的Fragment
 *
 * @author lakeshire
 */
abstract public class AbsTabFragment extends AbsPageHolderFragment {
    protected PagerSlidingTabStrip mTabs;

    @Override
    public int getContainerLayoutId() {
        return R.layout.fra_tab;
    }

    @Override
    public void loadData() {

    }

    @Override
    public void initUi() {
        super.initUi();

        mTabs = findView(R.id.tab);
        initTabStyle();
        mTabs.setViewPager(mPager);
    }

    abstract protected List<IPager> initFragments();

    abstract protected String[] getTitles();

    protected void initTabStyle() {
        mTabs.setDividerColor(Color.TRANSPARENT);
        mTabs.setIndicatorColor(getResources().getColor(R.color.black));
        mTabs.setUnderlineHeight(0);
        mTabs.setDividerPadding(0);
        mTabs.setShouldExpand(true);
        mTabs.setTextSize(14);
        mTabs.setIndicatorHeight(ScreenUtil.dp2px(mContext, 3));
        mTabs.setTabPaddingLeftRight(ScreenUtil.dp2px(mContext, 17));
    }
}
