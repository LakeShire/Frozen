package lakeshire.github.com.frozenframework.view.focusimage;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import lakeshire.github.com.frozenframework.R;
import lakeshire.github.com.frozenframework.util.ViewUtil;

public class FocusImageView extends RelativeLayout {
    private static final long SWAP_AD_INTERNAL = 5000;
    private ViewPagerInScroll mFocusPager;
    private CirclePageIndicator mFocusIndicator;
    private FocusImageAdapter mFocusAdapter;
    private List<Banner> mFocusImages;
    private ViewGroup mFocusImageRoot;
    private Context mContext;
    private boolean isFakeData = false;
    private int mFocusIndex = 0;
    private boolean isFocusTouch = false;
    private final Runnable mAutoSwapRunnable = new Runnable() {

        @Override
        public void run() {
            stopAutoSwapFocusImage();
            if (mFocusPager.getVisibility() == View.VISIBLE && mFocusAdapter != null
                    && mFocusAdapter.getCount() > 0 && !isFocusTouch) {
                mFocusPager.setCurrentItem(mFocusIndex++);
                if (mFocusIndex >= mFocusAdapter.getCount()) {
                    mFocusIndex = 0;
                }
            }
            postDelayed(this, SWAP_AD_INTERNAL);
        }
    };
    private List<ViewPager.OnPageChangeListener> pageChangeListeners;
    private boolean canRefresh = true;

    public FocusImageView(Context context) {
        super(context);
        initView(context);
    }

    public FocusImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public FocusImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    public void setTouchCallback(ViewPagerInScroll.TouchCallback callback) {
        mFocusPager.setTouchCallback(callback);
    }

    private void initView(Context context) {
        mContext = context;
        mFocusImageRoot = (ViewGroup) LayoutInflater.from(context).inflate(R.layout
                .view_focus_image_merge, this, true);

        mFocusPager = (ViewPagerInScroll) findViewById(R.id.main_pager);
        mFocusIndicator = (CirclePageIndicator) findViewById(R.id.main_indicator_dot);

//        mFocusPager.setDisallowInterceptTouchEventView(
//                (ViewGroup) mFocusPager.getParent(), false);
        FixedSpeedScroller mScroller = new FixedSpeedScroller(
                mFocusPager.getContext(), new DecelerateInterpolator());
        ViewUtil.setViewPagerScroller(mFocusPager, mScroller);

        //  解决轮播与刷新框架的冲突 触摸轮播时禁用刷新
        setTouchCallback(new ViewPagerInScroll.TouchCallback() {
            @Override
            public void onDown() {
                canRefresh = false;
            }

            @Override
            public void onUp() {
                canRefresh = true;
            }
        });
    }

    public FocusImageAdapter getAdapter() {
        return mFocusAdapter;
    }

    public void setDisallowInterceptTouchEventView(ViewGroup view) {
        mFocusPager.setDisallowInterceptTouchEventView(view, false);
    }

    public void setAdapter(FocusImageAdapter adapter) {
        if (adapter != null) {
            mFocusAdapter = adapter;
            mFocusImages = mFocusAdapter.getBanners();
            isFakeData = false;

            if (mFocusImages != null) {
                int size = mFocusImages.size();
                if (size == 1) {
                    mFocusAdapter.setCycleScrollFlag(false);
                } else if (size > 1) {
                    mFocusAdapter.setCycleScrollFlag(true);
                }

                if (size == 2 || size == 3) {
                    mFocusImages.addAll(new ArrayList<Banner>(mFocusImages));
                    isFakeData = true;
                }
            } else {
                mFocusAdapter.setCycleScrollFlag(false);
            }

            mFocusPager.setAdapter(mFocusAdapter);

            mFocusIndicator.setViewPager(mFocusPager);

            mFocusIndicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageSelected(int arg0) {
                    mFocusIndex = arg0;

                    if (mFocusImages != null && mFocusImageRoot != null) {
                        int postion = arg0 % mFocusImages.size();
                        if (mFocusImages.size() > postion) {
                            Banner bannerM = mFocusImages.get(postion);
                        }
                    }

                    if (pageChangeListeners != null) {
                        for (ViewPager.OnPageChangeListener pageListener : pageChangeListeners) {
                            pageListener.onPageSelected(arg0);
                        }
                    }
                }

                @Override
                public void onPageScrolled(int arg0, float arg1, int arg2) {
                    if (pageChangeListeners != null) {
                        for (ViewPager.OnPageChangeListener pageListener : pageChangeListeners) {
                            pageListener.onPageScrolled(arg0, arg1, arg2);
                        }
                    }
                }

                @Override
                public void onPageScrollStateChanged(int arg0) {
                    if (pageChangeListeners != null) {
                        for (ViewPager.OnPageChangeListener pageListener : pageChangeListeners) {
                            pageListener.onPageScrollStateChanged(arg0);
                        }
                    }
                }
            });

            if (isFakeData) {
                mFocusIndicator.setPagerRealCount(mFocusImages.size() / 2);
            } else {
                mFocusIndicator.setPagerRealCount(mFocusImages.size());
            }

            mFocusAdapter.notifyDataSetChanged();
            if (mFocusIndex == 0 && mFocusImages.size() > 1) {
                mFocusPager.setCurrentItem(Integer.MAX_VALUE / 2
                        - (Integer.MAX_VALUE / 2) % mFocusImages.size()); // 初始时ViewPager设置在中间位置
            } else {
                mFocusPager.setCurrentItem(mFocusIndex);
            }

            mFocusPager.setOnTouchListener(new OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {

                    switch (event.getAction()) {
                        case MotionEvent.ACTION_MOVE:
                            isFocusTouch = true;
                            break;
                        case MotionEvent.ACTION_UP:
                            isFocusTouch = false;
                            break;
                        default:
                            break;
                    }
                    return false;
                }
            });

            if (mFocusAdapter.getCount() > 1) {
                mFocusIndicator.setVisibility(View.VISIBLE);
            } else {
                mFocusIndicator.setVisibility(View.GONE);
            }
        }
    }

    public void stopAutoSwapFocusImage() {
        removeCallbacks(mAutoSwapRunnable);
    }

    public void startAutoSwapFocusImage() {
        postDelayed(mAutoSwapRunnable, SWAP_AD_INTERNAL);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        startAutoSwapFocusImage();
    }

    @Override
    protected void onDetachedFromWindow() {
        stopAutoSwapFocusImage();
        super.onDetachedFromWindow();
    }

    public void release() {
        removeAllListener();
        if (mFocusAdapter != null) {
            mFocusAdapter.destroy();
        }
    }

    public void addOnPageChangeListener(ViewPager.OnPageChangeListener onPageChangeListener) {
        if (pageChangeListeners == null) {
            pageChangeListeners = new ArrayList<>();
        }
        if (!pageChangeListeners.contains(onPageChangeListener)) {
            pageChangeListeners.add(onPageChangeListener);
        }
    }

    public void removeOnPageChangeListener(ViewPager.OnPageChangeListener onPageChangeListener) {
        if (pageChangeListeners != null) {
            pageChangeListeners.remove(onPageChangeListener);
        }
    }

    public void removeAllListener() {
        if (pageChangeListeners != null) {
            pageChangeListeners.clear();
        }
    }

    /**
     * 外部是否可刷新
     */
    public boolean canOutsideRefresh() {
        return canRefresh;
    }
}
