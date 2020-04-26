package com.tilseier.escapethemonster.screens.game

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.tilseier.escapethemonster.R
import com.tilseier.escapethemonster.base.BaseFragment
import com.tilseier.escapethemonster.models.PagerPlaces
import com.tilseier.escapethemonster.models.Place
import com.tilseier.escapethemonster.models.PlaceState
import com.tilseier.escapethemonster.screens.LevelsViewModel
import kotlinx.android.synthetic.main.fragment_place.*

// the fragment initialization parameters, e.g. ARG_PAGE_LEVELS
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

//    private var mLevelsViewModel: LevelsViewModel? = null
//    private var levels: List<Level>? = null

//    private var place: Place? = null
//    private var placeImageURL: String? = null

    private var mLevelsViewModel: LevelsViewModel? = null

    private var placeStatus: String = BACK_PLACE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mLevelsViewModel =
            activity?.let { ViewModelProvider(it).get(LevelsViewModel::class.java) }
//        mLevelsViewModel = activity?.let { ViewModelProvider(it).get(LevelsViewModel::class.java) }
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

        //setup levels

//        setupPlace()
//        placeImageURL?.let{
//            Glide.with(requireContext())
//                .load(it)
////                .error(R.drawable.avatar)
//                .into(iv_place)
//        }
    }

    private fun updatePlace(pagerPlaces: PagerPlaces?) {
        when (placeStatus) {
            BACK_PLACE -> {
                setPlace(pagerPlaces?.backPlace?.imageUrl, pagerPlaces?.backPlace?.state ?: PlaceState.PLACE)

//                when (pagerPlaces?.backPlace?.state) {
//                    PlaceState.PLACE -> {
//                        pagerPlaces.backPlace?.imageUrl?.let {
//                            Glide.with(requireContext())
//                                .load(it)
//                                .into(iv_place)
//                        }
//                    }
//                    PlaceState.GAME_OVER_PLACE -> {
//                        Glide.with(requireContext())
//                            .load(R.drawable.img_screamer)
//                            .into(iv_place)
//                    }
//                    else -> {
//                        Glide.with(requireContext())
//                            .load(R.drawable.open_doors)
//                            .into(iv_place)
//                    }
//                }
            }
            CURRENT_PLACE -> {

                setPlace(pagerPlaces?.currentPlace?.imageUrl, pagerPlaces?.currentPlace?.state ?: PlaceState.PLACE)

//                when (pagerPlaces?.currentPlace?.state) {
//                    PlaceState.PLACE -> {
//                        //PLACE
//                        pagerPlaces.currentPlace?.imageUrl?.let {
//                            Glide.with(requireContext())
//                                .load(it)
//                                .into(iv_place)
//                        }
//
//                        //TODO preload all place images before start level
//            //                    pagerPlaces.backPlace?.imageUrl?.let {
//            //                        Glide.with(requireContext())
//            //                            .load(it)
//            //                            .into(iv_preload_place)
//            //                    }
//                    }
//                    PlaceState.GAME_OVER_PLACE -> {
//                        //GAME OVER PLACE
//                        Glide.with(requireContext())
//                            .load(R.drawable.img_screamer)
//                            .into(iv_place)
//                    }
//                    else -> {
//                        //WIN PLACE
//                        Glide.with(requireContext())
//                            .load(R.drawable.open_doors)
//                            .into(iv_place)
//                    }
//                }


//                pagerPlaces?.nextPlace?.imageUrl?.let{
//                    Glide.with(requireContext())
//                        .load(it)
//                        .into(iv_preload_place)
//                }
            }
            NEXT_PLACE -> {
                setPlace(pagerPlaces?.nextPlace?.imageUrl, pagerPlaces?.nextPlace?.state ?: PlaceState.PLACE)

//                when (pagerPlaces?.nextPlace?.state) {
//                    PlaceState.PLACE -> {
//                        pagerPlaces.nextPlace?.imageUrl?.let {
//                            Glide.with(requireContext())
//                                .load(it)
//                                .into(iv_place)
//                        }
//                    }
//                    PlaceState.GAME_OVER_PLACE -> {
//                        Glide.with(requireContext())
//                            .load(R.drawable.img_screamer)
//                            .into(iv_place)
//                    }
//                    else -> {
//                        Glide.with(requireContext())
//                            .load(R.drawable.open_doors)
//                            .into(iv_place)
//                    }
//                }
            }
        }
    }

    private fun setPlace(imageUrl: String?, placeState: PlaceState){
        when (placeState) {
            PlaceState.PLACE -> {
                imageUrl?.let {
                    Glide.with(requireContext())
                        .load(it)
                        .into(iv_place)
                }
            }
            PlaceState.GAME_OVER_PLACE -> {
                Glide.with(requireContext())
                    .load(R.drawable.img_screamer)
                    .into(iv_place)
            }
            else -> {
                Glide.with(requireContext())
                    .load(R.drawable.open_doors)
                    .into(iv_place)
            }
        }
    }

//    override fun onResume() {
//        super.onResume()
//
//    }

//    fun setPlace(place: Place?) {
//        Log.e("PlaceFragment", "place.imageUrl: ${place?.imageUrl}");
////        this.place = place
//    }

}