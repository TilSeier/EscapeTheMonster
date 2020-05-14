package com.tilseier.escapethemonster.ui.screen

import android.os.Bundle
import android.util.Log
import com.google.gson.Gson
import com.tilseier.escapethemonster.R
import com.tilseier.escapethemonster.ui.base.BaseActivity
import com.tilseier.escapethemonster.data.model.Place
import com.tilseier.escapethemonster.utils.helper.shared.SharedPreferencesHelper
import javax.inject.Inject

class SplashActivity : BaseActivity() {

    @set:Inject
    var mGson: Gson? = null

    @set:Inject
    var mSharedPreferences: SharedPreferencesHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        getApplicationComponent()?.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        val place: Place = Place()
        place.imageUrl = "SOME IMAGE URL"
        place.isMonster = true
        Log.e("SPLASH_ACTIVITY", "${mGson?.toJson(place)}")

        mSharedPreferences?.setLanguage("UA")
        mSharedPreferences?.setMusicOn(false)
        mSharedPreferences?.setSoundOn(true)

        Log.e("SPLASH_ACTIVITY", "Language: ${mSharedPreferences?.getLanguage()}; MusicOn: ${mSharedPreferences?.isMusicOn()}; SoundOn: ${mSharedPreferences?.isSoundOn()};")

    }

}
