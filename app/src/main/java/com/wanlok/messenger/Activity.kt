package com.wanlok.messenger

import android.os.Bundle
import android.view.MenuItem
import androidx.core.view.GravityCompat
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem
import com.mikepenz.materialdrawer.model.interfaces.nameText
import com.wanlok.messenger.common.WActivity
import com.wanlok.messenger.common.WNavigationFragment
import kotlinx.android.synthetic.main.activity.*

class Activity: WActivity() {
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

        navigationFragments.add(WNavigationFragment(FriendListFragment()))
        navigationFragments.add(WNavigationFragment(MessengerFragment()))

        val position = 0

        val drawerItems = arrayOf(
            PrimaryDrawerItem().apply { nameText = "Friends" },
            PrimaryDrawerItem().apply { nameText = "About" }
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

        select(navigationFragments[position])
    }
}