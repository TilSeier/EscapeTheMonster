package com.tilseier.escapethemonster.screens.levels

import android.os.Bundle
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

    private var levels: List<Level>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            levels = Gson().fromJson(it.getString(ARG_PAGE_LEVELS), object : TypeToken<List<Level>>() {}.type)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.e("LevelsPageFragment", "onViewCreated")
        //setup levels
        setupLevels()
    }

    private fun setupLevels() {
        val adapter: LevelsRecycleAdapter? = levels?.let { LevelsRecycleAdapter(it) }
        rv_levels.layoutManager = GridLayoutManager(context, 3)//, RecyclerView.VERTICAL, false
        rv_levels.adapter = adapter
    }

}