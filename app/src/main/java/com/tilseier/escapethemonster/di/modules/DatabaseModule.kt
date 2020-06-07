package com.tilseier.escapethemonster.di.modules

import android.content.Context
import androidx.room.Room
import com.tilseier.escapethemonster.data.database.AppDatabase
import com.tilseier.escapethemonster.data.database.LevelDao
import com.tilseier.escapethemonster.di.ApplicationContext
import com.tilseier.escapethemonster.di.DatabaseInfo
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class DatabaseModule(@field:ApplicationContext @param:ApplicationContext private val mContext: Context) {

    @Singleton
    @Provides
    fun provideDatabase(): AppDatabase {
//        return AppDatabase.getDatabase(mContext)
        return Room.databaseBuilder(
            mContext,
            AppDatabase::class.java,
            "aasfhk"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @DatabaseInfo
    fun provideDatabaseName(): String {
        return AppDatabase.DBName
    }

    @Singleton
    @Provides
    fun provideLevelDao(db: AppDatabase): LevelDao {
        return db.levelsDao()
    }

}