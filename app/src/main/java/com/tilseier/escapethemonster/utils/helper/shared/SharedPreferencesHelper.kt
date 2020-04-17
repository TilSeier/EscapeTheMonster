package com.tilseier.escapethemonster.utils.helper.shared

interface SharedPreferencesHelper {

    fun getLanguage(): String?

    fun setLanguage(language: String)

    fun isMusicOn(): Boolean

    fun setMusicOn(on: Boolean)

    fun isSoundOn(): Boolean

    fun setSoundOn(on: Boolean)

}