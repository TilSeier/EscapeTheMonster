package com.tilseier.escapethemonster.models

import com.tilseier.escapethemonster.models.Place.Companion.INFINITE_TIME
import java.util.*

//TODO stars for level //achieve star1, star2, star3 position
data class Level(
    var level: Int,
    var safePlaces: List<Place>,
    var scaryPlaces: List<Place>,
    var locked: Boolean = true,
    var stars: Int = 0,
    //Game
    var levelPlaces: Queue<Place> = LinkedList(),
//    var passedScaryPlaces: Int = 0,
    var currentPagerPlaces: PagerPlaces = PagerPlaces(null, null, null)
) {
    fun startLevel() {
        //TODO shuffle mechanism
        levelPlaces.clear()
        levelPlaces.addAll(safePlaces)
        levelPlaces.addAll(scaryPlaces)
        levelPlaces.add(Place("", false, 3000, PlaceState.GAME_WIN_PLACE))

//        passedScaryPlaces = 0

        nextLevelPlaces()
    }

    fun nextLevelPlaces() {
        //TODO logic

        val currentPlace = levelPlaces.poll()
        val backPlace = if (currentPlace?.isMonster == true) levelPlaces.peek() else Place(
            "",
            true,
            INFINITE_TIME,
            PlaceState.GAME_OVER_PLACE
        )
        val nextPlace = if (currentPlace?.isMonster == false) levelPlaces.peek() else Place(
            "",
            true,
            INFINITE_TIME,
            PlaceState.GAME_OVER_PLACE
        )
        currentPagerPlaces = PagerPlaces(backPlace, currentPlace, nextPlace)
    }

    fun setGameOverPlaces() {
        currentPagerPlaces = PagerPlaces(
            Place("", true, INFINITE_TIME, PlaceState.GAME_OVER_PLACE),
            Place("", true, INFINITE_TIME, PlaceState.GAME_OVER_PLACE),
            Place("", true, INFINITE_TIME, PlaceState.GAME_OVER_PLACE)
        )
    }

}