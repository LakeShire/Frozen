package com.github.lakeshire.frozen

import android.content.Context
import android.widget.ImageView

import lakeshire.github.com.frozenframework.adapter.recyclerview.MultiItemTypeAdapter
import lakeshire.github.com.frozenframework.adapter.recyclerview.base.ItemViewDelegate
import lakeshire.github.com.frozenframework.adapter.recyclerview.base.ViewHolder
import lakeshire.github.com.frozenframework.util.loadImage

class ChatAdapter(context: Context, datas: List<ChatMessage>) : MultiItemTypeAdapter<ChatMessage>(context, datas) {


    init {
        addItemViewDelegate(MsgGoOutItemDelegate())
        addItemViewDelegate(MsgComeInItemDelegate())
    }

    internal inner class MsgGoOutItemDelegate : ItemViewDelegate<ChatMessage> {

        override fun getItemViewLayoutId(): Int {
            return R.layout.item_go_out_msg
        }

        override fun isForViewType(item: ChatMessage, position: Int): Boolean {
            return !item.isComMeg
        }

        override fun convert(holder: ViewHolder, chatMessage: ChatMessage, position: Int) {
            holder.setText(R.id.tv_content, chatMessage.content)
            holder.setText(R.id.tv_name, chatMessage.name)
//            holder.setImageResource(R.id.iv_icon, chatMessage.icon)
            holder.getView<ImageView>(R.id.iv_icon).loadImage(chatMessage.icon)
        }
    }

    internal inner class MsgComeInItemDelegate : ItemViewDelegate<ChatMessage> {

        override fun getItemViewLayoutId(): Int {
            return R.layout.item_come_in_msg
        }

        override fun isForViewType(item: ChatMessage, position: Int): Boolean {
            return item.isComMeg
        }

        override fun convert(holder: ViewHolder, chatMessage: ChatMessage, position: Int) {
            holder.setText(R.id.tv_content, chatMessage.content)
            holder.setText(R.id.tv_name, chatMessage.name)
            holder.setImageResource(R.id.iv_icon, chatMessage.icon)
        }
    }
}
