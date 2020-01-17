package com.myhan.chatnel

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.myhan.chatnel.model.ChatMessage
import kotlinx.android.synthetic.main.chat_log_recyclerview_item_income.view.*
import kotlinx.android.synthetic.main.chat_log_recyclerview_item_outcome.view.*
import kotlinx.android.synthetic.main.chat_log_recyclerview_item_outcome.view.owner

class ChatLogRecyclerviewItemAdapter(
    private val context: Context,
    val logList: MutableList<ChatMessage>,
    private val selfUid: String
): RecyclerView.Adapter<ChatLogRecyclerviewItemAdapter.ChatLogRecyclerviewItemViewHolder>() {

    companion object {
        val OUTCOME = 0
        val INCOME = 1
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ChatLogRecyclerviewItemViewHolder {
        return ChatLogRecyclerviewItemViewHolder(
            when(viewType) {
                OUTCOME -> LayoutInflater.from(context).inflate(
                    R.layout.chat_log_recyclerview_item_outcome,
                    parent,
                    false
                )
                INCOME -> LayoutInflater
                    .from(context).inflate(
                    R.layout.chat_log_recyclerview_item_income,
                    parent,
                    false
                )
                else -> throw Exception()
            }, viewType
        )
    }

    override fun getItemViewType(position: Int): Int =
        if(logList[position].uid == selfUid) INCOME else OUTCOME

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
                    INCOME -> itemView.contentIncome.text = field
                    OUTCOME -> itemView.contentOutCome.text = field
                }
            }
    }
}