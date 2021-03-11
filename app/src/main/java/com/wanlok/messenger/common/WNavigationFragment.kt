package com.wanlok.messenger.common

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.wanlok.messenger.R

class WNavigationFragment(var rootFragment: WFragment): Fragment() {
    private var fragments = ArrayList<WFragment>()

    fun title(title: String) {
        (activity as AppCompatActivity).supportActionBar?.let { supportActionBar ->
            supportActionBar.title = title
            supportActionBar.setDisplayHomeAsUpEnabled(true)
            if (fragments.size == 1) {
                supportActionBar.setHomeAsUpIndicator(ContextCompat.getDrawable(activity as Activity, R.drawable.ic_menu))
            } else {
                supportActionBar.setHomeAsUpIndicator(null)
            }
        }
    }

    fun isRoot(): Boolean {
        return fragments.size == 1
    }

    fun push(fragment: WFragment) {
        if (isAdded) {
            val fragmentTransaction = childFragmentManager.beginTransaction()
            fragmentTransaction.add(R.id.fl_root, fragment)
            if (fragments.size > 0) {
                fragmentTransaction.addToBackStack(null)
            }
            fragmentTransaction.commit()
            fragments.add(fragment)
            title(fragment.title)
        }
    }

    fun pop() {
        if (isAdded) {
            if (fragments.size > 1) {
                childFragmentManager.popBackStack()
                fragments.removeAt(fragments.size - 1)
            }
            if (fragments.size > 0) {
                title(fragments[fragments.size - 1].title)
            }
        }
    }

    fun peek() {
        if (isAdded) {
            val fragment = fragments.last()
            val fragmentTransaction = childFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fl_root, fragment)
            fragmentTransaction.commit()
            title(fragment.title)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_root, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (fragments.size == 0) {
            push(rootFragment)
        } else {
            peek()
        }
    }
}