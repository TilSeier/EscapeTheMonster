package com.tilseier.escapethemonster.screens

import android.util.Log
import androidx.lifecycle.*
import com.tilseier.escapethemonster.models.Level
import com.tilseier.escapethemonster.screens.levels.LevelsRepository
import com.tilseier.escapethemonster.utils.Event


class LevelsViewModel: ViewModel() {
//    , LifecycleObserver

    //LiveData - can't be changed directly, only observed
    //MutableLiveData - can be changed and observed
    private var mLevels: MutableLiveData<List<Level>>? = null
    private var mLevelsPages: MutableLiveData<List<List<Level>>>? = null
    private var mRepo: LevelsRepository? = null
    private var mSelectedLevel: MutableLiveData<Level> = MutableLiveData<Level>()
    private var _navigateToLevel: MutableLiveData<Event<Level>> = MutableLiveData<Event<Level>>()

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

    fun getLevels(): LiveData<List<Level>>? = mLevels

    fun getLevelsPages(): LiveData<List<List<Level>>>? = mLevelsPages

    fun getSelectedLevel(): LiveData<Level> = mSelectedLevel

    fun navigateToLevel(): LiveData<Event<Level>> = _navigateToLevel

    fun setSelectedLevel(level: Level?){
        mSelectedLevel.value = level
    }

    fun userClickOnLevel(level: Level){
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