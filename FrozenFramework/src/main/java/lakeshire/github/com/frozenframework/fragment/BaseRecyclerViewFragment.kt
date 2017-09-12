package lakeshire.github.com.frozenframework.fragment

import `in`.srain.cube.views.ptr.PtrFrameLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import lakeshire.github.com.frozenframework.R
import lakeshire.github.com.frozenframework.adapter.recyclerview.MultiItemTypeAdapter
import lakeshire.github.com.frozenframework.adapter.recyclerview.wrapper.HeaderAndFooterWrapper
import lakeshire.github.com.frozenframework.adapter.recyclerview.wrapper.LoadMoreWrapper
import lakeshire.github.com.frozenframework.view.recyclerview.DividerItemDecoration
import java.util.*

/**
 * 含RecyclerView的Fragment基础类

 * footer和加载更多的同时显示会有问题
 * @author lakeshire
 */
abstract class BaseRecyclerViewFragment<T> : BasePullFragment() {

    private var mRecyclerView: RecyclerView? = null
    protected var mDataList: MutableList<T> = ArrayList()
    protected lateinit var mAdapter: MultiItemTypeAdapter<T>
    protected lateinit var mLoadMoreWrapper: LoadMoreWrapper<T>
    private var mHeaderAndFooterWrapper: HeaderAndFooterWrapper<*>? = null

    override fun checkCanRefresh(frame: PtrFrameLayout, content: View, header: View): Boolean {
        val lm = mRecyclerView!!.layoutManager
        if (lm is LinearLayoutManager) {
            val isTop = lm.findFirstCompletelyVisibleItemPosition() == 0
            if (mAdapter is IInnerScrollable) {
                return isTop && !(mAdapter as IInnerScrollable).isInnerScrolling
            } else {
                return isTop
            }
        } else {
            return false
        }
    }

    override val containerLayoutId: Int
        get() = R.layout.fra_recycler_view

    override fun initUi() {
        super.initUi()

        mRecyclerView = mContainerView.findViewById<RecyclerView>(R.id.recycler_view)
        mRecyclerView!!.layoutManager = LinearLayoutManager(context)
        mRecyclerView!!.visibility = View.GONE

        if (hasDivider()) {
            initDivider()
        }
        mAdapter = initAdapter()
        mAdapter.setOnItemClickListener(object : MultiItemTypeAdapter.OnItemClickListener {
            override fun onItemClick(view: View, holder: RecyclerView.ViewHolder, position: Int) {
                onItemClicked(view, holder, position)
            }

            override fun onItemLongClick(view: View, holder: RecyclerView.ViewHolder, position: Int): Boolean {
                return onItemLongClicked(view, holder, position)
            }
        })

        mHeaderAndFooterWrapper = HeaderAndFooterWrapper<T>(mAdapter)
        addHeaderAndFooter()

        mLoadMoreWrapper = LoadMoreWrapper<T>(mHeaderAndFooterWrapper)
        mLoadMoreWrapper!!.setLoadMoreView(LayoutInflater.from(context).inflate(footerLayoutId, mRecyclerView, false))
        mLoadMoreWrapper!!.setOnLoadMoreListener { loadMoreData() }

        mRecyclerView!!.adapter = mLoadMoreWrapper
    }

    protected abstract fun onItemClicked(view: View, holder: RecyclerView.ViewHolder, position: Int)

    protected abstract fun onItemLongClicked(view: View, holder: RecyclerView.ViewHolder, position: Int): Boolean

    /**
     * 默认提供分割线
     * 通过drawable/divider_bg描述
     */
    protected fun initDivider() {
        //  注意这里传的Context有影响 需要传Activity的
        //  因为是通过复写主题中的listDivider 而Application中没有主题 只有Activity中才有主题的概念
        mRecyclerView!!.addItemDecoration(DividerItemDecoration(activity,
                DividerItemDecoration.VERTICAL_LIST))
    }

    protected open fun hasDivider(): Boolean {
        return true
    }

    protected open fun addHeaderAndFooter() {

    }

    protected abstract fun initAdapter(): MultiItemTypeAdapter<T>

    protected abstract fun loadMoreData()

    protected val footerLayoutId: Int
        get() = R.layout.layout_list_footer

    fun notifyAdapter(status: Int, refresh: Boolean) {
        runOnUiThread(Runnable {
            hideAllLayout()
            if (refresh) {
                refreshComplete()
            }

            if (status == STATUES_NETWORK_ERROR) {
                if (refresh) {
                    mDataList.clear()
                    showNetworkErrorLayout()
                }
            } else if (status == STATUES_NO_CONTENT) {
                if (refresh) {
                    mDataList.clear()
                    showNoContentLayout()
                }
            }

            if (mLoadMoreWrapper != null) {
                mLoadMoreWrapper!!.notifyDataSetChanged()
            }

            if (status == STATUES_OK) {
                mRecyclerView!!.visibility = View.VISIBLE
            }

            //                if (mListView instanceof LoadMoreListView) {
            //                    switch (status) {
            //                        case STATUES_NETWORK_ERROR:
            //                            ((LoadMoreListView) mListView).onLoadMoreComplete(LoadMoreListView
            //                                    .STATUS_NETWORK_ERROR);
            //                            break;
            //                        case STATUES_NO_CONTENT:
            //                            ((LoadMoreListView) mListView).onLoadMoreComplete(LoadMoreListView
            //                                    .STATUS_NO_CONTENT);
            //                            break;
            //                        case STATUES_OK:
            //                            ((LoadMoreListView) mListView).onLoadMoreComplete(LoadMoreListView
            //                                    .STATUS_OK);
            //                            break;
            //                        case STATUS_NO_MORE:
            //                            ((LoadMoreListView) mListView).onLoadMoreComplete(LoadMoreListView
            //                                    .STATUS_NO_MORE);
            //                            break;
            //                    }
            //                }
        })
    }

    override fun showNetworkErrorLayout() {
        super.showNetworkErrorLayout()
        runOnUiThread(Runnable { mRecyclerView!!.visibility = View.GONE })
    }

    override fun showNoContentLayout() {
        super.showNoContentLayout()
        runOnUiThread(Runnable { mRecyclerView!!.visibility = View.GONE })
    }

    override fun showLoadingLayout() {
        super.showLoadingLayout()
        runOnUiThread(Runnable { mRecyclerView!!.visibility = View.GONE })
    }

    protected fun addHeaderView(view: View) {
        mHeaderAndFooterWrapper!!.addHeaderView(view)
    }

    protected fun addFooterView(view: View) {
        mHeaderAndFooterWrapper!!.addFootView(view)
    }

    companion object {
        val STATUES_OK = 0
        val STATUES_NETWORK_ERROR = 1
        val STATUES_NO_CONTENT = 2
        val STATUS_NO_MORE = 3
    }
}
