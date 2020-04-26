package com.tilseier.escapethemonster.screens

import android.util.Log
import androidx.lifecycle.*
import com.tilseier.escapethemonster.models.Level
import com.tilseier.escapethemonster.models.PagerPlaces
import com.tilseier.escapethemonster.models.PlaceState
import com.tilseier.escapethemonster.screens.levels.LevelsRepository
import com.tilseier.escapethemonster.utils.Event


class LevelsViewModel : ViewModel() {
//    , LifecycleObserver

    //LiveData - can't be changed directly, only observed
    //MutableLiveData - can be changed and observed

    //Levels
    private var mLevels: MutableLiveData<List<Level>>? = null
    private var mLevelsPages: MutableLiveData<List<List<Level>>>? = null
    private var mRepo: LevelsRepository? = null
    private var _navigateToLevel: MutableLiveData<Event<Level>> = MutableLiveData<Event<Level>>()
    private var _navigateToGameEnd: MutableLiveData<Event<Boolean>> = MutableLiveData<Event<Boolean>>()
    private var _goAhead: MutableLiveData<Event<Boolean>> = MutableLiveData<Event<Boolean>>()
    private var _goBack: MutableLiveData<Event<Boolean>> = MutableLiveData<Event<Boolean>>()

    //Game Level
    private var mSelectedLevel: MutableLiveData<Level> = MutableLiveData<Level>()
    private var mPassedScaryPlaces: MutableLiveData<Int> = MutableLiveData<Int>()
//    private var mLevelPlaces: Queue<Place> = LinkedList()

    //    private var mCurrentPlace: Queue<Place>? = null
    private var mCurrentPagerPlaces: MutableLiveData<PagerPlaces> = MutableLiveData<PagerPlaces>()

    private var isGameOver = false
    private var isGameWin = false

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

    fun getPassedScaryPlaces(): LiveData<Int> = mPassedScaryPlaces

    fun navigateToLevel(): LiveData<Event<Level>> = _navigateToLevel

    fun navigateToGameEnd(): LiveData<Event<Boolean>> = _navigateToGameEnd

    fun goAhead(): LiveData<Event<Boolean>> = _goAhead

    fun goBack(): LiveData<Event<Boolean>> = _goBack

    fun setSelectedLevel(level: Level?) {
        mSelectedLevel.value = level
    }

    //Game Level
    fun getPagerPlaces(): LiveData<PagerPlaces> = mCurrentPagerPlaces

    fun startSelectedLevel() {
        //TODO shuffle mechanism
//        mSelectedLevel.value?.safePlaces?.let { mSelectedLevel.value?.levelPlaces?.addAll(it) }
//        mSelectedLevel.value?.scaryPlaces?.let { mSelectedLevel.value?.levelPlaces?.addAll(it) }

        isGameOver = false
        isGameWin = false

        mSelectedLevel.value?.startLevel()

        mCurrentPagerPlaces.value = mSelectedLevel.value?.currentPagerPlaces
        mPassedScaryPlaces.value = 0// mSelectedLevel.value?.passedScaryPlaces

//        nextPlace()
    }

    fun onPlaceGoAheadAnimationEnd() {
        //TODO THIS COMMENTED CODE ONLY FORE SWIPE GAME
//        if (mCurrentPagerPlaces.value?.nextPlace?.state == PlaceState.GAME_OVER_PLACE) {
//            isGameOver = true
//        }
//        if (mCurrentPagerPlaces.value?.currentPlace?.state == PlaceState.GAME_WIN_PLACE) {
//            isGameWin = true
//        }
//
//        if (!isGameWin || isGameOver) {
            if (!isGameOver) {
                val passed = mPassedScaryPlaces.value ?: 0
                mPassedScaryPlaces.value = passed + 1
                mSelectedLevel.value?.nextLevelPlace()
            } else {
                mSelectedLevel.value?.setGameOverPlaces()
            }
            mCurrentPagerPlaces.value = mSelectedLevel.value?.currentPagerPlaces
//        } else {
//            onGameEnd()
//        }
    }

    fun onPlaceGoBackAnimationEnd() {
        //TODO THIS COMMENTED CODE ONLY FORE SWIPE GAME
//        if (mCurrentPagerPlaces.value?.backPlace?.state == PlaceState.GAME_OVER_PLACE) {
//            isGameOver = true
//        }
//        if (mCurrentPagerPlaces.value?.currentPlace?.state == PlaceState.GAME_WIN_PLACE) {
//            isGameWin = true
//        }
//
//        if (!isGameWin || isGameOver) {
            if (!isGameOver) {
                val passed = mPassedScaryPlaces.value ?: 0
                mPassedScaryPlaces.value = passed - 1
                mSelectedLevel.value?.nextLevelPlace()
            } else {
                mSelectedLevel.value?.setGameOverPlaces()
            }
            mCurrentPagerPlaces.value = mSelectedLevel.value?.currentPagerPlaces
//        } else {
//            onGameEnd()
//        }
    }

    fun userClickGoAhead() {
        if (mCurrentPagerPlaces.value?.nextPlace?.state == PlaceState.GAME_OVER_PLACE) {
            isGameOver = true
        }
        if (mCurrentPagerPlaces.value?.currentPlace?.state == PlaceState.GAME_WIN_PLACE) {
            isGameWin = true
        }

        if (!isGameWin || isGameOver) {
            _goAhead.value = Event(true)
        } else {
            onGameEnd()
        }
    }

    fun userClickGoBack() {
        if (mCurrentPagerPlaces.value?.backPlace?.state == PlaceState.GAME_OVER_PLACE) {
            isGameOver = true
        }
        if (mCurrentPagerPlaces.value?.currentPlace?.state == PlaceState.GAME_WIN_PLACE) {
            isGameWin = true
        }

        if (!isGameWin || isGameOver) {
            _goBack.value = Event(true)
        } else {
            onGameEnd()
        }
    }

    //Events
    fun userClickOnLevel(level: Level) {
        _navigateToLevel.value = Event(level)
    }

    private fun onGameEnd() {//level: Level//TODO
        _navigateToGameEnd.value = Event(true)
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