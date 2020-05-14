package com.tilseier.escapethemonster.di.components

import android.content.Context
import com.tilseier.escapethemonster.di.ApplicationContext
import com.tilseier.escapethemonster.di.DatabaseInfo
import com.tilseier.escapethemonster.di.modules.ApplicationModule
import com.tilseier.escapethemonster.di.modules.DatabaseModule
import com.tilseier.escapethemonster.ui.base.BaseActivity
import com.tilseier.escapethemonster.ui.screen.MainActivity
import com.tilseier.escapethemonster.ui.screen.SplashActivity
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = arrayOf(ApplicationModule::class, DatabaseModule::class))
interface ApplicationComponent {

    fun inject(activity: BaseActivity)
    fun inject(activity: MainActivity)
    fun inject(activity: SplashActivity)

    @ApplicationContext
    fun getContext(): Context

    @DatabaseInfo
    fun getDatabaseName(): String

}