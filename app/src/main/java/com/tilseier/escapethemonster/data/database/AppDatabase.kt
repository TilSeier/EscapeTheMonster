package com.tilseier.escapethemonster.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// Annotates class to be a Room Database with a table (entity) of the Level class
@Database(
    entities = [Level::class],
    version = 1
) //, exportSchema = false
abstract class AppDatabase : RoomDatabase() {

    abstract fun levelsDao(): LevelDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: AppDatabase? = null

        val DBName = "escape_the_monster.db"

        fun getDatabase(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DBName
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}
//TODO don't provide now, but use INSTANCE https://codelabs.developers.google.com/codelabs/android-room-with-a-view-kotlin/#8
//TODO provide with dagger !!! and check