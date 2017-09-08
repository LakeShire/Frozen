package lakeshire.github.com.frozenframework.adapter.recyclerview.common;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.List;

import lakeshire.github.com.frozenframework.R;
import lakeshire.github.com.frozenframework.adapter.recyclerview.CommonAdapter;
import lakeshire.github.com.frozenframework.adapter.recyclerview.MultiItemTypeAdapter;
import lakeshire.github.com.frozenframework.adapter.recyclerview.base.ItemViewDelegate;
import lakeshire.github.com.frozenframework.adapter.recyclerview.base.ViewHolder;
import lakeshire.github.com.frozenframework.fragment.IInnerScrollable;
import lakeshire.github.com.frozenframework.util.ScreenUtil;

/**
 * 开袋即食
 * 多类型Adapter模板
 *
 * @author lakeshire
 */
public class MultiInfoAdapter extends MultiItemTypeAdapter<MultiInfo> implements IInnerScrollable {
    private boolean mInnerScrolling = false;

    public MultiInfoAdapter(Context context, List<MultiInfo> data) {
        super(context, data);

        addItemViewDelegate(new SingleImageDelegate());
        addItemViewDelegate(new MultiImageDelegate());
        addItemViewDelegate(new TextAndImageDelegate());
        addItemViewDelegate(new TextDelegate());
    }

    @Override
    public boolean isInnerScrolling() {
        return mInnerScrolling;
    }

    class SingleImageDelegate implements ItemViewDelegate<MultiInfo> {

        @Override
        public int getItemViewLayoutId() {
            return R.layout.item_single_image;
        }

        @Override
        public boolean isForViewType(MultiInfo item, int position) {
            return item.type == MultiInfo.TYPE_SINGLE_IMAGE;
        }

        @Override
        public void convert(ViewHolder holder, MultiInfo info, int position) {
            int width = ScreenUtil.getScreenWidth(mContext);
            int height = (int) (width / 2.5f);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(width, height);
            lp.topMargin = 8;
            lp.bottomMargin = 8;
            ImageView iv = holder.getView(R.id.iv_image);
            iv.setLayoutParams(lp);
            iv.setImageResource(info.images.get(0));
        }
    }

    class MultiImageDelegate implements ItemViewDelegate<MultiInfo> {

        @Override
        public int getItemViewLayoutId() {
            return R.layout.item_multi_image;
        }

        @Override
        public boolean isForViewType(MultiInfo item, int position) {
            return item.type == MultiInfo.TYPE_MULTI_IMAGE;
        }

        @Override
        public void convert(ViewHolder holder, final MultiInfo info, int position) {
            RecyclerView list =  holder.getView(R.id.list);
            LinearLayoutManager lm = new LinearLayoutManager(mContext, LinearLayoutManager
                    .HORIZONTAL, false);
            list.setLayoutManager(lm);
            list.setOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);
                    mInnerScrolling = (newState != RecyclerView.SCROLL_STATE_IDLE);
                }
            });
            list.setAdapter(new GalleryAdapter(mContext, R.layout.item_gallery_image, info.images));
        }
    }

    class GalleryAdapter extends CommonAdapter<Integer> {
        public GalleryAdapter(Context context, int layoutId, List<Integer> datas) {
            super(context, layoutId, datas);
        }

        @Override
        protected void convert(ViewHolder holder, Integer integer, int position) {
            ImageView iv = holder.getView(R.id.iv_image);
            iv.setImageResource(integer);
        }
    }

    class TextAndImageDelegate implements ItemViewDelegate<MultiInfo> {

        @Override
        public int getItemViewLayoutId() {
            return R.layout.item_image_text;
        }

        @Override
        public boolean isForViewType(MultiInfo item, int position) {
            return item.type == MultiInfo.TYPE_TEXT_AND_IMAGE;
        }

        @Override
        public void convert(ViewHolder holder, MultiInfo info, int position) {
            holder.setImageResource(R.id.iv_image, info.images.get(0));
            holder.setText(R.id.tv_title, info.title);
            holder.setText(R.id.tv_description, info.description);
        }
    }

    class TextDelegate implements ItemViewDelegate<MultiInfo> {

        @Override
        public int getItemViewLayoutId() {
            return R.layout.item_only_text;
        }

        @Override
        public boolean isForViewType(MultiInfo item, int position) {
            return item.type == MultiInfo.TYPE_TEXT;
        }

        @Override
        public void convert(ViewHolder holder, MultiInfo info, int position) {
            holder.setText(R.id.tv_title, info.title);
            holder.setText(R.id.tv_description, info.description);
        }
    }
}
