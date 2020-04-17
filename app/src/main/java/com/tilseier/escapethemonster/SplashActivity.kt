package com.tilseier.escapethemonster

import android.os.Bundle
import android.util.Log
import com.google.gson.Gson
import com.tilseier.escapethemonster.base.BaseActivity
import com.tilseier.escapethemonster.models.Place
import javax.inject.Inject

class SplashActivity : BaseActivity() {

    @set:Inject
    var gson: Gson? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        getApplicationComponent()?.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        var place: Place = Place()
        place.imageUrl = "SOME IMAGE URL";
        place.isMonster = true;
        Log.e("SPLASH_ACTIVITY", "${gson?.toJson(place)}")
    }


}
