package com.tilseier.escapethemonster.screens.game

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.tilseier.escapethemonster.R
import com.tilseier.escapethemonster.base.BaseFragment
import com.tilseier.escapethemonster.models.Level
import com.tilseier.escapethemonster.screens.LevelsViewModel
import kotlinx.android.synthetic.main.fragment_game.*

class GameFragment : BaseFragment() {

    private var mLevelsViewModel: LevelsViewModel? = null
    private var mGameViewModel: GameViewModel? = null

//    public var vpPlaces = vp_places

//    var handler = Handler()

    override fun getLayoutId(): Int {
        return R.layout.fragment_game
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mLevelsViewModel =
            activity?.let { ViewModelProvider(it).get(LevelsViewModel::class.java) } //available in all activity's fragments
        mGameViewModel =
            ViewModelProvider(this).get(GameViewModel::class.java) //only available in this fragment
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e("GameFragment", "onViewCreated")

        mLevelsViewModel?.getSelectedLevel()?.observe(viewLifecycleOwner, Observer {
            Log.e("GameFragment", "level: ${it.level}")
//            setupLevel(level)
            setupPagerPlaces()
        })

        btn_back.setOnClickListener {
            navController.popBackStack()
        }
        btn_place_ahead.setOnClickListener {
            vp_places.setCurrentItem(vp_places.currentItem + 1, true);
        }
        btn_place_back.setOnClickListener {
            vp_places.setCurrentItem(vp_places.currentItem - 1, true);
        }
    }

    private fun setupLevel(level: Level) {
        //TODO

    }

    private fun setupPagerPlaces() {
        //pagerPlaces: PagerPlaces

        //TODO
        val placesFragments: MutableList<Fragment> = mutableListOf()

        val place1 = PlaceFragment.newInstance()//, lvlListener
        val place2 = PlaceFragment.newInstance()//, lvlListener
        val place3 = PlaceFragment.newInstance()//, lvlListener
        placesFragments.add(place1)
        placesFragments.add(place2)
        placesFragments.add(place3)

        val levelsPagerAdapter: FragmentPagerAdapter =
            PlacePagerAdapter(childFragmentManager, placesFragments)

        vp_places.adapter = levelsPagerAdapter
//        vp_places.currentItem = 1

        vp_places.addOnPageChangeListener(PlacePageListener())
    }

//    private fun updatePagerPlaces(pagerPlaces: PagerPlaces) {
//        //TODO
//        vp_places.setAdapter(adapter)
//        vp_places.setCurrentItem(1)
//
//        vp_places.addOnPageChangeListener(com.kumar.dipanshu.viewpagertransformation.CircularViewPager.MyPageListener())
//    }

    private class PlacePageListener : ViewPager.OnPageChangeListener {
        override fun onPageScrollStateChanged(p0: Int) {

        }

        override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {
        }

        override fun onPageSelected(position: Int) {
//            if (position == 0) {
//                vpPlaces.setCurrentItem(3, true)
////                handler.postDelayed(Runnable { viewPager.setCurrentItem(10, false) }, 500)
//            } else if (position >= 4) {
//                vpPlaces.setCurrentItem(1, true)
////                handler.postDelayed(Runnable { viewPager.setCurrentItem(1, false) }, 500)
//            }
        }
    }

}
