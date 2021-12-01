package com.tilseier.escapethemonster.data.model

//TODO timer for places (true/false or float milliseconds)
data class Place (
    var imageUrl: String = "",
    var isMonster: Boolean = false,
    var milliseconds: Long = INFINITE_TIME, // 3000 //milliseconds
    var state: PlaceState = PlaceState.PLACE,
    var imageResource: Int? = null,
    var count: Int = 1
) {
    companion object {
        const val INFINITE_TIME: Long = -1
    }
}