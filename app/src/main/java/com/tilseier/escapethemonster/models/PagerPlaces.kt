package com.tilseier.escapethemonster.models

//TODO mb Place not null
data class PagerPlaces (
    var backPlace: Place?,
    var currentPlace: Place?,
    var nextPlace: Place?
)