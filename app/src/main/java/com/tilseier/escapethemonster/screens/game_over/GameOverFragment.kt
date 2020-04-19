package com.tilseier.escapethemonster.screens.game_over

import android.os.Bundle
import android.view.View

import com.tilseier.escapethemonster.R
import com.tilseier.escapethemonster.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_game_over.*

class GameOverFragment : BaseFragment() {

    override fun getLayoutId(): Int {
        return R.layout.fragment_game_over
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_move_to_screen.setOnClickListener {
            navController.navigate(R.id.action_gameOverFragment_to_menuFragment)
        }
    }

}
