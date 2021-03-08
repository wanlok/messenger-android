package com.wanlok.messenger

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wanlok.messenger.common.model.Friend
import kotlinx.android.synthetic.main.item_friend.view.*

class FriendListAdapter(var friends : ArrayList<Friend>, var context: Context, var onItemViewClick: ((Friend) -> Unit)): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_friend, parent, false))
    }

    override fun getItemCount(): Int {
        return friends.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bind(friends[position])
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(friend: Friend) {
            itemView.tv_name.text = friend.name
            itemView.tv_last_message.text = friend.lastMessage
            itemView.setOnClickListener {
                onItemViewClick.invoke(friend)
            }
        }
    }
}