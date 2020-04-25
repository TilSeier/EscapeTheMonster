package com.tilseier.escapethemonster.screens.game

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import com.tilseier.escapethemonster.R
import com.tilseier.escapethemonster.base.BaseFragment
import com.tilseier.escapethemonster.models.Level
import com.tilseier.escapethemonster.screens.LevelsViewModel
import kotlinx.android.synthetic.main.fragment_game.*

class GameFragment : BaseFragment() {

    private var mLevelsViewModel: LevelsViewModel? = null
    private var mGameViewModel: GameViewModel? = null

    override fun getLayoutId(): Int {
        return R.layout.fragment_game
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mLevelsViewModel = activity?.let { ViewModelProvider(it).get(LevelsViewModel::class.java) }
        mGameViewModel = activity?.let { ViewModelProvider(it).get(GameViewModel::class.java) }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e("GameFragment", "onViewCreated")

        mLevelsViewModel?.getSelectedLevel()?.observe(viewLifecycleOwner) { level ->
            Log.e("GameFragment", "level: ${level.level}")
            setupLevel(level)
        }

        btn_move_to_screen.setOnClickListener {
            navController.navigate(R.id.action_gameFragment_to_gameOverFragment)
        }
    }

    private fun setupLevel(level: Level) {
        //TODO
    }

}
