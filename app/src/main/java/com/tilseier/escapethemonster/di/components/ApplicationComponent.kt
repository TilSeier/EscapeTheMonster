package com.tilseier.escapethemonster.di.components

import com.tilseier.escapethemonster.screens.MainActivity
import com.tilseier.escapethemonster.screens.SplashActivity
import com.tilseier.escapethemonster.base.BaseActivity
import com.tilseier.escapethemonster.di.modules.ApplicationModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {

    fun inject(activity: BaseActivity)
    fun inject(activity: MainActivity)
    fun inject(activity: SplashActivity)

}