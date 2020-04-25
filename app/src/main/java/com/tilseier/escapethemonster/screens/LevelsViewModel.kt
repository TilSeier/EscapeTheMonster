package com.tilseier.escapethemonster.screens

import android.util.Log
import androidx.lifecycle.*
import com.tilseier.escapethemonster.models.Level
import com.tilseier.escapethemonster.models.PagerPlaces
import com.tilseier.escapethemonster.models.Place
import com.tilseier.escapethemonster.screens.levels.LevelsRepository
import com.tilseier.escapethemonster.utils.Event
import java.util.*


class LevelsViewModel : ViewModel() {
//    , LifecycleObserver

    //LiveData - can't be changed directly, only observed
    //MutableLiveData - can be changed and observed

    //Levels
    private var mLevels: MutableLiveData<List<Level>>? = null
    private var mLevelsPages: MutableLiveData<List<List<Level>>>? = null
    private var mRepo: LevelsRepository? = null
    private var _navigateToLevel: MutableLiveData<Event<Level>> = MutableLiveData<Event<Level>>()

    //Game Level
    private var mSelectedLevel: MutableLiveData<Level> = MutableLiveData<Level>()
    private var mLevelPlaces: Queue<Place>? = null

    //    private var mCurrentPlace: Queue<Place>? = null
    private var mCurrentPagerPlaces: MutableLiveData<PagerPlaces> = MutableLiveData<PagerPlaces>()

    init {
        if (mLevels == null) {
            Log.e("LevelsViewModel", "INIT")
            mRepo =
                LevelsRepository.newInstance()
            mLevels = mRepo?.getLevels()
            mLevelsPages = mRepo?.getLevelsPages()
//            mSelectedLevel = mRepo?.getSelectedLevel()//TODO
        }
    }

    //Levels
    fun getLevels(): LiveData<List<Level>>? = mLevels

    fun getLevelsPages(): LiveData<List<List<Level>>>? = mLevelsPages

    fun getSelectedLevel(): LiveData<Level> = mSelectedLevel

    fun navigateToLevel(): LiveData<Event<Level>> = _navigateToLevel

    fun setSelectedLevel(level: Level?) {
        mSelectedLevel.value = level
    }

    //Game Level
    fun startGame() {
        //TODO
    }


    //Events
    fun userClickOnLevel(level: Level) {
        _navigateToLevel.value = Event(level)
    }

//    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
//    fun onAny(source: LifecycleOwner?, event: Lifecycle.Event?) {
//        Log.e("LevelsViewModel", "ON_ANY: ${event?.name}")
//    }
//
//    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
//    fun onResume(){
//        Log.e("LevelsViewModel", "ON_RESUME")
//    }

}