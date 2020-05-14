package com.tilseier.escapethemonster.ui.screen

import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.*
import com.tilseier.escapethemonster.data.model.Level
import com.tilseier.escapethemonster.data.model.PagerPlaces
import com.tilseier.escapethemonster.data.model.Place.Companion.INFINITE_TIME
import com.tilseier.escapethemonster.data.model.PlaceState
import com.tilseier.escapethemonster.repository.LevelsRepository
import com.tilseier.escapethemonster.utils.Event


class LevelsViewModel : ViewModel(), LifecycleObserver {

    //LiveData - can't be changed directly, only observed
    //MutableLiveData - can be changed and observed

    companion object {
        private const val MOVE_BACK_DURATION: Long = 600
        private const val MOVE_AHEAD_DURATION: Long = 1000
        private const val SCREAMER_DURATION: Long = 150
    }

    //Levels
    private var mLevels: MutableLiveData<List<Level>>? = null
    private var mLevelsPages: MutableLiveData<List<List<Level>>>? = null
    private var mRepo: LevelsRepository? = null

    //Events
    private var _navigateToLevel: MutableLiveData<Event<Level>> = MutableLiveData<Event<Level>>()
    private var _prepareLevelImages: MutableLiveData<Event<Level>> = MutableLiveData<Event<Level>>()
    private var _navigateToGameEnd: MutableLiveData<Event<Boolean>> = MutableLiveData<Event<Boolean>>()
    private var _goAhead: MutableLiveData<Event<Boolean>> = MutableLiveData<Event<Boolean>>()
    private var _goBack: MutableLiveData<Event<Boolean>> = MutableLiveData<Event<Boolean>>()
    private var _enableUserInteraction: MutableLiveData<Event<Boolean>> = MutableLiveData<Event<Boolean>>()
    private var _showScreamer: MutableLiveData<Event<Boolean>> = MutableLiveData<Event<Boolean>>()

    //Game Level
    private var mSelectedLevel: MutableLiveData<Level> = MutableLiveData<Level>()
    private var mPassedScaryPlaces: MutableLiveData<Int> = MutableLiveData<Int>()
//    private var mLevelPlaces: Queue<Place> = LinkedList()

    //    private var mCurrentPlace: Queue<Place>? = null
    private var mCurrentPagerPlaces: MutableLiveData<PagerPlaces> = MutableLiveData<PagerPlaces>()
    private var mTimeLeft: MutableLiveData<Long> = MutableLiveData<Long>()
    private var mMaxTime: MutableLiveData<Long> = MutableLiveData<Long>()
    private var mMoveBackDuration: MutableLiveData<Long> = MutableLiveData<Long>()
    private var mMoveAheadDuration: MutableLiveData<Long> = MutableLiveData<Long>()

    private var countDownTimer: CountDownTimer? = null

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

    //Events
    fun navigateToLevel(): LiveData<Event<Level>> = _navigateToLevel

    fun navigateToGameEnd(): LiveData<Event<Boolean>> = _navigateToGameEnd

    fun prepareLevelImages(): LiveData<Event<Level>> = _prepareLevelImages

    fun goAhead(): LiveData<Event<Boolean>> = _goAhead

    fun goBack(): LiveData<Event<Boolean>> = _goBack

    fun enableUserInteraction(): LiveData<Event<Boolean>> = _enableUserInteraction

    fun showScreamer(): LiveData<Event<Boolean>> = _showScreamer

    fun setSelectedLevel(level: Level?) {
        mSelectedLevel.value = level
    }

    //Game Level
    fun getPagerPlaces(): LiveData<PagerPlaces> = mCurrentPagerPlaces

    fun getTimeLeft(): LiveData<Long> = mTimeLeft

    fun getMaxTime(): LiveData<Long> = mMaxTime

    fun getMoveBackDuration(): LiveData<Long> = mMoveBackDuration

    fun getMoveAheadDuration(): LiveData<Long> = mMoveAheadDuration

    fun prepareStartSelectedLevel() {
        isGameOver = false
        isGameWin = false

        mSelectedLevel.value?.startLevel()

        mCurrentPagerPlaces.value = mSelectedLevel.value?.currentPagerPlaces
        mPassedScaryPlaces.value = 0// mSelectedLevel.value?.passedScaryPlaces

        mMoveAheadDuration.value = MOVE_AHEAD_DURATION
        mMoveBackDuration.value = MOVE_BACK_DURATION

        stopTimer()
        mTimeLeft.value = INFINITE_TIME//TODO check
        mMaxTime.value = INFINITE_TIME//TODO check

    }

    fun onPlaceGoAheadAnimationEnd() {
        //TODO enable user interaction
        _enableUserInteraction.value = Event(true)

        //TODO THIS COMMENTED CODE ONLY FORE SWIPE GAME
//        if (mCurrentPagerPlaces.value?.nextPlace?.state == PlaceState.GAME_OVER_PLACE) {
//            isGameOver = true
//        }
//        if (mCurrentPagerPlaces.value?.currentPlace?.state == PlaceState.GAME_WIN_PLACE) {
//            isGameWin = true
//        }
//
//        if (!isGameWin || isGameOver) {
        if (!isGameOver) {//TODO mb move to on click
            mSelectedLevel.value?.nextLevelPlaces()

            mMaxTime.value = mSelectedLevel.value?.currentPagerPlaces?.currentPlace?.milliseconds ?: INFINITE_TIME
            mTimeLeft.value = mMaxTime.value
            startTimer(mTimeLeft.value ?: INFINITE_TIME)

        } else {
            mSelectedLevel.value?.setGameOverPlaces()

            //TODO if screamer is allowed than show screamer else onGameEnd
            _showScreamer.value = Event(true)
        }
        mCurrentPagerPlaces.value = mSelectedLevel.value?.currentPagerPlaces
//        } else {
//            onGameEnd()
//        }
    }

    fun onPlaceGoBackAnimationEnd() {
        //TODO enable user interaction
        _enableUserInteraction.value = Event(true)

        //TODO THIS COMMENTED CODE ONLY FORE SWIPE GAME
//        if (mCurrentPagerPlaces.value?.backPlace?.state == PlaceState.GAME_OVER_PLACE) {
//            isGameOver = true
//        }
//        if (mCurrentPagerPlaces.value?.currentPlace?.state == PlaceState.GAME_WIN_PLACE) {
//            isGameWin = true
//        }
//
//        if (!isGameWin || isGameOver) {
        if (!isGameOver) {//TODO mb move to on click
            val passed = mPassedScaryPlaces.value ?: 0
            mPassedScaryPlaces.value = passed + 1
            mSelectedLevel.value?.nextLevelPlaces()

            mMaxTime.value = mSelectedLevel.value?.currentPagerPlaces?.currentPlace?.milliseconds ?: INFINITE_TIME
            mTimeLeft.value = mMaxTime.value
            startTimer(mTimeLeft.value ?: INFINITE_TIME)

        } else {
            mSelectedLevel.value?.setGameOverPlaces()

            //TODO if screamer is allowed than show screamer else onGameEnd
            _showScreamer.value = Event(true)
        }
        mCurrentPagerPlaces.value = mSelectedLevel.value?.currentPagerPlaces
//        } else {
//            onGameEnd()
//        }
    }

    fun userClickGoAhead() {
        //TODO disable user interaction
        _enableUserInteraction.value = Event(false)

        stopTimer()

        if (mCurrentPagerPlaces.value?.nextPlace?.state == PlaceState.GAME_OVER_PLACE) {
            isGameOver = true

            mMoveAheadDuration.value = SCREAMER_DURATION
            mMoveBackDuration.value = SCREAMER_DURATION
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
        //TODO disable user interaction
        _enableUserInteraction.value = Event(false)

        stopTimer()

        if (mCurrentPagerPlaces.value?.backPlace?.state == PlaceState.GAME_OVER_PLACE) {
            isGameOver = true

            mMoveAheadDuration.value = SCREAMER_DURATION
            mMoveBackDuration.value = SCREAMER_DURATION
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
    fun onLevelImagesReady(level: Level) {
        _navigateToLevel.value = Event(level)
    }

    fun userClickOnLevel(level: Level) {
        _prepareLevelImages.value = Event(level)
    }

    fun onScreamerEnd() {
        onGameEnd()
    }

    private fun onGameEnd() {//level: Level//TODO
        _navigateToGameEnd.value = Event(true)
    }

    private fun startTimer(milliseconds: Long) {//TODO counter
        if (milliseconds != INFINITE_TIME) {
            stopTimer()
            countDownTimer = object : CountDownTimer(milliseconds, 50) {//TODO set proper countDownInterval
                override fun onTick(millisUntilFinished: Long) {
                    Log.e("CountDownTimer", "seconds remaining: " + millisUntilFinished / 1000)
                    //here you can have your logic to set text to edittext
                    mTimeLeft.value = millisUntilFinished
                }

                override fun onFinish() {
                    Log.e("CountDownTimer", "DONE!")
                    mTimeLeft.value = 0

                    mMoveAheadDuration.value = SCREAMER_DURATION
                    mMoveBackDuration.value = SCREAMER_DURATION

                    //if screamer is allowed
//                    _showScreamer.value = Event(true)

                    //if screamer is not allowed
                    if (mCurrentPagerPlaces.value?.nextPlace?.state == PlaceState.GAME_OVER_PLACE) {
                        userClickGoAhead()
                    } else {
                        userClickGoBack()
                    }

                }
            }.start()
        } else {
            mTimeLeft.value = INFINITE_TIME
        }
    }

    private fun stopTimer() {
        countDownTimer?.cancel()
    }

//    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
//    fun onAny(source: LifecycleOwner?, event: Lifecycle.Event?) {
//        Log.e("LevelsViewModel", "ON_ANY: ${event?.name}")
//    }
//

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
        Log.e("LevelsViewModel", "ON_RESUME")


//        startTimer(mTimeLeft.value ?: INFINITE_TIME)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause() {
        Log.e("LevelsViewModel", "ON_PAUSE")
//        stopTimer()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop() {
        Log.e("LevelsViewModel", "ON_STOP")

    }

}