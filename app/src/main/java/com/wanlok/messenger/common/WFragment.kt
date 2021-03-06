package com.wanlok.messenger.common

import androidx.fragment.app.Fragment
import com.wanlok.messenger.Activity

open class WFragment: Fragment() {
    var title: String = ""

    fun push(fragment: WFragment) {
        (activity as? Activity)?.currentNavigationFragment?.push(fragment)
    }

    fun title(title: String) {
        this.title = title
        (activity as? Activity)?.currentNavigationFragment?.title(title)
    }
}