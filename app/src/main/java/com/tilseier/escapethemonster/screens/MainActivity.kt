package com.tilseier.escapethemonster.screens

import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.lifecycle.ViewModelProvider
import com.tilseier.escapethemonster.R
import com.tilseier.escapethemonster.base.BaseActivity

class MainActivity : BaseActivity() {

    private var mLevelsViewModel: LevelsViewModel? = null

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

        mLevelsViewModel = ViewModelProvider(this).get(LevelsViewModel::class.java)
        mLevelsViewModel?.let { lifecycle.addObserver(it) }

    }
}
