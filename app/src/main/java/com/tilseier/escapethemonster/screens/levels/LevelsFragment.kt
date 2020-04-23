package com.tilseier.escapethemonster.screens.levels

import android.os.Bundle
import android.os.Parcel
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.ViewModelProvider
import com.tilseier.escapethemonster.R
import com.tilseier.escapethemonster.base.BaseFragment
import com.tilseier.escapethemonster.models.Level
import kotlinx.android.synthetic.main.fragment_levels.*

class LevelsFragment : BaseFragment() {

    //TODO ViewPager Indicator +
    //TODO Click on Level Item +
    //TODO Stars in the level item
    //TODO size of Grid Items like in Steps

    override fun getLayoutId(): Int {
        return R.layout.fragment_levels
    }

    private lateinit var mLevelsViewModel: LevelsViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.e("TAG", "LevelsFragment onViewCreated")

        mLevelsViewModel =
            ViewModelProvider(this).get(LevelsViewModel::class.java)
        mLevelsViewModel.init()
        mLevelsViewModel.getLevelsPages()?.observe(viewLifecycleOwner) { levelsPages ->
            Log.e("TAG", "LEVEL PAGES: ${levelsPages?.size}")

            //setup levels
            setupLevels(levelsPages)
        }
        lifecycle.addObserver(mLevelsViewModel)

        btn_back.setOnClickListener {//TODO CHANGE
            navController.navigate(R.id.action_levelsFragment_to_gameFragment)
        }
    }

    private fun setupLevels(levels: List<List<Level>>) {
        val levelsFragments: MutableList<Fragment> = mutableListOf()
        levels.forEach {
            val levelsPage = LevelsPageFragment.newInstance(it, lvlListener)
            levelsFragments.add(levelsPage)
        }
        val levelsPagerAdapter: FragmentPagerAdapter = LevelsPagerAdapter(childFragmentManager, levelsFragments)
        levels_pager.adapter = levelsPagerAdapter
        levels_dot_indicator.setViewPager(levels_pager)
    }

    private val lvlListener: LevelsPageFragment.PageLevelClickListener =
        object : LevelsPageFragment.PageLevelClickListener {
            override fun onLevelClick(level: Level) {

                Log.e("LevelsFragment", "LevelsPageFragment CLICK activity: $activity")
                Log.e("LevelsFragment", "LevelsPageFragment CLICK context: $context")
                Log.e("LevelsFragment", "LevelsPageFragment CLICK myContext: $myContext")

                Log.e("LevelsFragment", "LevelsPageFragment.PageLevelClickListener onLevelClick ${level.level}")
                //TODO open game
                showToastMessage(level.level.toString())
            }

            override fun writeToParcel(p0: Parcel?, p1: Int) {}

            override fun describeContents(): Int {
                return 0
            }
        }

}
