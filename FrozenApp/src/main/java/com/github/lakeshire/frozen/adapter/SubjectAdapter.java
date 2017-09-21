package com.github.lakeshire.frozen.adapter;

import android.content.Context;

import com.github.lakeshire.frozen.R;

import java.util.List;

import lakeshire.github.com.frozenframework.adapter.BaseAdapter;
import lakeshire.github.com.frozenframework.adapter.ViewHolder;

/**
 * Created by louis.liu on 2017/9/5.
 */

public class SubjectAdapter extends BaseAdapter<String> {

    public SubjectAdapter(Context context, List<String> list, int resId) {
        super(context, list, resId);
    }

    @Override
    public void bindViewData(ViewHolder viewHolder, String item, int position) {
        viewHolder.setText(R.id.tv_title, item);
    }
}
