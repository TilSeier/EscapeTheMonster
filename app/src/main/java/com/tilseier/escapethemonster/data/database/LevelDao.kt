package com.tilseier.escapethemonster.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.google.gson.Gson


@Dao
interface LevelDao {
//    @Insert
//    fun addLevel(level: Level): Long //returns id

//    @Insert
//    fun insert(levels: List<Level>?): LongArray? //returns ids

//    @Insert(onConflict = OnConflictStrategy.REPLACE) //replace level if needed
//    fun addLevel(level: Level)

    @Insert
    fun addLevel(level: Level)

//    @Insert
//    fun addLevels(vararg levels: Level?)

    @Insert//(onConflict = OnConflictStrategy.REPLACE)
    fun addLevels(levels: List<Level>)

    @Query("SELECT * FROM levels WHERE id = :id")
    fun getLevel(id: Int): Level?

    @Query("SELECT * FROM levels")
    fun getLevels(): LiveData<List<Level>>

    @Query("DELETE FROM levels")//TODO mb we don't need this
    fun deleteAllLevels()

    @Query("UPDATE levels SET stars = :countOfStars WHERE id = :id")
    fun updateLevelStars(id: Int, countOfStars: Int)

    @Query("UPDATE levels SET locked = :locked WHERE id = :id")
    fun updateLevelLock(id: Int, locked: Boolean)

//    @Update
//    fun update(levels: List<Level>)
//

//    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    fun addLevelsIgnore(levels: List<Level>): Long

    @Update
    fun updateLevel(level: Level)

//    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    fun addLevelIgnore(levels: Level): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addLevelIgnore(level: Level) : Long

    @Update
    fun updateLevel(levels: List<Level>)

    //(onConflict = OnConflictStrategy.REPLACE)
//    @Update
//    fun updateLevels(levels: List<Level>)

    @Query("UPDATE levels SET level = :level, seconds = :seconds, safe_places = :safePlaces, scary_places = :scaryPlaces, achievements = :achievements WHERE id = :id")
    fun updateLevel(id: Int, level: String, seconds: Long, safePlaces: String, scaryPlaces: String, achievements: String)

//    var level: String = ""
//
//    @ColumnInfo(name = "safe_places")
//    @TypeConverters(DataConverter::class)
//    var safePlaces: List<Place>? = null
//
//    @ColumnInfo(name = "scary_places")
//    @TypeConverters(DataConverter::class)
//    var scaryPlaces: List<Place>? = null
//
//    @ColumnInfo(name = "achievements")
//    @TypeConverters(DataConverter::class)
//    var achievements

    @Transaction
    fun addOrUpdateLevels(levels: List<Level>) {
        val gson = Gson()
        for (level in levels) {
            if (addLevelIgnore(level) == -1L) {
                updateLevel(level.id, level.level, level.seconds, gson.toJson(level.safePlaces), gson.toJson(level.scaryPlaces), gson.toJson(level.achievements))
            }
        }
    }


//    @get:Query("select * from favourite_web ORDER BY id DESC")
//    val favouriteWeb: List<Any?>?
//
//    @Query("DELETE FROM favourite_web WHERE web_url = :webURL")
//    fun deleteFavouriteWeb(webURL: String?)
//
//    @Query("SELECT COUNT(id) FROM favourite_web WHERE web_url = :webURL")
//    fun getNumberByURL(webURL: String?): Int
//
//    @get:Query("SELECT COUNT(id) FROM favourite_web")
//    val countOfFavouritesWeb: Int
}