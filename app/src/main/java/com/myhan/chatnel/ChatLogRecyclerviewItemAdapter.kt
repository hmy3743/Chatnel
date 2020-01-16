package com.myhan.chatnel

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.myhan.chatnel.model.ChatMessage
import kotlinx.android.synthetic.main.chat_log_recyclerview_item_outcome.view.*

class ChatLogRecyclerviewItemAdapter(
    private val context: Context,
    val logList: MutableList<ChatMessage>,
    private val selfUid: String
): RecyclerView.Adapter<ChatLogRecyclerviewItemAdapter.ChatLogRecyclerviewItemViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ChatLogRecyclerviewItemViewHolder {
        return ChatLogRecyclerviewItemViewHolder(
            if(viewType == 0)
                LayoutInflater.from(context).inflate(R.layout.chat_log_recyclerview_item_outcome, parent, false)
            else
                LayoutInflater.from(context).inflate(R.layout.chat_log_recyclerview_item_income, parent, false)
        )
    }

    override fun getItemViewType(position: Int): Int =
        if(logList[position].uid == selfUid) 0 else 1

    override fun getItemCount(): Int {
        return logList.count()
    }

    override fun onBindViewHolder(holder: ChatLogRecyclerviewItemViewHolder, position: Int) {
        holder.owner = logList[position].owner
        holder.content = logList[position].content
    }

    class ChatLogRecyclerviewItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var owner: String = ""
            set(value) {
                field = value
                itemView.owner.text = value
            }
        var content: String = ""
            set(value) {
                field = value
                itemView.content.text = value
            }
    }
}