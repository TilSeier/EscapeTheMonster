package com.tilseier.escapethemonster.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.tilseier.escapethemonster.data.DataConverter
import com.tilseier.escapethemonster.data.model.Place


@Entity(tableName = "levels")
class Level {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    var level: String = ""

    var locked: Boolean = true

    var stars: Int = 0

    @ColumnInfo(name = "safe_places")
    @TypeConverters(DataConverter::class)
    var safePlaces: List<Place>? = null

    @ColumnInfo(name = "scary_places")
    @TypeConverters(DataConverter::class)
    var scaryPlaces: List<Place>? = null

}