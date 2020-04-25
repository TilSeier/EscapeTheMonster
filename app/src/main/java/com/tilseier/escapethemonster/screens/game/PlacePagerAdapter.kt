package com.tilseier.escapethemonster.screens.game

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import java.util.*

class PlacePagerAdapter(manager: FragmentManager?, var fragments: List<Fragment>) :
    FragmentPagerAdapter(manager!!, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getCount(): Int {
        return fragments.size
    }
}