package com.wanlok.messenger

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.wanlok.messenger.common.WFragment
import kotlinx.android.synthetic.main.fragment_messenger.*

class MessengerFragment: WFragment() {
    val messengerVM = MessengerVM()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_messenger, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rv_messages.layoutManager = LinearLayoutManager(context)

        messengerVM.friend?.let { friend ->
            friend.name?.let { name ->
                title(name)
            }
        }

        messengerVM.connect(activity)

        messengerVM.reloaded.observe(this, Observer { reloaded ->
            context?.let { context ->
                rv_messages.adapter = MessengerAdapter(messengerVM.messages, context)
                rv_messages.scrollToPosition(messengerVM.messages.size - 1)
            }
        })

        b_message.setOnClickListener {
            messengerVM.send(activity as Activity, et_message.text.toString())
            et_message.setText("")
        }
    }
}