package com.tilseier.escapethemonster.screens

import android.util.Log
import androidx.lifecycle.*
import com.tilseier.escapethemonster.models.Level
import com.tilseier.escapethemonster.screens.levels.LevelsRepository


class LevelsViewModel: ViewModel() {
//    , LifecycleObserver

    //LiveData - can't be changed directly, only observed
    //MutableLiveData - can be changed and observed
    private var mLevels: MutableLiveData<List<Level>>? = null
    private var mLevelsPages: MutableLiveData<List<List<Level>>>? = null
    private var mRepo: LevelsRepository? = null
    private var mSelectedLevel: MutableLiveData<Level> = MutableLiveData<Level>()
    private var mMoveNext: MutableLiveData<Boolean> = MutableLiveData<Boolean>()

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

    fun getLevels(): LiveData<List<Level>>? {
        return mLevels
    }

    fun getLevelsPages(): LiveData<List<List<Level>>>? {
        return mLevelsPages
    }

    fun getSelectedLevel(): LiveData<Level>{
        return mSelectedLevel
    }

    fun setSelectedLevel(level: Level?){
        mSelectedLevel.value = level
    }

    fun getMoveNext(): LiveData<Boolean>{
        return mMoveNext
    }

    fun setMoveNext(move: Boolean){
        mMoveNext.value = move
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