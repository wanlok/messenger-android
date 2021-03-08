package com.wanlok.messenger

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.wanlok.messenger.common.model.Friend
import kotlinx.android.synthetic.main.fragment_friend_list.*

class FriendListFragment: Fragment() {

    val friendListVM = FriendListVM()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
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

        friendListVM.reload()
    }

    fun onFriendItemViewClick(friend: Friend) {
        Log.v("HELLO_WORLD", "friend " + friend.name)
        (activity as? Activity)?.push(MessengerFragment())

//        childFragmentManager?.let {
//            val fragmentTransaction = it.beginTransaction()
//            fragmentTransaction.add(R.id.ll_content, MessengerFragment())
//            fragmentTransaction.commit()
//        }
    }
}