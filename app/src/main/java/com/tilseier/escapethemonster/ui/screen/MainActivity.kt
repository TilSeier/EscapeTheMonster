package com.tilseier.escapethemonster.ui.screen

import android.os.Bundle
import android.util.Log
import android.view.Window
import android.view.WindowManager
import androidx.lifecycle.ViewModelProvider
import com.google.gson.Gson
import com.tilseier.escapethemonster.R
import com.tilseier.escapethemonster.data.database.AppDatabase
import com.tilseier.escapethemonster.data.model.Place
import com.tilseier.escapethemonster.ui.base.BaseActivity
import javax.inject.Inject


class MainActivity : BaseActivity() {

    private lateinit var mLevelsViewModel: LevelsViewModel

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
        mLevelsViewModel.let { lifecycle.addObserver(it) }
    }

}
