package com.github.lakeshire.frozen;

import android.content.Context;

import java.util.List;

import lakeshire.github.com.frozenframework.adapter.recyclerview.MultiItemTypeAdapter;
import lakeshire.github.com.frozenframework.adapter.recyclerview.base.ItemViewDelegate;
import lakeshire.github.com.frozenframework.adapter.recyclerview.base.ViewHolder;

/**
 * Created by louis.liu on 2017/9/7.
 */

public class ChatAdapter extends MultiItemTypeAdapter<ChatMessage>{


    public ChatAdapter(Context context, List<ChatMessage> datas) {
        super(context, datas);

        addItemViewDelegate(new MsgGoOutItemDelegate());
        addItemViewDelegate(new MsgComeInItemDelegate());
    }

    class MsgGoOutItemDelegate implements ItemViewDelegate<ChatMessage> {

        @Override
        public int getItemViewLayoutId() {
            return R.layout.item_go_out_msg;
        }

        @Override
        public boolean isForViewType(ChatMessage item, int position) {
            return !item.isComMeg();
        }

        @Override
        public void convert(ViewHolder holder, ChatMessage chatMessage, int position) {
            holder.setText(R.id.tv_content, chatMessage.getContent());
            holder.setText(R.id.tv_name, chatMessage.getName());
            holder.setImageResource(R.id.iv_icon, chatMessage.getIcon());
        }
    }

    class MsgComeInItemDelegate implements ItemViewDelegate<ChatMessage> {

        @Override
        public int getItemViewLayoutId() {
            return R.layout.item_come_in_msg;
        }

        @Override
        public boolean isForViewType(ChatMessage item, int position) {
            return item.isComMeg();
        }

        @Override
        public void convert(ViewHolder holder, ChatMessage chatMessage, int position) {
            holder.setText(R.id.tv_content, chatMessage.getContent());
            holder.setText(R.id.tv_name, chatMessage.getName());
            holder.setImageResource(R.id.iv_icon, chatMessage.getIcon());
        }
    }
}
