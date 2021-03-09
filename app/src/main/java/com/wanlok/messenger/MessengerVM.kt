package com.wanlok.messenger

import android.util.Log
import androidx.lifecycle.ViewModel
import com.wanlok.messenger.common.model.Friend

class MessengerVM: ViewModel() {
    var friend: Friend? = null

    fun reload() {

    }

    fun send(message: String) {
        Log.v("HELLO_WORLD", "" + message)
    }
}