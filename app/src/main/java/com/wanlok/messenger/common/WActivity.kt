package com.wanlok.messenger.common

import androidx.appcompat.app.AppCompatActivity
import com.wanlok.messenger.R

open class WActivity: AppCompatActivity() {
    var navigationFragment: WNavigationFragment? = null

    override fun onBackPressed() {
        navigationFragment?.pop()
    }

    fun push(navigationFragment: WNavigationFragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.ll_content, navigationFragment)
        fragmentTransaction.commit()
        this.navigationFragment = navigationFragment
    }
}