package com.wanlok.messenger

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wanlok.messenger.common.model.Message
import kotlinx.android.synthetic.main.item_friend.view.tv_name
import kotlinx.android.synthetic.main.item_message.view.*

class MessengerAdapter(var messages : ArrayList<Message>, var context: Context): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_message, parent, false))
    }

    override fun getItemCount(): Int {
        return messages.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bind(messages[position])
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(message: Message) {
            itemView.tv_name.text = message.sender
            itemView.tv_message.text = message.content
        }
    }
}