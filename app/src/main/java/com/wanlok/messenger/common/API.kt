package com.wanlok.messenger.common

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject


class API(var context: Context) {
    private var requestQueue: RequestQueue? = null

    companion object {
        @Volatile
        private var instance: API? = null

        fun init(context: Context) {
            if (instance == null) {
                instance = API(context)
                instance?.requestQueue = Volley.newRequestQueue(context)
            }
        }

        fun instance(): API {
            return instance!!
        }
    }

    fun get(url: String, success: ((JSONObject)->Unit), failed: ((VolleyError)->Unit)) {
        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null, Response.Listener { response ->
            success(response)
//            try {
//                val jsonArray = response.getJSONArray("data")
//                for (i in 0 until jsonArray.length()) {
//                    val jsonObject = jsonArray.getJSONObject(i)
//                    val email = jsonObject.getString("email")
//                    jsonResponses.add(email)
//                }
//            } catch (e: JSONException) {
//                e.printStackTrace()
//            }
        }, Response.ErrorListener { error -> failed(error) })
        requestQueue?.add(jsonObjectRequest)
    }
}
