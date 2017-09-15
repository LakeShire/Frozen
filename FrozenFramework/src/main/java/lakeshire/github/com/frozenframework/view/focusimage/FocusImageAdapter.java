package lakeshire.github.com.frozenframework.view.focusimage;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import lakeshire.github.com.frozenframework.manager.image.Image;
import lakeshire.github.com.frozenframework.manager.image.ImageLoader;
import lakeshire.github.com.frozenframework.util.ScreenUtil;

/**
 * @author chadwii
 * @Date 2014-3-11 上午11:36:01
 * @see
 * @since Ver 1.1
 */
public class FocusImageAdapter extends PagerAdapter {
    private ArrayList<ImageView> mFocusImageView = new ArrayList<>();
    private List<Banner> mFocusImages;
    private boolean isCycleScroll = false;
    private Context mContext;

    public FocusImageAdapter(Context context, List<Banner> list) {
        mFocusImages = list;
        mContext = context;
        buildPages();
    }

    public void setCycleScrollFlag(boolean isCycleScroll) {
        this.isCycleScroll = isCycleScroll;
    }

    public void setList(List<Banner> list) {
        if (list == null) {
            return;
        }

        if (mFocusImages == null) {
            mFocusImages = list;
        } else {
            mFocusImages.clear();
            mFocusImages.addAll(list);
        }
    }

    public List<Banner> getBanners() {
        return mFocusImages;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        if (mFocusImageView == null || mFocusImageView.size() == 0) {
            return;
        }

        if (isCycleScroll) {
            position = position % mFocusImageView.size();
        }
        ImageView iv = mFocusImageView.get(position);
        if (iv.getDrawable() != null) {
            iv.getDrawable().setCallback(null);
        }
        iv.setImageBitmap(null);
        iv.setTag(null);
        container.removeView(iv);
    }

    @Override
    public int getCount() {
        if (mFocusImageView.size() != 1 && isCycleScroll) {
            return mFocusImageView.size() != 0 ? Integer.MAX_VALUE : 0;
        } else {
            return mFocusImageView.size();
        }

    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        final int pos;
        if (mFocusImageView.size() != 0 && isCycleScroll) {
            position = position % mFocusImageView.size();
            pos = position;
        } else {
            pos = position;
        }
        ImageView iv;
        if (position < mFocusImageView.size()) {
            iv = mFocusImageView.get(position);
            if (mFocusImages == null || mFocusImages.size() <= 0 || position >= mFocusImages.size
                    ()) {
                container.addView(iv);
                return iv;
            }
        } else {
            iv = new ImageView(mContext);
            iv.setScaleType(ScaleType.FIT_XY);
            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams
                    .MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            iv.setLayoutParams(lp);
            mFocusImageView.add(iv);
        }

        if (position < 0 || position >= mFocusImages.size()) {
            position = 0;
        }

        String url = mFocusImages.get(position).url;
        if (!TextUtils.isEmpty(url)) {
            ImageLoader.getInstance().loadImageOrigin(mContext, new Image(url), iv);
        } else {
            int res = mFocusImages.get(pos).res;
            if (res > 0) {
                ImageLoader.getInstance().loadImageOrigin(mContext, new Image(res), iv);
            }
        }

        if (iv.getParent() != null && iv.getParent() instanceof ViewGroup) {
            ((ViewGroup) iv.getParent()).removeView(iv);
        }
        container.addView(iv);
        return iv;
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override
    public int getItemPosition(Object object) {

        return POSITION_NONE;
    }

    @Override
    public void notifyDataSetChanged() {
        buildPages();
        super.notifyDataSetChanged();

    }

    public void buildPages() {
        int width = ScreenUtil.getScreenWidth(mContext);
        int resize = mFocusImages.size() - mFocusImageView.size();
        if (resize > 0) {
            for (int i = 0; i < resize; i++) {
                ImageView ti = new ImageView(mContext);

                int height = (int) (width / 2.5f);
                ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(width, height);
                ti.setLayoutParams(lp);
                mFocusImageView.add(ti);
            }
        } else if (resize < 0) {
            for (int i = 0; i < Math.abs(resize); i++) {
                if (mFocusImageView.size() > 0) {
                    ImageView imgView = mFocusImageView.remove(0);
                    if (imgView.getParent() != null) {
                        ((ViewGroup) imgView.getParent()).removeView(imgView);
                    }
                }
            }
        }
    }

    public void destroy() {
        if (mFocusImageView != null && mFocusImageView.size() > 0) {
            Iterator<ImageView> iterator = mFocusImageView.iterator();
            while (iterator.hasNext()) {
                ImageView imgView = iterator.next();
                iterator.remove();
                imgView.setImageDrawable(null);
            }
        }
    }
}
