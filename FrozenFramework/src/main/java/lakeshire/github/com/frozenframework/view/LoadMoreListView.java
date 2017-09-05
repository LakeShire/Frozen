package lakeshire.github.com.frozenframework.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import lakeshire.github.com.frozenframework.R;

/**
 * 可加载更多的ListView
 *
 * @author lakeshire
 */
public class LoadMoreListView extends ListView {

    private Context mContext;
    private View mFooterView;
    private boolean isLoading = false;
    private boolean isLastLine = false;
    private TextView mTvHint;
    private ImageView mIvLoading;
    private int mStatus;
    private ProgressBar mProgress;
    private boolean useLoadingImage = false;

    public interface Callback {
        void loadMore();

        void initFooter(View view);
    }

    private Callback mCallback;

    public void setLoadMoreCallback(Callback cb) {
        this.mCallback = cb;
    }

    public LoadMoreListView(Context context) {
        super(context);
        this.mContext = context;
        initView();
        setListener();
    }

    public LoadMoreListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initView();
        setListener();
    }

    public LoadMoreListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        initView();
        setListener();
    }

    public void initView() {
        mFooterView = View.inflate(mContext, R.layout.layout_list_footer, null);
        mFooterView.setVisibility(INVISIBLE);
        mTvHint = (TextView) mFooterView.findViewById(R.id.tv_hint);
        mIvLoading = (ImageView) mFooterView.findViewById(R.id.iv_anim);
        mProgress = (ProgressBar) mFooterView.findViewById(R.id.progress);
        addFooterView(mFooterView);
    }

    private void setListener() {
        setOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == OnScrollListener.SCROLL_STATE_IDLE && !isLoading && isLastLine
                        && mStatus != STATUS_NO_MORE) {
                    load();
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount,
                                 int totalItemCount) {
                isLastLine = (firstVisibleItem + visibleItemCount == totalItemCount);
            }
        });

        mFooterView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                load();
            }
        });
    }

    private void load() {
        mFooterView.setVisibility(VISIBLE);
        mIvLoading.setVisibility(VISIBLE);
        mTvHint.setVisibility(INVISIBLE);
        isLoading = true;
        if (mCallback != null) {
            mCallback.initFooter(mFooterView);
            mCallback.loadMore();
        }
    }

    public static final int STATUS_OK = 0;
    public static final int STATUS_NETWORK_ERROR = 1;
    public static final int STATUS_NO_CONTENT = 2;
    public static final int STATUS_LOADING = 3;
    public static final int STATUS_NO_MORE = 4;

    public void onLoadMoreComplete(final int status) {
        this.isLoading = false;
        this.mStatus = status;
        post(new Runnable() {
            @Override
            public void run() {
                switch (status) {
                    case STATUS_LOADING:
                        if (useLoadingImage) {
                            mIvLoading.setVisibility(VISIBLE);
                        } else {
                            mProgress.setVisibility(VISIBLE);
                        }
                        mTvHint.setVisibility(INVISIBLE);
                        break;
                    case STATUS_NETWORK_ERROR:
                        mIvLoading.setVisibility(INVISIBLE);
                        mProgress.setVisibility(INVISIBLE);
                        mTvHint.setVisibility(VISIBLE);
                        mTvHint.setText("网络出错");
                        break;
                    case STATUS_NO_CONTENT:
                        mIvLoading.setVisibility(INVISIBLE);
                        mProgress.setVisibility(INVISIBLE);
                        mTvHint.setVisibility(VISIBLE);
                        mTvHint.setText("没有获得数据");
                        break;
                    case STATUS_NO_MORE:
                        mIvLoading.setVisibility(INVISIBLE);
                        mProgress.setVisibility(INVISIBLE);
                        mTvHint.setVisibility(VISIBLE);
                        mTvHint.setText("没有更多数据");
                        break;
                    case STATUS_OK:
                    default:
                        mFooterView.setVisibility(INVISIBLE);
                }
            }
        });
    }

    public void resetStatus() {
        this.mStatus = STATUS_OK;
        mFooterView.setVisibility(INVISIBLE);
    }
}
