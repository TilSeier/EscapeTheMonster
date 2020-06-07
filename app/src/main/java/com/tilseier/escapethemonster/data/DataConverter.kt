package com.tilseier.escapethemonster.data

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tilseier.escapethemonster.data.model.Achievements
import com.tilseier.escapethemonster.data.model.Place

class DataConverter {

    var gson = Gson()

    @TypeConverter
    fun fromPlaceList(value: List<Place>): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toPlaceList(value: String): List<Place> {
        val type = object : TypeToken<List<Place>>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun fromAchievements(value: Achievements?): String? {//TODO check
        return gson.toJson(value)
    }

    @TypeConverter
    fun toAchievements(value: String?): Achievements? {//TODO check
//        val type = object : TypeToken<Achievements>() {}.type
        return gson.fromJson(value, Achievements::class.java)
    }

}