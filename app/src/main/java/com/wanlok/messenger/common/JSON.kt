package com.wanlok.messenger.common

import org.json.JSONArray
import org.json.JSONObject

open class JSON {

    fun string(key: String, jsonObject: JSONObject): String? {
        return if (jsonObject.isNull(key))
            null
        else
            jsonObject.getString(key)
    }

    fun int(key: String, jsonObject: JSONObject): Int? {
        var value: Int?
        if (jsonObject.isNull(key)) {
            value = null
        } else {
            value = jsonObject.getInt(key)
        }
        return value
    }

    fun long(key: String, jsonObject: JSONObject): Long? {
        var value: Long?
        if (jsonObject.isNull(key)) {
            value = null
        } else {
            value = jsonObject.getLong(key)
        }
        return value
    }

    fun boolean(key: String, jsonObject: JSONObject): Boolean? {
        var value: Boolean?
        if (jsonObject.isNull(key)) {
            value = null
        } else {
            value = jsonObject.getBoolean(key)
        }
        return value
    }

    fun jsonObject(key: String, jsonObject: JSONObject): JSONObject? {
        var value: JSONObject?
        if (jsonObject.isNull(key)) {
            value = null
        } else {
            value = jsonObject.getJSONObject(key)
        }
        return value
    }

    fun jsonArray(key: String, jsonObject: JSONObject): JSONArray? {
        var value: JSONArray?
        if (jsonObject.isNull(key)) {
            value = null
        } else {
            value = jsonObject.getJSONArray(key)
        }
        return value
    }

    fun arrayListOfString(key: String, jsonObject: JSONObject): ArrayList<String>? {
        var value: ArrayList<String>?
        var jsonArray = jsonArray(key, jsonObject)
        if (jsonArray == null) {
            value = null
        } else {
            value = ArrayList()
            for (i in 0 until jsonArray.length()) {
                value.add(jsonArray.getString(i))
            }
        }
        return value
    }
}