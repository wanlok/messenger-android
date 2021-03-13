package com.wanlok.messenger

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.core.view.GravityCompat
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem
import com.mikepenz.materialdrawer.model.interfaces.nameText
import com.wanlok.messenger.common.API
import com.wanlok.messenger.common.Constants
import com.wanlok.messenger.common.WActivity
import com.wanlok.messenger.common.WNavigationFragment
import io.socket.client.IO
import io.socket.client.Socket
import kotlinx.android.synthetic.main.activity.*

class Activity: WActivity() {

    var socket: Socket? = null

    var sharedPreferences: SharedPreferences? = null

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        currentNavigationFragment?.let { navigationFragment ->
            if (navigationFragment.isRoot()) {
                if (item.itemId == android.R.id.home) {
                    slider.drawerLayout?.let { drawerLayout ->
                        if (drawerLayout.isOpen) {
                            drawerLayout.closeDrawer(GravityCompat.START)
                        } else {
                            drawerLayout.openDrawer(GravityCompat.START)
                        }
                    }
                    return true
                }
            } else {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity)

        navigationFragments.add(WNavigationFragment(MessengerFragment()))
        navigationFragments.add(WNavigationFragment(RegistrationFragment()))

        API.init(applicationContext)

        val sharedPreferences = getSharedPreferences(applicationContext.packageName, MODE_PRIVATE)
        val userId = sharedPreferences.getLong(Constants.SHARED_PREFERENCES.USER_ID, 0)
        if (userId > 0) {
            proceed(userId)
        } else {
            API.instance().get("http://192.168.27.235:8080/user/new", { jsonObject ->
                val userId = jsonObject.getLong(Constants.SHARED_PREFERENCES.USER_ID)
                if (userId != null) {
                    val editor = sharedPreferences.edit()
                    editor.putLong(Constants.SHARED_PREFERENCES.USER_ID, userId)
                    editor.commit()
                    proceed(userId)
                } else {
                    Log.v("FAILED", "FAILED")
                }
            }, { error ->
                Log.v("FAILED", "" + error.message)
            })
        }
        this.sharedPreferences = sharedPreferences
    }

    fun proceed(userId: Long) {
        val position = 0

        val drawerItems = arrayOf(
            PrimaryDrawerItem().apply { nameText = "Friends" },
            PrimaryDrawerItem().apply { nameText = "Register" }
        )

        for (i in drawerItems.indices) {
            val drawerItem = drawerItems[i]
            slider.itemAdapter.add(drawerItem)
            if (i == position) {
                drawerItem.isSelected = true
            }
        }

        slider.onDrawerItemClickListener = { _, _, position ->
            select(navigationFragments[position])
            false
        }

        socket = IO.socket("http://wanlok.myftp.org:8081?clientid=" + userId)
        socket?.connect()
        select(navigationFragments[position])
    }
}