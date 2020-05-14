package com.tilseier.escapethemonster.data.model

//TODO timer for places (true/false or float milliseconds)
data class Place (
    var imageUrl: String = "",
    var isMonster: Boolean = false,
    var milliseconds: Long = 3000,//milliseconds
    var state: PlaceState = PlaceState.PLACE
) {
    companion object {
        const val INFINITE_TIME: Long = -1
    }
}