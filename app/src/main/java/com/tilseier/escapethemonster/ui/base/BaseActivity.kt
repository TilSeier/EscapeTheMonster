package com.tilseier.escapethemonster.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tilseier.escapethemonster.Application
import com.tilseier.escapethemonster.di.components.ApplicationComponent

abstract class BaseActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        getApplicationComponent()?.inject(this)
        super.onCreate(savedInstanceState)
    }

    /**
     * Get the Main Application component for dependency injection.
     *
     * @return [ApplicationComponent]
     */
    protected fun getApplicationComponent(): ApplicationComponent? {
        return (application as Application).getApplicationComponent()
    }

}