package com.wanlok.messenger.common

import androidx.fragment.app.Fragment

//fun add(fragment: Fragment, id: Int, parent: Fragment?, root: Boolean, type: Int?) {
//    val added = parent?.isAdded()
//    if (added != null && added) {
//        var fragmentManager = parent?.childFragmentManager
//        if (fragmentManager != null) {
//            val fragmentTransaction = fragmentManager.beginTransaction()
//            fragmentTransaction.add(id, fragment)
//            if (!root) {
//                fragmentTransaction.addToBackStack(null)
//            }
//            fragmentTransaction.commit()
//            if (!root) {
//                backButton(type, parent)
//            }
//        }
//    }
//}