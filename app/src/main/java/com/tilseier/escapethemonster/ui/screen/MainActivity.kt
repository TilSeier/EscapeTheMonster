package com.tilseier.escapethemonster.ui.screen

import android.os.Bundle
import android.util.Log
import android.view.Window
import android.view.WindowManager
import androidx.lifecycle.ViewModelProvider
import com.google.gson.Gson
import com.tilseier.escapethemonster.R
import com.tilseier.escapethemonster.data.database.AppDatabase
import com.tilseier.escapethemonster.data.database.Level
import com.tilseier.escapethemonster.data.model.Place
import com.tilseier.escapethemonster.ui.base.BaseActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import javax.inject.Inject


class MainActivity : BaseActivity() {

    @set:Inject
    var mAppDatabase: AppDatabase? = null

    @set:Inject
    var mGson: Gson? = null

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

        //TODO run on new Thread
        //TODO change to coroutines
        //TODO JUST FOR TEST SO DELETE ALL
        Log.e("TEST_LEVEL", "LEVELS 1")
        Thread {
            mAppDatabase?.levelsDao()?.deleteAllLevels()
            Log.e("TEST_LEVEL", "LEVELS 2")
            val level1 = Level()
            level1.level = "1"
            level1.locked = false
            level1.stars = 3
            level1.safePlaces = listOf(
                Place("https://i.ibb.co/2vCGcVw/y-img-10-lvl-16.jpg", false),
                Place("https://i.ibb.co/T28VtK9/y-img-10-lvl-17.jpg", false),
                Place("https://i.ibb.co/5WPdnfj/y-img-10-lvl-18.jpg", false),
                Place("https://i.ibb.co/v44mVkf/y-img-10-lvl-19.jpg", false),
                Place("https://i.ibb.co/1z9x85j/y-img-10-lvl-20.jpg", false)
            )
            level1.scaryPlaces = listOf(
                Place("https://i.ibb.co/R4QJd1y/n-img-1-lvl-1.jpg", true),
                Place("https://i.ibb.co/rm5MgL2/n-img-1-lvl-2.jpg", true),
                Place("https://i.ibb.co/jM8hzym/n-img-1-lvl-3.jpg", true),
                Place("https://i.ibb.co/6rk8KK5/n-img-1-lvl-4.jpg", true),
                Place("https://i.ibb.co/c8wMgq3/n-img-1-lvl-5.jpg", true)
            )

            mAppDatabase?.levelsDao()?.addLevel(level1)

            Log.e("TEST_LEVEL", "LEVELS 3: ${mGson?.toJson(mAppDatabase?.levelsDao()?.getLevels())}")

        }.start()
    }

}
