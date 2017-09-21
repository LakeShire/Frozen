package com.github.lakeshire.frozen.adapter

import android.content.Context
import android.widget.TextView
import com.github.lakeshire.frozen.R
import lakeshire.github.com.frozenframework.adapter.recyclerview.CommonAdapter
import lakeshire.github.com.frozenframework.adapter.recyclerview.base.ViewHolder

/**
 * Created by louis.liu on 2017/9/13.
 */
class TitleAdapter(context: Context?, layoutId: Int, datas: MutableList<String>?) : CommonAdapter<String>(context, layoutId, datas) {

    override fun convert(holder: ViewHolder?, t: String?, position: Int) {
        holder?.getView<TextView>(R.id.tv_title)?.text = t
    }
}