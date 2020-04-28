package com.tilseier.escapethemonster.screens.game

import android.animation.Animator
import android.animation.ValueAnimator
import android.animation.ValueAnimator.AnimatorUpdateListener
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.view.animation.AccelerateInterpolator
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.tilseier.escapethemonster.R
import com.tilseier.escapethemonster.base.BaseFragment
import com.tilseier.escapethemonster.models.Level
import com.tilseier.escapethemonster.screens.LevelsViewModel
import kotlinx.android.synthetic.main.fragment_game.*


class GameFragment : BaseFragment() {

    //TODO preload all place images before start level +-

    //TODO view pager transformation
    //TODO progress with achievements// stars for level //achieve star1, star2, star3 position in Level model
    //TODO timer for places (true/false or float milliseconds) in Place model
    //TODO progress enhance

    //TODO fix problem with slider images

    private var mLevelsViewModel: LevelsViewModel? = null

    private var handler = Handler()
//    private var mGameViewModel: GameViewModel? = null

    private lateinit var placeBack: PlaceFragment
    private lateinit var placeCurrent: PlaceFragment
    private lateinit var placeForward: PlaceFragment

    private var doSetupLevel = true

    override fun getLayoutId(): Int {
        return R.layout.fragment_game
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mLevelsViewModel =
            activity?.let { ViewModelProvider(it).get(LevelsViewModel::class.java) } //available in all activity's fragments
//        mGameViewModel =
//            ViewModelProvider(this).get(GameViewModel::class.java) //only available in this fragment
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e("GameFragment", "onViewCreated")

//        setupPagerPlaces()

        setupLevel(mLevelsViewModel?.getSelectedLevel()?.value)

//        mLevelsViewModel?.getSelectedLevel()?.observe(viewLifecycleOwner, Observer {
//            Log.e("GameFragment", "level: ${it.level}")
//            if (doSetupLevel) {
//                setupLevel(it)
//            }
//            //TODO MB SEPARATE UPDATE
////            updateLevel(it)
////            setupPagerPlaces()
////            mLevelsViewModel?.startGame()
//        })

        mLevelsViewModel?.getPassedScaryPlaces()?.observe(viewLifecycleOwner, Observer {
            Log.e("GameFragment", "PassedScaryPlaces: $it")
            level_progress?.progress = it
        })
        mLevelsViewModel?.goAhead()?.observe(viewLifecycleOwner, Observer {
            Log.e("GameFragment", "goAhead: $it")
            it.getEventIfNotHandled()?.let{
                animatePlacePagerTransition(true)
            }
        })
        mLevelsViewModel?.goBack()?.observe(viewLifecycleOwner, Observer {
            Log.e("GameFragment", "goAhead: $it")
            it.getEventIfNotHandled()?.let{
                animatePlacePagerTransition(false)
            }
        })
        mLevelsViewModel?.navigateToGameEnd()?.observe(viewLifecycleOwner, Observer {
            Log.e("GameFragment", "goAhead: $it")
            it.getEventIfNotHandled()?.let{
                navController.navigate(R.id.action_gameFragment_to_gameOverFragment)
            }
        })

//        mLevelsViewModel?.getPagerPlaces()?.observe(viewLifecycleOwner, Observer {
//            Log.e("GameFragment", "getPagerPlaces: $it")
//            updatePagerPlaces(it)
//        })

//        mLevelsViewModel?.getPagerPlaces()?.observe(viewLifecycleOwner, Observer {
//            Log.e("GameFragment", "backPlace?.imageUrl: ${it.backPlace?.imageUrl}")
//            Log.e("GameFragment", "currentPlace?.imageUrl: ${it.currentPlace?.imageUrl}")
//            Log.e("GameFragment", "nextPlace?.imageUrl: ${it.nextPlace?.imageUrl}")
////            setupLevel(level)
////            setupPagerPlaces()
////            mLevelsViewModel?.startGame()
//        })

        btn_back.setOnClickListener {
            navController.popBackStack()
        }
        btn_place_ahead.setOnClickListener {
            mLevelsViewModel?.userClickGoAhead()
//            vp_places.setCurrentItem(vp_places.currentItem + 1, true);
        }
        btn_place_back.setOnClickListener {
            mLevelsViewModel?.userClickGoBack()
//            vp_places.setCurrentItem(vp_places.currentItem - 1, true);
        }
    }

    private fun setupLevel(level: Level?) {
        doSetupLevel = false
        setupPagerPlaces(level)
        level_progress?.max = level?.scaryPlaces?.size ?: 0
//        level_progress.progress = level?.passedScaryPlaces ?: 0//TODO
    }

    private fun setupPagerPlaces(level: Level?) {
        //pagerPlaces: PagerPlaces

        //TODO
        val placesFragments: MutableList<Fragment> = mutableListOf()

//        val _place3 = PlaceFragment.newInstance()
        placeCurrent = PlaceFragment.newInstance(CURRENT_PLACE)
        placeBack = PlaceFragment.newInstance(BACK_PLACE)
        placeForward = PlaceFragment.newInstance(NEXT_PLACE)
//        val _place1 = PlaceFragment.newInstance()
//        placesFragments.add(_place3)
        placesFragments.add(placeBack)
        placesFragments.add(placeCurrent)
        placesFragments.add(placeForward)
//        placesFragments.add(_place1)

        val levelsPagerAdapter: FragmentPagerAdapter =
            PlacePagerAdapter(childFragmentManager, placesFragments)

        vp_places?.adapter = levelsPagerAdapter
        vp_places?.currentItem = 1

//        vp_places.addOnPageChangeListener(PlacePageListener())

        vp_places?.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageSelected(position: Int) {
                Log.e("GameFragment", "onPageSelected position: $position")
                Log.e("GameFragment", "onPageSelected vp_places.isFakeDragging: ${vp_places.isFakeDragging}")
                if (position == 0 || position == 2) {
                    handler.postDelayed({
                        if (position == 0) {
                            mLevelsViewModel?.onPlaceGoBackAnimationEnd()
                        } else {
                            mLevelsViewModel?.onPlaceGoAheadAnimationEnd()
                        }
                        vp_places?.setCurrentItem(1, false)
                    }, 100)//TODO appropriate count of milliseconds
                }

//                if (position == 0) {
//                    vp_places.setCurrentItem(3, false)
//                    animatePagerTransition(false)
////                    handler.postDelayed({ vp_places.setCurrentItem(3, false) }, 500)
//                } else if (position >= 4) {
//                    vp_places.setCurrentItem(1, false)
//                    animatePagerTransition(true)
////                    handler.postDelayed({ vp_places.setCurrentItem(1, false) }, 500)
//                }
            }
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

            override fun onPageScrollStateChanged(state: Int) {
//                if (state == ViewPager.SCROLL_STATE_IDLE) {
//                    if (mFocusedPage === 0) {
//                        // move some stuff from the
//                        // center to the right here
//                        moveStuff(pages.get(1), pages.get(2))
//
//                        // move stuff from the left to the center
//                        moveStuff(pages.get(0), pages.get(1))
//                        // retrieve new stuff and insert it to the left page
//                        insertStuff(pages.get(0))
//                    } else if (mFocusedPage === 2) {
//
//
//                        // move stuff from the center to the left page
//                        moveStuff(pages.get(1), pages.get(0))
//                        // move stuff from the right to the center page
//                        moveStuff(pages.get(2), pages.get(1))
//                        // retrieve stuff and insert it to the right page
//                        insertStuff(pages.get(2))
//                    }
//
//                    // go back to the center allowing to scroll indefinitely
//                    mDayPager.setCurrentItem(1, false)
//                }
            }
        })

    }

    private fun animatePlacePagerTransition(forward: Boolean) {
        Log.e("GameFragment", "animatePagerTransition vp_places.isFakeDragging: ${vp_places.isFakeDragging}")
        if (!vp_places.isFakeDragging) {
            val animator = ValueAnimator.ofInt(
                0,
                vp_places.getWidth() - if (forward) vp_places.getPaddingLeft() else vp_places.getPaddingRight()
            )
            animator.addListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(animation: Animator?) {}
                override fun onAnimationEnd(animation: Animator?) {
                    vp_places?.endFakeDrag()
                }

                override fun onAnimationCancel(animation: Animator?) {
                    vp_places?.endFakeDrag()
                }

                override fun onAnimationRepeat(animation: Animator?) {}
            })
            animator.interpolator = AccelerateInterpolator()//try
            animator.addUpdateListener(object : AnimatorUpdateListener {
                private var oldDragPosition = 0
                override fun onAnimationUpdate(animation: ValueAnimator) {
                    val dragPosition = animation.animatedValue as Int
                    val dragOffset = dragPosition - oldDragPosition
                    oldDragPosition = dragPosition
                    if (vp_places != null) {
                        if (vp_places.isFakeDragging || vp_places.beginFakeDrag()) {
                            vp_places.fakeDragBy((dragOffset * if (forward) -1 else 1).toFloat())
                        }
                    }
                }
            })
            animator.duration = 1000//TODO appropriate count of milliseconds//AppConstants.PAGER_TRANSITION_DURATION_MS

            vp_places?.beginFakeDrag()
            animator.start()
        }
    }

//    private fun updatePagerPlaces(pagerPlaces: PagerPlaces) {
        //TODO
////        placeCurrent.setPlace(pagerPlaces.currentPlace)
////        placeBack.setPlace(pagerPlaces.backPlace)
////        placeForward.setPlace(pagerPlaces.nextPlace)
//
//        placeCurrent = PlaceFragment.newInstance(pagerPlaces.currentPlace?.imageUrl)
//        placeBack = PlaceFragment.newInstance(pagerPlaces.backPlace?.imageUrl)
//        placeForward = PlaceFragment.newInstance(pagerPlaces.nextPlace?.imageUrl)
//
//        vp_places.adapter?.notifyDataSetChanged()
//    }

//    private class PlacePageListener : ViewPager.OnPageChangeListener {
//        override fun onPageScrollStateChanged(p0: Int) {
//
//        }
//
//        override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {
//        }
//
//        override fun onPageSelected(position: Int) {
////            if (position == 0) {
////                vpPlaces.setCurrentItem(3, true)
//////                handler.postDelayed(Runnable { viewPager.setCurrentItem(10, false) }, 500)
////            } else if (position >= 4) {
////                vpPlaces.setCurrentItem(1, true)
//////                handler.postDelayed(Runnable { viewPager.setCurrentItem(1, false) }, 500)
////            }
//        }
//    }

}
