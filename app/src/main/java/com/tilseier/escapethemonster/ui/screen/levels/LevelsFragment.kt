package com.tilseier.escapethemonster.ui.screen.levels

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.google.gson.Gson
import com.tilseier.escapethemonster.R
import com.tilseier.escapethemonster.ui.base.BaseFragment
import com.tilseier.escapethemonster.data.database.Level
import com.tilseier.escapethemonster.ui.extensions.toPagesWithLevels
import com.tilseier.escapethemonster.ui.screen.LevelsViewModel
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

        prepareViewsVisibility()

        mLevelsViewModel?.getLevels()?.observe(viewLifecycleOwner, Observer { levels ->
            Log.e("LevelsFragment", "LEVELS: ${levels?.size}")
            if (levels.isNotEmpty()) {
                Log.e("LevelsFragment", "LEVEL 1: ${Gson().toJson(levels[0])}")
            }
            setupLevels(levels.toPagesWithLevels(COUNT_OF_LEVELS_ON_PAGE))
        })

        mLevelsViewModel?.navigateToLevel()?.observe(viewLifecycleOwner, Observer {
            // Only proceed if the event has never been handled
            it.getEventIfNotHandled()?.let {
                mLevelsViewModel?.setSelectedLevel(it)
                mLevelsViewModel?.prepareStartSelectedLevel()
                navController.navigate(R.id.action_levelsFragment_to_gameFragment)
            }
        })
        mLevelsViewModel?.userClickOnLevel()?.observe(viewLifecycleOwner, Observer {
            Log.e("GameFragment", "goAhead: $it")
            it.getEventIfNotHandled()?.let{
                if (it.locked) {
                    Toast.makeText(context, "Level is locked!", Toast.LENGTH_SHORT).show()
                } else {
                    mLevelsViewModel?.onLevelImagesReady(it)
                }
            }
        })
//        mLevelsViewModel?.let { lifecycle.addObserver(it) }//TODO DECIDE DO WE NEED IT? //CAUSE ERROR Android lifecycle library: Cannot add the same observer with different lifecycles GameFragment: 32

        btn_back.setOnClickListener {
            navController.popBackStack()
        }
    }

    private fun prepareViewsVisibility() {
        preload_level.visibility = View.GONE
    }

    private fun preloadLevelImages(level: Level?) {
        var isLevelReady = false
        var isPreloadFailed = false
        val maxProgress = (level?.scaryPlaces?.size ?: 0) + (level?.safePlaces?.size ?: 0)
        //TODO preload all place images before start level
        preload_level.visibility = View.VISIBLE
        preload_level_progress.progress = 0
        preload_level_progress.max = maxProgress

        level?.scaryPlaces?.forEach{
            myContext?.let { it1 ->
                Glide.with(it1)
                    .load(it.imageUrl)
                    .listener(object : RequestListener<Drawable> {
                        override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
//                            Log.e("preloadLevelImages", "onLoadFailed countOfLoadedImages: $countOfLoadedImages")
                            Log.e("preloadLevelImages", "onLoadFailed 1 !!!!!!! e?.causes: ${e?.causes}")
                            e?.printStackTrace()

                            //TODO if scary place failed to load than don't load level and show message

                            if (!isPreloadFailed) {
                                //TODO check internet connection
                                //TODO message that preloading failed or user should check internet connection
                                preload_level.visibility = View.GONE
                            }
                            isPreloadFailed = true

                            return false
                        }

                        override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                            preload_level_progress.progress++

//                            Log.e("preloadLevelImages", "onResourceReady countOfLoadedImages: $countOfLoadedImages")
                            Log.e("preloadLevelImages", "onResourceReady 1 preload_level_progress.progress: ${preload_level_progress.progress} ; maxProgress: $maxProgress")

                            if (preload_level_progress.progress >= maxProgress && !isLevelReady) {
                                isLevelReady = true
                                preload_level.visibility = View.GONE
                                mLevelsViewModel?.onLevelImagesReady(level)
                            }

                            return false
                        }
                    })
                    .preload(480, 360)
            }
        }
        level?.safePlaces?.forEach{
            myContext?.let { it1 ->
                Glide.with(it1)
                    .load(it.imageUrl)
                    .listener(object : RequestListener<Drawable> {
                        override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
//                            Log.e("preloadLevelImages", "onLoadFailed countOfLoadedImages: $countOfLoadedImages")
                            Log.e("preloadLevelImages", "onLoadFailed 2 !!!!!!! e?.causes: ${e?.causes}")
                            e?.printStackTrace()

                            //TODO if safe place failed to load than remove that place from list of safe places (of course if list is big enough)

                            if (!isPreloadFailed) {
                                //TODO check internet connection
                                //TODO message that preloading failed or user should check internet connection
                                preload_level.visibility = View.GONE
                            }
                            isPreloadFailed = true

                            return false
                        }

                        override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                            preload_level_progress.progress++
//                            Log.e("preloadLevelImages", "onResourceReady countOfLoadedImages: $countOfLoadedImages")
                            Log.e("preloadLevelImages", "onResourceReady 2 preload_level_progress.progress: ${preload_level_progress.progress} ; maxProgress: $maxProgress")

                            if (preload_level_progress.progress >= maxProgress && !isLevelReady) {
                                isLevelReady = true
                                preload_level.visibility = View.GONE
                                mLevelsViewModel?.onLevelImagesReady(level)
                            }
                            return false
                        }
                    })
                    .preload(480, 360)
            }
        }

        //TODO find a way to properly start the level
    }

    private fun setupLevels(levels: List<List<Level>>) {
        val levelsFragments: MutableList<Fragment> = mutableListOf()
        levels.forEach {
            val levelsPage = LevelsPageFragment.newInstance(it)
            levelsFragments.add(levelsPage)
        }
        val levelsPagerAdapter: FragmentStatePagerAdapter =
            LevelsPagerAdapter(childFragmentManager, levelsFragments)
        levels_pager.adapter = levelsPagerAdapter
        levels_dot_indicator.setViewPager(levels_pager)
    }

    companion object {
        const val COUNT_OF_LEVELS_ON_PAGE = 9
    }
}
