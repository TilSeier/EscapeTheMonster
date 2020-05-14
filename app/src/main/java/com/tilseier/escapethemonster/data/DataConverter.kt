package com.tilseier.escapethemonster.data

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tilseier.escapethemonster.data.model.Place

class DataConverter {

    var gson = Gson()

    @TypeConverter
    fun fromCountryLangList(value: List<Place>): String {
//        val gson = Gson()
//        val type = object : TypeToken<List<Place>>() {}.type
        return gson.toJson(value)//, type
    }

    @TypeConverter
    fun toCountryLangList(value: String): List<Place> {
//        val gson = Gson()
        val type = object : TypeToken<List<Place>>() {}.type
        return gson.fromJson(value, type)
    }

}