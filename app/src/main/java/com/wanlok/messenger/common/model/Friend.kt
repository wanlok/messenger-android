package com.wanlok.messenger.common.model

class Friend() {
    var name: String? = null
    var lastMessage: String? = null

    constructor(name: String, lastMessage: String): this() {
        this.name = name
        this.lastMessage = lastMessage
    }
}