package com.tilseier.escapethemonster.ui.screen.levels

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tilseier.escapethemonster.R
import com.tilseier.escapethemonster.ui.base.BaseFragment
import com.tilseier.escapethemonster.data.model.Level
import com.tilseier.escapethemonster.ui.screen.LevelsViewModel
import kotlinx.android.synthetic.main.fragment_levels_page.*

// the fragment initialization parameters, e.g. ARG_PAGE_LEVELS
private const val ARG_PAGE_LEVELS = "ARG_PAGE_LEVELS"

class LevelsPageFragment : BaseFragment() {

    companion object {
        @JvmStatic
        fun newInstance(levels: List<Level>) =
            LevelsPageFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PAGE_LEVELS, Gson().toJson(levels))
                }
            }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_levels_page
    }

    private var mLevelsViewModel: LevelsViewModel? = null
    private var levels: List<Level>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mLevelsViewModel = activity?.let { ViewModelProvider(it).get(LevelsViewModel::class.java) }
        arguments?.let {
            levels = Gson().fromJson(it.getString(ARG_PAGE_LEVELS), object : TypeToken<List<Level>>() {}.type)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //setup levels
        setupLevels()
    }

    private val lvlListener: LevelsRecycleAdapter.LevelClickListener =
        object : LevelsRecycleAdapter.LevelClickListener {
            override fun onLevelClick(level: Level) {
                Log.e("LevelsPageFragment", "LevelsRecycleAdapter.LevelClickListener onLevelClick ${level.level}")
                mLevelsViewModel?.userClickOnLevel(level)
            }
        }

    private fun setupLevels() {
        val adapter: LevelsRecycleAdapter? = levels?.let { LevelsRecycleAdapter(it) }
        adapter?.setOnLevelClickListener (lvlListener)
        rv_levels.layoutManager = GridLayoutManager(context, 3)//, RecyclerView.VERTICAL, false
        rv_levels.adapter = adapter
    }

}