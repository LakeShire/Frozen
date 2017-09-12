package lakeshire.github.com.frozenframework.fragment.common

import android.support.v4.view.ViewPager

import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.ashokvarma.bottomnavigation.BottomNavigationItem

import lakeshire.github.com.frozenframework.R
import lakeshire.github.com.frozenframework.fragment.AbsPageHolderFragment

/**
 * 带BottomBar的Fragment

 * @author lakeshire
 */
abstract class AbsBottomBarFragment : AbsPageHolderFragment(), BottomNavigationBar.OnTabSelectedListener {
    private var mBottomNavigationBar: BottomNavigationBar? = null

    override fun loadData() {

    }

    override fun initUi() {
        super.initUi()

        mBottomNavigationBar = mContainerView.findViewById(R.id.bottom_bar)
        initBottomBar()
        mPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                mBottomNavigationBar!!.selectTab(position)
            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })
    }

    fun initBottomBar() {
        initBottomBarStyle()
        for (i in 0..titles.size - 1) {
            mBottomNavigationBar!!.addItem(BottomNavigationItem(icons[i], titles[i])
                    .setActiveColorResource(activeColors[i]))
        }
        mBottomNavigationBar!!.initialise()
        mBottomNavigationBar!!.setTabSelectedListener(this)

    }

    fun initBottomBarStyle() {
        mBottomNavigationBar!!.setMode(BottomNavigationBar.MODE_FIXED)
        mBottomNavigationBar!!.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC
        )
        mBottomNavigationBar!!.setFirstSelectedPosition(0)
    }

    override val containerLayoutId: Int
        get() = R.layout.fra_bottom_bar

    override fun onTabSelected(i: Int) {
        mPager.setCurrentItem(i, true)
    }

    override fun onTabUnselected(i: Int) {

    }

    override fun onTabReselected(i: Int) {

    }

    abstract val titles: Array<String>

    abstract val icons: IntArray

    abstract val activeColors: IntArray
}
