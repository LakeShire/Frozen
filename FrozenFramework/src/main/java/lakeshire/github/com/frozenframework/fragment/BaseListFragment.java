package lakeshire.github.com.frozenframework.fragment;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrFrameLayout;
import lakeshire.github.com.frozenframework.R;
import lakeshire.github.com.frozenframework.adapter.BaseAdapter;
import lakeshire.github.com.frozenframework.view.LoadMoreListView;

/**
 * 列表Fragment基础类
 *
 * @author lakeshire
 */
public abstract class BaseListFragment<T> extends BasePullFragment implements AdapterView
        .OnItemClickListener {

    public static final int STATUES_OK = 0;
    public static final int STATUES_NETWORK_ERROR = 1;
    public static final int STATUES_NO_CONTENT = 2;
    public static final int STATUS_NO_MORE = 3;

    protected ListView mListView;
    protected List<T> mDataList = new ArrayList<>();
    protected BaseAdapter mAdapter;

    @Override
    public void loadData() {
        initData();
    }

    protected abstract void initData();

    @Override
    public int getContainerLayoutId() {
        return R.layout.fra_base_list;
    }

    @Override
    public void initUi() {
        super.initUi();
        mListView = findView(R.id.list);
        mAdapter = getAdapter();
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(this);
        if (mListView instanceof LoadMoreListView) {
            ((LoadMoreListView) mListView).setLoadMoreCallback(new LoadMoreListView.Callback() {
                @Override
                public void loadMore() {
                    loadMoreData();
                }

                @Override
                public void initFooter(View view) {
                    initListFooter(view);
                }
            });
        }
    }

    @Override
    public boolean checkCanRefresh(PtrFrameLayout frame, View content, View header) {
        ListView absListView = mListView;
        boolean canRefresh = !(absListView.getChildCount() > 0 && (absListView
                .getFirstVisiblePosition() > 0 || absListView.getChildAt(0).getTop() <
                absListView.getPaddingTop()));
        return canRefresh;
    }

    abstract protected BaseAdapter<T> getAdapter();

    protected void initListFooter(View view) {
//        ImageView ivAnim = (ImageView) view.findViewById(R.id.iv_anim);
//        FadingCircle cg = new FadingCircle();
//        int color = getResources().getColor(R.color.main_theme_color);
//        cg.setColor(color);
//        ivAnim.setBackgroundDrawable(cg);
//        cg.start();
    }

    abstract protected void loadMoreData();

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
                    mListView.setVisibility(View.VISIBLE);
                }

                if (mListView instanceof LoadMoreListView) {
                    switch (status) {
                        case STATUES_NETWORK_ERROR:
                            ((LoadMoreListView) mListView).onLoadMoreComplete(LoadMoreListView
                                    .STATUS_NETWORK_ERROR);
                            break;
                        case STATUES_NO_CONTENT:
                            ((LoadMoreListView) mListView).onLoadMoreComplete(LoadMoreListView
                                    .STATUS_NO_CONTENT);
                            break;
                        case STATUES_OK:
                            ((LoadMoreListView) mListView).onLoadMoreComplete(LoadMoreListView
                                    .STATUS_OK);
                            break;
                        case STATUS_NO_MORE:
                            ((LoadMoreListView) mListView).onLoadMoreComplete(LoadMoreListView
                                    .STATUS_NO_MORE);
                            break;
                    }
                }
            }
        });
    }

    @Override
    abstract public void onItemClick(AdapterView<?> parent, View view, int position, long id);

    @Override
    public void showNetworkErrorLayout() {
        super.showNetworkErrorLayout();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mListView.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void showNoContentLayout() {
        super.showNoContentLayout();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mListView.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void showLoadingLayout() {
        super.showLoadingLayout();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mListView.setVisibility(View.GONE);
            }
        });
    }
}
