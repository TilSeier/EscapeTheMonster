package com.tilseier.escapethemonster.utils.helper.shared

import android.content.SharedPreferences
import com.tilseier.escapethemonster.utils.Const

//@Inject

class SharedPreferencesHelperImpl  constructor(val sharedPreferences: SharedPreferences) :
    SharedPreferencesHelper {
    override fun getLanguage(): String? {
        return sharedPreferences.getString(Const.KEY_LANGUAGE, "EN")
    }

    override fun setLanguage(language: String) {
        sharedPreferences.edit().putString(Const.KEY_LANGUAGE, language).apply()
    }

    override fun isMusicOn(): Boolean {
        return sharedPreferences.getBoolean(Const.KEY_IS_MUSIC_ON, true)
    }

    override fun setMusicOn(on: Boolean) {
        sharedPreferences.edit().putBoolean(Const.KEY_IS_MUSIC_ON, on).apply()
    }

    override fun isSoundOn(): Boolean {
        return sharedPreferences.getBoolean(Const.KEY_IS_SOUNDS_ON, true)
    }

    override fun setSoundOn(on: Boolean) {
        sharedPreferences.edit().putBoolean(Const.KEY_IS_SOUNDS_ON, on).apply()
    }

    //    @Inject
//    fun SharedPreferencesHelperImpl(sharedPreferences: SharedPreferences?) {
//        this.sharedPreferences = sharedPreferences!!
//    }

}