package lakeshire.github.com.frozenframework.fragment;

import android.support.v4.view.ViewPager;
import android.view.View;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

import in.srain.cube.views.ptr.PtrFrameLayout;
import lakeshire.github.com.frozenframework.R;

/**
 * Created by louis.liu on 2017/9/6.
 */

abstract public class BottomBarFragment extends PageHolderFragment implements BottomNavigationBar
        .OnTabSelectedListener {
    private BottomNavigationBar mBottomNavigationBar;

    @Override
    public void initUi() {
        super.initUi();
        initBottomBar();
    }

    private void initBottomBar() {
        mBottomNavigationBar = findView(R.id.bottom_bar);
        mBottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        mBottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC
        );

        for (int i = 0; i < getTitles().length; i++) {
            mBottomNavigationBar.addItem(new BottomNavigationItem(getIcons()[i], getTitles()[i])
                    .setActiveColorResource(getActiveColors()[i]));
        }

        mBottomNavigationBar.setFirstSelectedPosition(0).initialise();
        mBottomNavigationBar.setTabSelectedListener(this);

        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int
                    positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mBottomNavigationBar.selectTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected void onRefresh(PtrFrameLayout frame) {
        refreshComplete();
    }

    @Override
    protected boolean checkCanRefresh(PtrFrameLayout frame, View content, View header) {
        return false;
    }

    @Override
    public int getContainerLayoutId() {
        return R.layout.fra_bottom_bar;
    }

    @Override
    public void onTabSelected(int i) {
        mPager.setCurrentItem(i, true);
    }

    @Override
    public void onTabUnselected(int i) {

    }

    @Override
    public void onTabReselected(int i) {

    }

    abstract protected String[] getTitles();

    abstract protected int[] getIcons();

    abstract protected int[] getActiveColors();
}
