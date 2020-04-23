package com.tilseier.escapethemonster.screens.levels

import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tilseier.escapethemonster.R
import com.tilseier.escapethemonster.base.BaseFragment
import com.tilseier.escapethemonster.models.Level
import kotlinx.android.synthetic.main.fragment_levels_page.*


// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PAGE_LEVELS = "ARG_PAGE_LEVELS"
private const val ARG_PAGE_LEVEL_CLICK_LISTENER = "ARG_PAGE_LEVEL_CLICK_LISTENER"

class LevelsPageFragment : BaseFragment() {

    //we made listener Parcelable to pass put it in Bundle
    //this approach allows to avoid problems with passing data (listeners, models...) on orientation change
    //after orientation changes data passing later than view created
    interface PageLevelClickListener: Parcelable {
        fun onLevelClick(level: Level)
    }

    companion object {
        @JvmStatic
        fun newInstance(levels: List<Level>, levelListener: PageLevelClickListener) =
            LevelsPageFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PAGE_LEVELS, Gson().toJson(levels))
                    putParcelable(ARG_PAGE_LEVEL_CLICK_LISTENER, levelListener)
                }
            }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_levels_page
    }

    private var levels: List<Level>? = null
    private var levelClickListener: PageLevelClickListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("LevelsPageFragment", "onCreate")
        arguments?.let {
            levels = Gson().fromJson(it.getString(ARG_PAGE_LEVELS), object : TypeToken<List<Level>>() {}.type)
            levelClickListener = it.getParcelable(ARG_PAGE_LEVEL_CLICK_LISTENER)
        }
        if (this.levelClickListener == null) {
            Log.e("LevelsPageFragment", "NULL LISTENER onCreate")
        } else {
            Log.e("LevelsPageFragment", "LISTENER onCreate")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (this.levelClickListener == null) {
            Log.e("LevelsPageFragment", "NULL LISTENER onViewCreated")
        } else {
            Log.e("LevelsPageFragment", "LISTENER onViewCreated")
        }
        Log.e("LevelsPageFragment", "onViewCreated")
        //setup levels
        setupLevels()
    }

    override fun onStart() {
        super.onStart()
        Log.e("LevelsPageFragment", "onStart")
        if (this.levelClickListener == null) {
            Log.e("LevelsPageFragment", "NULL LISTENER onStart")
        } else {
            Log.e("LevelsPageFragment", "LISTENER onStart")
        }
    }

    override fun onResume() {
        super.onResume()
        Log.e("LevelsPageFragment", "onResume")
        if (this.levelClickListener == null) {
            Log.e("LevelsPageFragment", "NULL LISTENER onResume")
        } else {
            Log.e("LevelsPageFragment", "LISTENER onResume")
        }
    }

    private val lvlListener: LevelsRecycleAdapter.LevelClickListener =
        object : LevelsRecycleAdapter.LevelClickListener {
            override fun onLevelClick(level: Level) {
                Log.e("LevelsPageFragment", "LevelsRecycleAdapter.LevelClickListener onLevelClick")
                levelClickListener?.onLevelClick(level)

                if (levelClickListener == null) {
                    Log.e("LevelsPageFragment", "NULL LISTENER!!! 2")
                }
            }
        }

    private fun setupLevels() {
        val adapter: LevelsRecycleAdapter? = levels?.let { LevelsRecycleAdapter(it) }
        adapter?.setOnLevelClickListener (lvlListener)
        rv_levels.layoutManager = GridLayoutManager(context, 3)//, RecyclerView.VERTICAL, false
        rv_levels.adapter = adapter
    }

}