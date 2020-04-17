package com.tilseier.escapethemonster.models

data class Level (
    var level: Int,
    var places: List<Place>,
    var locked: Boolean = true,
    var stars: Int = 0
)