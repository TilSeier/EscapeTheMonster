package com.tilseier.escapethemonster.ui.screen.game_over

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavOptions

import com.tilseier.escapethemonster.R
import com.tilseier.escapethemonster.ui.base.BaseFragment
import com.tilseier.escapethemonster.ui.screen.LevelsViewModel
import kotlinx.android.synthetic.main.fragment_game_over.*

class GameOverFragment : BaseFragment() {

    private var mLevelsViewModel: LevelsViewModel? = null

    override fun getLayoutId(): Int {
        return R.layout.fragment_game_over
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mLevelsViewModel =
            activity?.let { ViewModelProvider(it).get(LevelsViewModel::class.java) }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mLevelsViewModel?.navigateToGameEnd()?.observe(viewLifecycleOwner, Observer {
            Log.e("GameFragment", "goAhead: $it")
            it.getEventIfNotHandled()?.let {
                navController.navigate(R.id.action_gameFragment_to_gameOverFragment)
            }
        })

        btn_move_to_screen.setOnClickListener {
            val navOptions = NavOptions.Builder()
                .setPopUpTo(R.id.menuFragment, true)
                .build()
            navController.navigate(R.id.action_gameOverFragment_to_menuFragment, null, navOptions)
        }
    }

}
