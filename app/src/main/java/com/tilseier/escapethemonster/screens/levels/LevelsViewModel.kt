package com.tilseier.escapethemonster.screens.levels

import android.util.Log
import androidx.lifecycle.*
import com.tilseier.escapethemonster.models.Level


class LevelsViewModel: ViewModel(), LifecycleObserver {

    //LiveData - can't be changed directly, only observed
    //MutableLiveData - can be changed and observed
    private var mLevels: MutableLiveData<List<Level>>? = null
    private var mLevelsPages: MutableLiveData<List<List<Level>>>? = null
    private var mRepo: LevelsRepository? = null

    fun init() {
        if (mLevels != null) {
            return
        }
        mRepo = LevelsRepository.newInstance()
        mLevels = mRepo?.getLevels()
        mLevelsPages = mRepo?.getLevelsPages()
    }

    fun getLevels(): LiveData<List<Level>>? {
        return mLevels
    }

    fun getLevelsPages(): LiveData<List<List<Level>>>? {
        return mLevelsPages
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    fun onAny(source: LifecycleOwner?, event: Lifecycle.Event?) {
        Log.e("LevelsViewModel", "ON_ANY: ${event?.name}")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume(){
        Log.e("LevelsViewModel", "ON_RESUME")
    }

}