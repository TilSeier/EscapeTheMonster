package com.tilseier.escapethemonster.screens

import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import com.tilseier.escapethemonster.R
import com.tilseier.escapethemonster.base.BaseActivity

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        getApplicationComponent()?.inject(this)
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_main)

        //TODO fragments navigation

    }
}
