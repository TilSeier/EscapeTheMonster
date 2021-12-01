package com.tilseier.escapethemonster.data.database

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.tilseier.escapethemonster.data.HardcodedLevels
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


// Annotates class to be a Room Database with a table (entity) of the Level class
@Database(
    entities = [Level::class],
    version = HardcodedLevels.HARDCODED_LEVELS_VERSION
) //, exportSchema = false
abstract class AppDatabase : RoomDatabase() {

    abstract fun levelsDao(): LevelDao

    private class AppDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            Log.e("TAG_DATABASE", "onOpen 0 CREATE_DATABASE")
            INSTANCE?.let { database ->
                scope.launch(Dispatchers.IO) {
                    populateDatabase(database.levelsDao())
                }
            }
        }

        override fun onDestructiveMigration(db: SupportSQLiteDatabase) {
            super.onDestructiveMigration(db)
            Log.e("TAG_DATABASE", "onDestructiveMigration 0 CREATE_DATABASE")
        }

        fun populateDatabase(levelDao: LevelDao) {

            Log.e("TAG_DATABASE", "onCreate 1 CREATE_DATABASE")
            // Delete all content here.
//            levelDao.deleteAllLevels()

            val levels: List<Level> = HardcodedLevels.getAllLevels()

//            levelDao.addLevels(levels)
            levelDao.addOrUpdateLevels(levels)

            Log.e("TAG_DATABASE", "onCreate 3 CREATE_DATABASE")

        }

    }

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        val DBName = "escape_the_monster.db"

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): AppDatabase {
            Log.e("TAG_DATABASE", "getDatabase 0 CREATE_DATABASE")
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                Log.e("TAG_DATABASE", "getDatabase 1 CREATE_DATABASE")
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        DBName
                    )
                    .addCallback(AppDatabaseCallback(scope))//TODO https://stackoverflow.com/questions/47619718/room-database-not-created/47619844#47619844
//                    .addMigrations(MIGRATION_1_2)//migration if needed
//                    .fallbackToDestructiveMigration()//instead of migration, but database will be recreated and all data lost
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }

        //magrations
//        val MIGRATION_1_2: Migration = object : Migration(1, 2) {
//            override fun migrate(database: SupportSQLiteDatabase) {
//                // Since we didn't alter the table, there's nothing else to do here.
//                database.execSQL("ALTER TABLE levels "
//                        + " ADD COLUMN achievements TEXT")
//            }
//        }

    }

}
//TODO don't provide now, but use INSTANCE https://codelabs.developers.google.com/codelabs/android-room-with-a-view-kotlin/#8
//TODO provide with dagger !!! and check