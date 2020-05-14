package com.tilseier.escapethemonster.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


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

    @Insert
    fun addLevels(levels: List<Level>)

    @Query("SELECT * FROM levels WHERE id = :id")
    fun getLevel(id: Int): Level?

    @Query("SELECT * FROM levels")
    fun getLevels(): List<Level>?

    @Query("DELETE FROM levels")//TODO mb we don't need this
    fun deleteAllLevels()

    @Query("UPDATE levels SET stars = :countOfStars WHERE id = :id")
    fun updateLevelStars(id: Int, countOfStars: Int)

    @Query("UPDATE levels SET locked = :locked WHERE id = :id")
    fun updateLevelLocked(id: Int, locked: Boolean)


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