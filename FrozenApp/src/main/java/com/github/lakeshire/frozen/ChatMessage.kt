package com.github.lakeshire.frozen

class ChatMessage(var icon: Int, var name: String?, var content: String?,
                  var createDate: String?, var isComMeg: Boolean) {

    override fun toString(): String {
        return "ChatMessage [icon=$icon, name=$name, content=$content, createDate=$createDate, isComing=$isComMeg]"
    }

    companion object {
        val RECIEVE_MSG = 0
        val SEND_MSG = 1
    }
}