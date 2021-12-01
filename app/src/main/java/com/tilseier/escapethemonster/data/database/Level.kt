package com.tilseier.escapethemonster.data.database

import androidx.room.*
import com.tilseier.escapethemonster.data.DataConverter
import com.tilseier.escapethemonster.data.model.Achievements
import com.tilseier.escapethemonster.data.model.PagerPlaces
import com.tilseier.escapethemonster.data.model.Place
import com.tilseier.escapethemonster.data.model.Place.Companion.INFINITE_TIME
import com.tilseier.escapethemonster.data.model.PlaceState
import java.util.*

//TODO stars for level //achieve star1, star2, star3 position
@Entity(tableName = "levels")
class Level {

    @PrimaryKey
    var id: Int = 0

    var level: String = ""

    // TODO properly add places variable

    @ColumnInfo(name = "safe_places")
    @TypeConverters(DataConverter::class)
    var safePlaces: List<Place>? = null

    @ColumnInfo(name = "scary_places")
    @TypeConverters(DataConverter::class)
    var scaryPlaces: List<Place>? = null

    @ColumnInfo(name = "achievements")
    @TypeConverters(DataConverter::class)
    var achievements: Achievements = Achievements(Achievements.DEFAULT_ACHIEVEMENT_POSITION, Achievements.DEFAULT_ACHIEVEMENT_POSITION, Achievements.DEFAULT_ACHIEVEMENT_POSITION)

    var locked: Boolean = true

    var stars: Int = 0

    //TODO remove locked and stars to not change it when update level
    constructor(id: Int,
                level: String,
                safePlaces: List<Place>?,
                scaryPlaces: List<Place>?,
                achievements: Achievements = Achievements(Achievements.DEFAULT_ACHIEVEMENT_POSITION, Achievements.DEFAULT_ACHIEVEMENT_POSITION, Achievements.DEFAULT_ACHIEVEMENT_POSITION)) {//, locked: Boolean, stars: Int
        this.id = id
        this.level = level
        this.safePlaces = safePlaces
        this.scaryPlaces = scaryPlaces
        this.achievements = achievements
    }

}