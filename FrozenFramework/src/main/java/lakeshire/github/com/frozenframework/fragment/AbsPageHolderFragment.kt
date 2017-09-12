package lakeshire.github.com.frozenframework.fragment

import `in`.srain.cube.views.ptr.PtrFrameLayout
import android.support.v4.view.ViewPager
import android.view.View
import lakeshire.github.com.frozenframework.R
import lakeshire.github.com.frozenframework.adapter.PageFragmentAdapter

/**
 * 有子页面的Fragment抽象类

 * @author lakeshire
 */
abstract class AbsPageHolderFragment : BasePullFragment() {
    lateinit var mPager: ViewPager
    lateinit var mAdapter: PageFragmentAdapter

    abstract override val containerLayoutId: Int

    override fun initUi() {
        super.initUi()

        mPager = mContainerView.findViewById(R.id.pager)
        val fragments = initFragments()
        mAdapter = PageFragmentAdapter(fragments, childFragmentManager)
        mPager.adapter = mAdapter
    }

    abstract fun initFragments(): List<IPager>

    override fun onRefresh() {
        refreshComplete()
    }

    override fun checkCanRefresh(frame: PtrFrameLayout, content: View, header: View): Boolean {
        return false
    }
}
