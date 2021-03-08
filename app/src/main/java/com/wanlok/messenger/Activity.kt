package com.wanlok.messenger

import android.os.Bundle
import android.os.Messenger
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem
import com.mikepenz.materialdrawer.model.interfaces.nameText
import kotlinx.android.synthetic.main.activity.*

class Activity : AppCompatActivity() {

    fun push(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.ll_content, fragment)
        fragmentTransaction.commit()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
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
        return super.onOptionsItemSelected(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(ContextCompat.getDrawable(this, R.drawable.ic_menu))

        val drawerItems = arrayOf(
            PrimaryDrawerItem().apply { nameText = "Friends" },
            PrimaryDrawerItem().apply { nameText = "Travel" }
        )

        for (i in drawerItems.indices) {
            val drawerItem = drawerItems[i]
            slider.itemAdapter.add(drawerItem)
            if (i == 0) {
                drawerItem.isSelected = true
                title = drawerItem.name?.textString
            }
        }

        slider.onDrawerItemClickListener = { _, _, position ->
            val drawerItem = drawerItems[position]
            title = drawerItem.name?.textString
            false
        }

        push(FriendListFragment())
    }
}