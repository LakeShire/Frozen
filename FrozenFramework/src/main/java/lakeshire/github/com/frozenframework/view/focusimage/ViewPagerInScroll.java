package lakeshire.github.com.frozenframework.view.focusimage;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;

public class ViewPagerInScroll extends ViewPager {

    private ViewGroup mDisallowInterceptTouchEventView;
    private boolean mAllowParentViewScrolling = false;
    private int mLastXIntercept;
    private int mLastYIntercept;
    private Context mContext;
    private TouchCallback mTouchCallback;

    public interface TouchCallback {
        void onDown();

        void onUp();
    }

    public ViewPagerInScroll(Context context) {
        super(context);
        this.mContext = context;
    }

    public ViewPagerInScroll(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
    }

    public void setTouchCallback(TouchCallback callback) {
        mTouchCallback = callback;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                if (mTouchCallback != null) {
                    mTouchCallback.onDown();
                }
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                if (mTouchCallback != null) {
                    mTouchCallback.onUp();
                }
                break;
        }
        return super.onTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        try {
            if (mDisallowInterceptTouchEventView != null) {
                if (mAllowParentViewScrolling) {
                    int x = (int) ev.getX();
                    int y = (int) ev.getY();
                    switch (ev.getAction()) {
                        case MotionEvent.ACTION_DOWN: {
                            mDisallowInterceptTouchEventView
                                    .requestDisallowInterceptTouchEvent(true);
                        }
                        break;
                        case MotionEvent.ACTION_MOVE: {
                            int deltaX = x - mLastXIntercept;
                            int deltaY = y - mLastYIntercept;
                            if (Math.abs(deltaX) < Math.abs(deltaY)) {
                                mDisallowInterceptTouchEventView
                                        .requestDisallowInterceptTouchEvent(false);
                            }
                        }
                        break;
                        case MotionEvent.ACTION_UP: {
                            break;
                        }

                        default:
                            break;
                    }
                    mLastXIntercept = x;
                    mLastYIntercept = y;
                } else {
                    int action = ev.getAction();
                    switch (action) {
                        case MotionEvent.ACTION_DOWN:
                        case MotionEvent.ACTION_MOVE:
                            if (mTouchCallback != null) {
                                mTouchCallback.onDown();
                            }
                            mDisallowInterceptTouchEventView
                                    .requestDisallowInterceptTouchEvent(true);
                            break;
                        case MotionEvent.ACTION_CANCEL:
                        case MotionEvent.ACTION_UP:
                            if (mTouchCallback != null) {
                                mTouchCallback.onUp();
                            }
                            mDisallowInterceptTouchEventView
                                    .requestDisallowInterceptTouchEvent(false);
                            break;
                    }
                }

            }
            return super.onInterceptTouchEvent(ev);
        } catch (Exception e) {
        }
        http:
//www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2015/0501/2822.html*/
        super.onInterceptTouchEvent(ev);
        return false;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    public void setDisallowInterceptTouchEventView(ViewGroup view) {
        mDisallowInterceptTouchEventView = view;
        mAllowParentViewScrolling = false;
    }

    public void setDisallowInterceptTouchEventView(ViewGroup view,
                                                   boolean allowParentViewScrolling) {
        mDisallowInterceptTouchEventView = view;
        mAllowParentViewScrolling = allowParentViewScrolling;
    }
}
