package com.github.lakeshire.frozen


import `in`.srain.cube.views.ptr.PtrFrameLayout
import android.content.DialogInterface
import android.view.View
import android.widget.RelativeLayout
import lakeshire.github.com.frozenframework.fragment.BasePullFragment
import lakeshire.github.com.frozenframework.util.DialogUtil
import lakeshire.github.com.frozenframework.util.ScreenUtil
import lakeshire.github.com.frozenframework.view.focusimage.Banner
import lakeshire.github.com.frozenframework.view.focusimage.FocusImageAdapter
import lakeshire.github.com.frozenframework.view.focusimage.FocusImageView
import java.util.*

class SubFragment : BasePullFragment() {
    private var mFocusImageAdapter: FocusImageAdapter? = null
    private var mLayoutFocus: FocusImageView? = null
    private val mFocusImages = ArrayList<Banner>()

    override val containerLayoutId: Int
        get() = R.layout.fra_sub

    override fun loadData() {

    }

    override fun initUi() {
        super.initUi()

        mLayoutFocus = mContainerView.findViewById(R.id.banner)
        val screenWidth = ScreenUtil.getScreenWidth(activity)
        val height = (screenWidth / 2.5f).toInt()
        val lp = RelativeLayout.LayoutParams(screenWidth, height)
        mLayoutFocus!!.layoutParams = lp

        mFocusImages.clear()
        val banner1 = Banner()
        banner1.res = R.drawable.banner_1
        val banner2 = Banner()
        banner2.res = R.drawable.banner_2
        mFocusImages.add(banner1)
        mFocusImages.add(banner2)

        mFocusImageAdapter = FocusImageAdapter(activity.applicationContext,
                mFocusImages)
        mLayoutFocus!!.adapter = mFocusImageAdapter
    }

    override fun refresh() {

    }

    override fun onRefresh() {
        refreshComplete()
    }

    override fun checkCanRefresh(frame: PtrFrameLayout, content: View, header: View): Boolean {
        return mLayoutFocus!!.canOutsideRefresh()
    }

    override fun getTitle(): String {
        return "子页面"
    }

    override fun getActionRes(): Int {
        return 0
    }

    override fun getActionListener(): View.OnClickListener? {
        return null
    }

    override fun onBackPressed(): Boolean {
        DialogUtil.showTips(activity, "确定退出？", "确定退出？", "确定", DialogInterface.OnClickListener { dialog, which -> endFragment() }, "取消", null)
        return true
    }
}
