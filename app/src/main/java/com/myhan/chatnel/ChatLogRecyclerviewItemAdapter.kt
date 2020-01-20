package com.myhan.chatnel

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.myhan.chatnel.model.ChatMessage
import kotlinx.android.synthetic.main.chat_log_recyclerview_item_from_out.view.*
import kotlinx.android.synthetic.main.chat_log_recyclerview_item_from_me.view.*
import kotlinx.android.synthetic.main.chat_log_recyclerview_item_from_me.view.owner

class ChatLogRecyclerviewItemAdapter(
    private val context: Context,
    val logList: MutableList<ChatMessage>,
    private val selfUid: String
): RecyclerView.Adapter<ChatLogRecyclerviewItemAdapter.ChatLogRecyclerviewItemViewHolder>() {

    companion object {
        val MESSAGE_FROM_ME = 0
        val MESSAGE_FROM_OUT = 1
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ChatLogRecyclerviewItemViewHolder {
        return ChatLogRecyclerviewItemViewHolder(
            when(viewType) {
                MESSAGE_FROM_ME -> LayoutInflater.from(context).inflate(
                    R.layout.chat_log_recyclerview_item_from_me,
                    parent,
                    false
                )
                MESSAGE_FROM_OUT -> LayoutInflater
                    .from(context).inflate(
                    R.layout.chat_log_recyclerview_item_from_out,
                    parent,
                    false
                )
                else -> throw Exception()
            }, viewType
        )
    }

    override fun getItemViewType(position: Int): Int =
        if(logList[position].uid == selfUid) MESSAGE_FROM_ME else MESSAGE_FROM_OUT

    override fun getItemCount(): Int {
        return logList.count()
    }

    override fun onBindViewHolder(holder: ChatLogRecyclerviewItemViewHolder, position: Int) {
        holder.owner = logList[position].owner
        holder.content = logList[position].content
    }

    class ChatLogRecyclerviewItemViewHolder(itemView: View, private val viewType: Int) : RecyclerView.ViewHolder(itemView) {
        var owner: String = ""
            set(value) {
                field = value
                itemView.owner.text = value
            }
        var content: String = ""
            set(value) {
                field = value
                when(viewType){
                    MESSAGE_FROM_OUT -> itemView.contentFromOut.text = field
                    MESSAGE_FROM_ME -> itemView.contentFromMe.text = field
                }
            }
    }
}