package lakeshire.github.com.frozenframework.fragment.common;

import android.support.v4.view.ViewPager;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

import lakeshire.github.com.frozenframework.R;
import lakeshire.github.com.frozenframework.fragment.AbsPageHolderFragment;

/**
 * 带BottomBar的Fragment
 *
 * @author lakeshire
 */
abstract public class AbsBottomBarFragment extends AbsPageHolderFragment implements
        BottomNavigationBar
        .OnTabSelectedListener {
    private BottomNavigationBar mBottomNavigationBar;

    @Override
    public void loadData() {

    }

    @Override
    public void initUi() {
        super.initUi();

        mBottomNavigationBar = findView(R.id.bottom_bar);
        initBottomBar();
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

    private void initBottomBar() {
        initBottomBarStyle();
        for (int i = 0; i < getTitles().length; i++) {
            mBottomNavigationBar.addItem(new BottomNavigationItem(getIcons()[i], getTitles()[i])
                    .setActiveColorResource(getActiveColors()[i]));
        }
        mBottomNavigationBar.initialise();
        mBottomNavigationBar.setTabSelectedListener(this);

    }

    protected void initBottomBarStyle() {
        mBottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        mBottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC
        );
        mBottomNavigationBar.setFirstSelectedPosition(0);
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
