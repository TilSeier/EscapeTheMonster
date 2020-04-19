package com.tilseier.escapethemonster

import android.app.Application
import com.tilseier.escapethemonster.di.components.ApplicationComponent
import com.tilseier.escapethemonster.di.components.DaggerApplicationComponent
import com.tilseier.escapethemonster.di.modules.ApplicationModule

class Application: Application() {

    private var applicationComponent: ApplicationComponent? = null

    override fun onCreate() {
        super.onCreate()
        initializeInjector()
    }

    private fun initializeInjector() {
        applicationComponent = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }

    fun getApplicationComponent(): ApplicationComponent? {
        return applicationComponent
    }

}