package lakeshire.github.com.frozenframework.fragment.common

import android.graphics.Color

import lakeshire.github.com.frozenframework.R
import lakeshire.github.com.frozenframework.fragment.AbsPageHolderFragment
import lakeshire.github.com.frozenframework.fragment.IPager
import lakeshire.github.com.frozenframework.util.ScreenUtil
import lakeshire.github.com.frozenframework.view.PagerSlidingTabStrip

/**
 * 带Tab的Fragment

 * @author lakeshire
 */
abstract class AbsTabFragment : AbsPageHolderFragment() {
    lateinit var mTabs: PagerSlidingTabStrip

    override val containerLayoutId: Int
        get() = R.layout.fra_tab

    override fun loadData() {

    }

    override fun initUi() {
        super.initUi()

        mTabs = mContainerView.findViewById(R.id.tab)
        initTabStyle()
        mTabs.setViewPager(mPager)
    }

    abstract override fun initFragments(): List<IPager>

    abstract val titles: Array<String>

    fun initTabStyle() {
        mTabs.dividerColor = Color.TRANSPARENT
        mTabs.indicatorColor = resources.getColor(R.color.black)
        mTabs.underlineHeight = 0
        mTabs.dividerPadding = 0
        mTabs.shouldExpand = true
        mTabs.textSize = 14
        mTabs.indicatorHeight = ScreenUtil.dp2px(context, 3f)
        mTabs.tabPaddingLeftRight = ScreenUtil.dp2px(context, 17f)
    }
}
