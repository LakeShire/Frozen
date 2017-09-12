package lakeshire.github.com.frozenframework.fragment

import `in`.srain.cube.views.ptr.PtrFrameLayout
import `in`.srain.cube.views.ptr.PtrHandler
import `in`.srain.cube.views.ptr.PtrUIHandler
import `in`.srain.cube.views.ptr.header.MaterialHeader
import android.view.View
import lakeshire.github.com.frozenframework.R

/**
 * 支持下拉的Fragment

 * @author lakeshire
 */
abstract class BasePullFragment : BaseFragment() {
    lateinit var mPtrFrameLayout: PtrFrameLayout

    override fun initUi() {
        mPtrFrameLayout = mContainerView.findViewById<View>(R.id.ptr_frame) as PtrFrameLayout
        initPtrFrame()
    }

    fun initPtrFrame() {
        val header = header
        mPtrFrameLayout.setDurationToCloseHeader(1500)
        mPtrFrameLayout.setDurationToClose(300)
        mPtrFrameLayout.resistance = 1.5f
        mPtrFrameLayout.setRatioOfHeaderHeightToRefresh(1.0f)
        mPtrFrameLayout.isKeepHeaderWhenRefresh = true
        mPtrFrameLayout.isPullToRefresh = true
        mPtrFrameLayout.headerView = header
        if (header is PtrUIHandler) {
            mPtrFrameLayout.addPtrUIHandler(header as PtrUIHandler)
        }
        mPtrFrameLayout.setPtrHandler(object : PtrHandler {
            override fun checkCanDoRefresh(frame: PtrFrameLayout, content: View, header: View): Boolean {
                return checkCanRefresh(frame, content, header)
            }

            override fun onRefreshBegin(frame: PtrFrameLayout) {
                onRefresh()
            }
        })

    }

    val header: View
        get() {
            val header = MaterialHeader(mContext)
            header.setPadding(0, 0, 0, 32)
            return header
        }

    fun refreshComplete() {
        runOnUiThread(Runnable { mPtrFrameLayout.refreshComplete() })
    }

    abstract fun onRefresh()

    abstract fun checkCanRefresh(frame: PtrFrameLayout, content: View, header: View): Boolean
}
