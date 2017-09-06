package lakeshire.github.com.frozenframework.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;

import java.util.List;

import lakeshire.github.com.frozenframework.R;
import lakeshire.github.com.frozenframework.model.MenuItem;

public class MenuItemAdapter extends BaseAdapter<MenuItem> {

    public MenuItemAdapter(Context context, List<MenuItem> list, int res) {
        super(context, list, res);
    }

    @Override
    public void bindViewData(ViewHolder viewHolder, MenuItem item, int position) {
        viewHolder.setText(R.id.tv_title, item.getTitle());
        viewHolder.setImageResource(R.id.iv_pic, item.getPic());

        if (!TextUtils.isEmpty(item.getDetail())) {
            viewHolder.setText(R.id.tv_detail, item.getDetail());
        }

        View ivNew = viewHolder.getItemView(R.id.iv_new);
        if (ivNew != null) {
            if (item.isNeedUpdate()) {
                viewHolder.getItemView(R.id.iv_new).setVisibility(View.VISIBLE);
            } else {
                viewHolder.getItemView(R.id.iv_new).setVisibility(View.GONE);
            }
        }

        viewHolder.setVisibility(R.id.iv_arrow, item.isShowArrow());
    }
}