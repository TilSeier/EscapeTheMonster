package com.tilseier.escapethemonster.di.modules

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.tilseier.escapethemonster.Application
import com.tilseier.escapethemonster.R
import com.tilseier.escapethemonster.di.ApplicationContext
import com.tilseier.escapethemonster.utils.helper.shared.SharedPreferencesHelper
import com.tilseier.escapethemonster.utils.helper.shared.SharedPreferencesHelperImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule constructor(val application: Application) {

    @Singleton
    @Provides
    @ApplicationContext
    fun provideContext(): Context {
        return application
    }

    @Provides
    @Singleton
    fun provideSharedPreference(): SharedPreferences {
        return application.getSharedPreferences(
            application.getString(R.string.preference_file_key),
            Context.MODE_PRIVATE
        )
    }

    @Provides
    @Singleton
    fun provideSharedPreferencesHelperImpl(sharedPreferences: SharedPreferences): SharedPreferencesHelper? {
        return SharedPreferencesHelperImpl(sharedPreferences)
    }

    @Provides
    @Singleton
    fun provideGson(): Gson? {
        val gsonBuilder = GsonBuilder()
//        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        return gsonBuilder.create()
    }

}