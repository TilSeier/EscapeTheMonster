package com.tilseier.escapethemonster.screens.game

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tilseier.escapethemonster.R
import com.tilseier.escapethemonster.base.BaseFragment
import com.tilseier.escapethemonster.models.Level
import com.tilseier.escapethemonster.screens.LevelsViewModel
import kotlinx.android.synthetic.main.fragment_levels_page.*

// the fragment initialization parameters, e.g. ARG_PAGE_LEVELS
private const val ARG_PAGE_LEVELS = "ARG_PAGE_LEVELS"

class PlaceFragment : BaseFragment() {

    companion object {
        @JvmStatic
        fun newInstance() = PlaceFragment()
//                .apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PAGE_LEVELS, Gson().toJson(levels))
//                }
//            }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_place
    }

//    private var mLevelsViewModel: LevelsViewModel? = null
//    private var levels: List<Level>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        mLevelsViewModel = activity?.let { ViewModelProvider(it).get(LevelsViewModel::class.java) }
//        arguments?.let {
//            levels = Gson().fromJson(it.getString(ARG_PAGE_LEVELS), object : TypeToken<List<Level>>() {}.type)
//        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //setup levels
        setupPlace()
    }

    private fun setupPlace() {

    }

}