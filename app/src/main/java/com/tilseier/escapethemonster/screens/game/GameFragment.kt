package com.tilseier.escapethemonster.screens.game

import android.os.Bundle
import android.view.View

import com.tilseier.escapethemonster.R
import com.tilseier.escapethemonster.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_game.*

class GameFragment : BaseFragment() {

    override fun getLayoutId(): Int {
        return R.layout.fragment_game
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_move_to_screen.setOnClickListener {
            navController.navigate(R.id.action_gameFragment_to_gameOverFragment)
        }
    }

}
