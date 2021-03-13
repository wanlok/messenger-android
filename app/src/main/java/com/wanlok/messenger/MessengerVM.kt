package com.wanlok.messenger

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wanlok.messenger.common.Constants
import com.wanlok.messenger.common.model.Friend
import com.wanlok.messenger.common.model.Message
import kotlinx.android.synthetic.main.fragment_messenger.*
import org.json.JSONObject

class MessengerVM: ViewModel() {
    var friend: Friend? = null
    var messages = ArrayList<Message>()
    var reloaded = MutableLiveData<Boolean>()

    fun connect(activity: FragmentActivity?) {
        (activity as Activity).socket?.on("messageevent") { o ->
            activity.runOnUiThread {
                if (o.size > 0) {
                    Log.v("HELLO_WORLD", "" + (o[0] as JSONObject))
                    messages.add(Message(o[0] as JSONObject))
                }
                reloaded.value = true
            }
        }
    }

    fun send(activity: FragmentActivity?, message: String) {
        val activity = activity as? Activity
        val jsonObject = JSONObject()
        jsonObject.put("sourceClientId", activity?.sharedPreferences?.getLong(Constants.SHARED_PREFERENCES.USER_ID, 0))
//        jsonObject.put("targetClientId", "testclient2")
        jsonObject.put("msgType", "chat")
        jsonObject.put("msgContent", message)
        activity?.socket?.emit("messageevent", jsonObject)
    }
}