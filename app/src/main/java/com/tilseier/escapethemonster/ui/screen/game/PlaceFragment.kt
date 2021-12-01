package com.tilseier.escapethemonster.ui.screen.game

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.tilseier.escapethemonster.R
import com.tilseier.escapethemonster.ui.base.BaseFragment
import com.tilseier.escapethemonster.data.model.PagerPlaces
import com.tilseier.escapethemonster.data.model.PlaceState
import com.tilseier.escapethemonster.ui.extensions.px
import com.tilseier.escapethemonster.ui.screen.LevelsViewModel
import kotlinx.android.synthetic.main.fragment_place.*

// the fragment initialization parameters, e.g. ARG_PLACE_STATUS
private const val ARG_PLACE_STATUS = "ARG_PLACE_STATUS"

const val BACK_PLACE = "BACK_PLACE"
const val CURRENT_PLACE = "CURRENT_PLACE"
const val NEXT_PLACE = "NEXT_PLACE"

class PlaceFragment : BaseFragment() {

    companion object {
        @JvmStatic
        fun newInstance(placeStatus: String?) = PlaceFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PLACE_STATUS, placeStatus)
                }
            }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_place
    }

    private var mLevelsViewModel: LevelsViewModel? = null

    private var placeStatus: String = BACK_PLACE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mLevelsViewModel =
            activity?.let { ViewModelProvider(it).get(LevelsViewModel::class.java) }
        arguments?.let {
            placeStatus = it.getString(ARG_PLACE_STATUS) ?: BACK_PLACE
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mLevelsViewModel?.getPagerPlaces()?.observe(viewLifecycleOwner, Observer {
            Log.e("PlaceFragment", "getPagerPlaces backPlace: ${it.backPlace}")
            Log.e("PlaceFragment", "getPagerPlaces currentPlace: ${it.currentPlace}")
            Log.e("PlaceFragment", "getPagerPlaces nextPlace: ${it.nextPlace}")
            updatePlace(it)
        })

    }

    private fun updatePlace(pagerPlaces: PagerPlaces?) {
        when (placeStatus) {
            BACK_PLACE -> {
                setPlace(pagerPlaces?.backPlace?.imageResource, pagerPlaces?.backPlace?.imageUrl, pagerPlaces?.backPlace?.state ?: PlaceState.PLACE)
            }
            CURRENT_PLACE -> {
                setPlace(pagerPlaces?.currentPlace?.imageResource, pagerPlaces?.currentPlace?.imageUrl, pagerPlaces?.currentPlace?.state ?: PlaceState.PLACE)
            }
            NEXT_PLACE -> {
                setPlace(pagerPlaces?.nextPlace?.imageResource, pagerPlaces?.nextPlace?.imageUrl, pagerPlaces?.nextPlace?.state ?: PlaceState.PLACE)
            }
        }
    }

    private fun setPlace(imageResource: Int?, imageUrl: String?, placeState: PlaceState){
        Glide.with(requireContext())
            .load(imageResource ?: imageUrl ?: R.drawable.test_road)
            .into(iv_place)
        when (placeState) {
            PlaceState.PLACE -> {
                iv_finish.visibility = View.GONE
                iv_star.visibility = View.GONE
            }
            PlaceState.GAME_OVER_PLACE -> {
                Glide.with(requireContext())
                    .load(R.drawable.img_screamer)
                    .into(iv_place)
                iv_finish.visibility = View.GONE
                iv_star.visibility = View.GONE
            }
            PlaceState.REWARD_PLACE -> {
                iv_star.layoutParams.width = 200.px
                iv_star.layoutParams.height = 200.px
                iv_finish.visibility = View.GONE
                iv_star.visibility = View.VISIBLE
            }
            PlaceState.NEAR_REWARD_PLACE -> {
                iv_star.layoutParams.width = 120.px
                iv_star.layoutParams.height = 120.px
                iv_finish.visibility = View.GONE
                iv_star.visibility = View.VISIBLE
            }
            PlaceState.FAR_FROM_REWARD_PLACE -> {
                iv_star.layoutParams.width = 50.px
                iv_star.layoutParams.height = 50.px
                iv_finish.visibility = View.GONE
                iv_star.visibility = View.VISIBLE
            }
            else -> {
                //WIN PLACE
                iv_finish.visibility = View.VISIBLE
                iv_star.visibility = View.GONE
            }
        }
    }

}