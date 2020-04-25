package com.tilseier.escapethemonster.screens.levels

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.tilseier.escapethemonster.R
import com.tilseier.escapethemonster.base.BaseFragment
import com.tilseier.escapethemonster.models.Level
import com.tilseier.escapethemonster.screens.LevelsViewModel
import kotlinx.android.synthetic.main.fragment_levels.*

class LevelsFragment : BaseFragment() {

    //TODO ViewPager Indicator +
    //TODO Click on Level Item +
    //TODO Stars in the level item
    //TODO size of Grid Items like in Steps

    override fun getLayoutId(): Int {
        return R.layout.fragment_levels
    }

    private var mLevelsViewModel: LevelsViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mLevelsViewModel = activity?.let { ViewModelProvider(it).get(LevelsViewModel::class.java) }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mLevelsViewModel?.getLevelsPages()?.observe(viewLifecycleOwner, Observer {
            Log.e("LevelsFragment", "LEVEL PAGES: ${it?.size}")
            //setup levels
            setupLevels(it)
        })

//        observe(viewLifecycleOwner

        mLevelsViewModel?.navigateToLevel()?.observe(viewLifecycleOwner, Observer {
            // Only proceed if the event has never been handled
            it.getEventIfNotHandled()?.let {
                mLevelsViewModel?.setSelectedLevel(it)
                navController.navigate(R.id.action_levelsFragment_to_gameFragment)
            }
        })
//        mLevelsViewModel?.let { lifecycle.addObserver(it) }//TODO DECIDE DO WE NEED IT? //CAUSE ERROR Android lifecycle library: Cannot add the same observer with different lifecycles GameFragment: 32

        btn_back.setOnClickListener {
            navController.popBackStack();
        }
    }

    private fun setupLevels(levels: List<List<Level>>) {
        val levelsFragments: MutableList<Fragment> = mutableListOf()
        levels.forEach {
            val levelsPage = LevelsPageFragment.newInstance(it)//, lvlListener
            levelsFragments.add(levelsPage)
        }
        val levelsPagerAdapter: FragmentPagerAdapter =
            LevelsPagerAdapter(childFragmentManager, levelsFragments)
        levels_pager.adapter = levelsPagerAdapter
        levels_dot_indicator.setViewPager(levels_pager)
    }

}
