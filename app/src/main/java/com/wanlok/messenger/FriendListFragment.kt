package com.wanlok.messenger

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.wanlok.messenger.common.WFragment
import com.wanlok.messenger.common.model.Friend
import kotlinx.android.synthetic.main.fragment_friend_list.*

class FriendListFragment: WFragment() {
    val friendListVM = FriendListVM()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_friend_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rv_friend_list.layoutManager = LinearLayoutManager(context)

        friendListVM.friends.observe(this, Observer<ArrayList<Friend>> { friends ->
            context?.let { context ->
                rv_friend_list.adapter = FriendListAdapter(friends, context,
                    { friend -> onFriendItemViewClick(friend) })
            }
        })

        title("Friends")

        friendListVM.reload()
    }

    fun onFriendItemViewClick(friend: Friend) {
        val messengerFragment = MessengerFragment()
        messengerFragment.messengerVM.friend = friend
        push(messengerFragment)
    }
}