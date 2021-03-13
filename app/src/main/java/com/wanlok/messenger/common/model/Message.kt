package com.wanlok.messenger.common.model

import com.wanlok.messenger.common.JSON
import org.json.JSONObject

class Message(): JSON() {
    var sender: String? = null
    var content: String? = null

    constructor(jsonObject: JSONObject): this() {
        this.sender = string("sourceClientId", jsonObject)
        this.content = string("msgContent", jsonObject)
    }
}