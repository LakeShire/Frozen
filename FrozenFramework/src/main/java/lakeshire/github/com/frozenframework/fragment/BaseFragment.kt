package lakeshire.github.com.frozenframework.fragment

import android.arch.lifecycle.LifecycleFragment
import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import com.orhanobut.logger.Logger
import lakeshire.github.com.frozenframework.BaseApplication
import lakeshire.github.com.frozenframework.R
import lakeshire.github.com.frozenframework.activity.BaseActivity
import java.util.*

/**
 * Fragment基类

 * @author lakeshire
 */
abstract class BaseFragment : LifecycleFragment(), View.OnClickListener {

    var mContext: Context = BaseApplication.myApplicationContext
    lateinit var mContainerView: ViewGroup
    val mImageViews = ArrayList<ImageView>()
    var mLoadingLayout: View? = null
    var mNetworkErrorLayout: View? = null
    var mNoContentLayout: View? = null
    var mLayoutParams: ViewGroup.LayoutParams? = null

    fun startFragment(clazz: Class<*>) {
        (activity as BaseActivity).startFragment(clazz, null)
    }

    fun startFragment(clazz: Class<*>, bundle: Bundle?) {
        (activity as BaseActivity).startFragment(clazz, bundle)
    }

    fun endFragment() {
        (activity as BaseActivity).endFragment()
    }

    var mContentView: ViewGroup? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mContainerView = inflater!!.inflate(containerLayoutId, container, false) as ViewGroup
        mContentView = mContainerView.findViewById<ViewGroup>(R.id.layout_content)
        if (mContentView != null) {
            if (mContentView is FrameLayout) {
                mLayoutParams = FrameLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT)
                (mLayoutParams as FrameLayout.LayoutParams).gravity = Gravity.CENTER
            } else if (mContentView is RelativeLayout) {
                mLayoutParams = RelativeLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT)
                (mLayoutParams as RelativeLayout.LayoutParams).addRule(RelativeLayout.CENTER_IN_PARENT)
            } else if (mContentView is LinearLayout) {
                mLayoutParams = LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT)
                (mLayoutParams as LinearLayout.LayoutParams).gravity = Gravity.CENTER
            } else {
                mLayoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT)
            }
        }
        return mContainerView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initUi()
        loadData()
    }

    abstract fun loadData()

    abstract fun initUi()

    abstract val containerLayoutId: Int

    val loadingLayoutId: Int
        get() = R.layout.layout_loading

    val networkErrorLayoutId: Int
        get() = R.layout.layout_network_error

    val noContentLayoutId: Int
        get() = R.layout.layout_no_content

    open fun refresh() {
        if (this is BaseListFragment<*>) {
            this.onRefresh()
        } else if (this is BaseRecyclerViewFragment<*>) {
            this.onRefresh()
        }
    }

    override fun onResume() {
        super.onResume()
        Logger.d(this)
        if (this !is IPager) {
            hideOrShowTitleBar(isTitleVisible())
            if (isTitleVisible()) {
                setTitle(getTitle())
                setAction(getActionRes(), getActionListener())
            }
        }
    }

    open fun onBackPressed(): Boolean {
        endFragment()
        return true
    }

    override fun onDestroy() {
        for (iv in mImageViews) {
            iv.setImageBitmap(null)
        }
        super.onDestroy()
    }

    open fun showLoadingLayout() {
        hideAllLayout()
        runOnUiThread(Runnable {
            if (mLoadingLayout == null) {
                mLoadingLayout = View.inflate(context, loadingLayoutId, null)
                mContentView?.addView(mLoadingLayout, mLayoutParams)
            } else {
                mLoadingLayout!!.visibility = View.VISIBLE
            }
        })
    }

    open fun showNetworkErrorLayout() {
        hideAllLayout()
        runOnUiThread(Runnable {
            if (mNetworkErrorLayout == null) {
                mNetworkErrorLayout = View.inflate(context, networkErrorLayoutId, null)
                mNetworkErrorLayout!!.setOnClickListener { refresh() }
                mContentView?.addView(mNetworkErrorLayout, mLayoutParams)
            } else {
                mNetworkErrorLayout!!.visibility = View.VISIBLE
            }
        })
    }

    open fun showNoContentLayout() {
        hideAllLayout()
        runOnUiThread(Runnable {
            if (mNoContentLayout == null) {
                mNoContentLayout = View.inflate(context, noContentLayoutId, null)
                mNoContentLayout?.setOnClickListener { refresh() }
                mContentView?.addView(mNoContentLayout, mLayoutParams)
            } else {
                mNoContentLayout?.visibility = View.VISIBLE
            }
        })
    }

    fun hideAllLayout() {
        runOnUiThread(Runnable {
            if (mNoContentLayout != null) {
                mNoContentLayout?.visibility = View.GONE
            }
            if (mNetworkErrorLayout != null) {
                mNetworkErrorLayout?.visibility = View.GONE
            }
            if (mLoadingLayout != null) {
                mLoadingLayout?.visibility = View.GONE
            }
        })
    }

    fun canUpdateUi(): Boolean {
        return isAdded && !isRemoving && !isDetached && activity != null
    }

    fun runOnUiThread(runnable: Runnable) {
        if (canUpdateUi()) {
            activity.runOnUiThread(runnable)
        }
    }

    open fun hideOrShowTitleBar(show: Boolean) {
        if (activity != null && activity is BaseActivity) {
            (activity as BaseActivity).hideOrShowTitleBar(show)
        }
    }

    open fun setAction(res: Int, listener: View.OnClickListener?) {
        if (activity != null && activity is BaseActivity) {
            (activity as BaseActivity).setAction(res, listener)
        }
    }

    open fun isTitleVisible(): Boolean {
        return true
    }

    open fun setTitle(title: String) {
        if (activity != null && activity is BaseActivity) {
            (activity as BaseActivity).setTitle(title)
        }
    }

    open fun getTitle(): String {
        return ""
    }

    open fun getActionRes(): Int {
        return 0
    }

    open fun getActionListener(): View.OnClickListener? {
        return null
    }

    override fun onClick(view: View?) {

    }
}

