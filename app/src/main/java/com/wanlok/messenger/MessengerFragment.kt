package com.wanlok.messenger

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wanlok.messenger.common.WFragment
import kotlinx.android.synthetic.main.fragment_messenger.*

class MessengerFragment: WFragment() {
    val messengerVM = MessengerVM()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_messenger, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        messengerVM.friend?.let { friend ->
            friend.name?.let { name ->
                title(name)
            }
        }

        b_message.setOnClickListener {
            messengerVM.send(et_message.text.toString())
            et_message.setText("")
        }
    }
}