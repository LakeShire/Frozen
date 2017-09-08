package lakeshire.github.com.frozenframework.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrFrameLayout;
import lakeshire.github.com.frozenframework.R;
import lakeshire.github.com.frozenframework.adapter.recyclerview.MultiItemTypeAdapter;
import lakeshire.github.com.frozenframework.adapter.recyclerview.wrapper.LoadMoreWrapper;
import lakeshire.github.com.frozenframework.view.recyclerview.DividerItemDecoration;

/**
 * 含RecyclerView的Fragment基础类
 *
 * @author lakeshire
 */
abstract public class BaseRecyclerViewFragment<T> extends BasePullFragment {
    public static final int STATUES_OK = 0;
    public static final int STATUES_NETWORK_ERROR = 1;
    public static final int STATUES_NO_CONTENT = 2;
    public static final int STATUS_NO_MORE = 3;

    private RecyclerView mRecyclerView;
    protected List<T> mDataList = new ArrayList<>();
    protected MultiItemTypeAdapter<T> mAdapter;
    protected LoadMoreWrapper mLoadMoreWrapper;

    @Override
    public boolean checkCanRefresh(PtrFrameLayout frame, View content, View header) {
        RecyclerView.LayoutManager lm = mRecyclerView.getLayoutManager();
        if (lm instanceof LinearLayoutManager) {
            return ((LinearLayoutManager) lm).findFirstCompletelyVisibleItemPosition() == 0;
        } else {
            return false;
        }
    }

    @Override
    public int getContainerLayoutId() {
        return R.layout.fra_recycler_view;
    }

    @Override
    public void loadData() {
        super.loadData();
    }

    @Override
    public void initUi() {
        super.initUi();

        mRecyclerView = findView(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setVisibility(View.GONE);

        if (hasDivider()) {
            initDivider();
        }
        mAdapter = initAdapter();
        mAdapter = addHeaderAndFooter();

        mLoadMoreWrapper = new LoadMoreWrapper(mAdapter);
        mLoadMoreWrapper.setLoadMoreView(LayoutInflater.from(mContext).inflate(getFooterLayoutId(), mRecyclerView, false));
        mLoadMoreWrapper.setOnLoadMoreListener(new LoadMoreWrapper.OnLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                loadMoreData();
            }
        });
        mRecyclerView.setAdapter(mLoadMoreWrapper);
    }

    /**
     * 默认提供分割线
     * 通过drawable/divider_bg描述
     */
    protected void initDivider() {
        //  注意这里传的Context有影响 需要传Activity的
        //  因为是通过复写主题中的listDivider 而Application中没有主题 只有Activity中才有主题的概念
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),
                DividerItemDecoration.VERTICAL_LIST));
    }

    protected boolean hasDivider() {
        return true;
    }

    protected MultiItemTypeAdapter<T> addHeaderAndFooter() {
        return mAdapter;
    }

    abstract protected MultiItemTypeAdapter<T> initAdapter();

    abstract protected void loadMoreData();

    protected int getFooterLayoutId() {
        return R.layout.layout_list_footer;
    }

    public void notifyAdapter(final int status, final boolean refresh) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                hideAllLayout();
                if (refresh) {
                    refreshComplete();
                }

                if (status == STATUES_NETWORK_ERROR) {
                    if (refresh) {
                        mDataList.clear();
                        showNetworkErrorLayout();
                    }
                } else if (status == STATUES_NO_CONTENT) {
                    if (refresh) {
                        mDataList.clear();
                        showNoContentLayout();
                    }
                }

                if (mAdapter != null) {
                    mAdapter.notifyDataSetChanged();
                }

                if (status == STATUES_OK) {
                    mRecyclerView.setVisibility(View.VISIBLE);
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
            }
        });
    }

    @Override
    public void showNetworkErrorLayout() {
        super.showNetworkErrorLayout();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mRecyclerView.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void showNoContentLayout() {
        super.showNoContentLayout();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mRecyclerView.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void showLoadingLayout() {
        super.showLoadingLayout();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mRecyclerView.setVisibility(View.GONE);
            }
        });
    }
}
