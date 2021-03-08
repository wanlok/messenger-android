package com.wanlok.messenger

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wanlok.messenger.common.model.Friend

class FriendListVM: ViewModel() {

    val friends = MutableLiveData<ArrayList<Friend>>()

    fun reload() {
        var friend1 = Friend("Peter", "Hello World")
        var friend2 = Friend("Jack Chan", "I see!")
        friends.value = arrayListOf(friend1, friend2)
    }
}