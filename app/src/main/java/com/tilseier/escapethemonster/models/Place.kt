package com.tilseier.escapethemonster.models

//TODO timer for places (true/false or float milliseconds)
data class Place (
    var imageUrl: String = "",
    var isMonster: Boolean = false,
    var state: PlaceState = PlaceState.PLACE
)