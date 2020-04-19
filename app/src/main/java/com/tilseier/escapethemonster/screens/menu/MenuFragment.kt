package com.tilseier.escapethemonster.screens.menu

import android.os.Bundle
import android.view.View

import com.tilseier.escapethemonster.R
import com.tilseier.escapethemonster.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_menu.*

class MenuFragment : BaseFragment() {

    override fun getLayoutId(): Int {
        return R.layout.fragment_menu
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_play.setOnClickListener {
            navController.navigate(R.id.action_menuFragment_to_levelsFragment)
        }
        btn_rules.setOnClickListener {
            navController.navigate(R.id.action_menuFragment_to_levelsFragment)
        }
        btn_rate.setOnClickListener {
            navController.navigate(R.id.action_menuFragment_to_levelsFragment)
        }

    }

}
