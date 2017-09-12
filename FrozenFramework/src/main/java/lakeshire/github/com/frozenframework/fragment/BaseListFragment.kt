package lakeshire.github.com.frozenframework.fragment

import `in`.srain.cube.views.ptr.PtrFrameLayout
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import lakeshire.github.com.frozenframework.R
import lakeshire.github.com.frozenframework.adapter.BaseAdapter
import lakeshire.github.com.frozenframework.view.LoadMoreListView
import java.util.*

/**
 * 列表Fragment基础类

 * @author lakeshire
 */
abstract class BaseListFragment<T> : BasePullFragment(), AdapterView.OnItemClickListener {

    lateinit var mListView: ListView
    var mDataList: MutableList<T> = ArrayList()
    var mAdapter: BaseAdapter<*>? = null

    override fun loadData() {
        initData()
    }

    abstract fun initData()

    override val containerLayoutId: Int
        get() = R.layout.fra_base_list

    override fun initUi() {
        super.initUi()
        mListView = mContainerView.findViewById(R.id.list)
        mAdapter = adapter
        mListView.adapter = mAdapter
        mListView.onItemClickListener = this
        if (mListView is LoadMoreListView) {
            (mListView as LoadMoreListView).setLoadMoreCallback(object : LoadMoreListView.Callback {
                override fun loadMore() {
                    loadMoreData()
                }

                override fun initFooter(view: View) {
                    initListFooter(view)
                }
            })
        }
    }

    override fun checkCanRefresh(frame: PtrFrameLayout, content: View, header: View): Boolean {
        val absListView = mListView
        val canRefresh = !(absListView.childCount > 0 && (absListView
                .firstVisiblePosition > 0 || absListView.getChildAt(0).top < absListView.paddingTop))
        return canRefresh
    }

    abstract val adapter: BaseAdapter<T>

    fun initListFooter(view: View) {

    }

    abstract fun loadMoreData()

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

            if (mAdapter != null) {
                mAdapter!!.notifyDataSetChanged()
            }

            if (status == STATUES_OK) {
                mListView.visibility = View.VISIBLE
            }

            if (mListView is LoadMoreListView) {
                when (status) {
                    STATUES_NETWORK_ERROR -> (mListView as LoadMoreListView).onLoadMoreComplete(LoadMoreListView
                            .STATUS_NETWORK_ERROR)
                    STATUES_NO_CONTENT -> (mListView as LoadMoreListView).onLoadMoreComplete(LoadMoreListView
                            .STATUS_NO_CONTENT)
                    STATUES_OK -> (mListView as LoadMoreListView).onLoadMoreComplete(LoadMoreListView
                            .STATUS_OK)
                    STATUS_NO_MORE -> (mListView as LoadMoreListView).onLoadMoreComplete(LoadMoreListView
                            .STATUS_NO_MORE)
                }
            }
        })
    }

    abstract override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long)

    override fun showNetworkErrorLayout() {
        super.showNetworkErrorLayout()
        runOnUiThread(Runnable { mListView.visibility = View.GONE })
    }

    override fun showNoContentLayout() {
        super.showNoContentLayout()
        runOnUiThread(Runnable { mListView.visibility = View.GONE })
    }

    override fun showLoadingLayout() {
        super.showLoadingLayout()
        runOnUiThread(Runnable { mListView.visibility = View.GONE })
    }

    companion object {
        val STATUES_OK = 0
        val STATUES_NETWORK_ERROR = 1
        val STATUES_NO_CONTENT = 2
        val STATUS_NO_MORE = 3
    }
}
