package com.wanlok.messenger.common

import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.wanlok.messenger.R

open class WActivity: AppCompatActivity() {
    var navigationFragments = ArrayList<WNavigationFragment>()
    var currentNavigationFragment: WNavigationFragment? = null

    override fun onBackPressed() {
        currentNavigationFragment?.pop()
    }

    fun select(navigationFragment: WNavigationFragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.ll_content, navigationFragment)
        fragmentTransaction.commit()
        currentNavigationFragment = navigationFragment
    }
}