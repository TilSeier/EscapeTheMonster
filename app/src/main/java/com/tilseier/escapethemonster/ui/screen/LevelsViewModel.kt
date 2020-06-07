package com.tilseier.escapethemonster.ui.screen

import android.app.Application
import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.*
import com.tilseier.escapethemonster.data.database.AppDatabase
import com.tilseier.escapethemonster.data.database.Level
import com.tilseier.escapethemonster.data.model.Achievements
import com.tilseier.escapethemonster.data.model.PagerPlaces
import com.tilseier.escapethemonster.data.model.Place
import com.tilseier.escapethemonster.data.model.Place.Companion.INFINITE_TIME
import com.tilseier.escapethemonster.data.model.PlaceState
import com.tilseier.escapethemonster.repository.LevelsRepository
import com.tilseier.escapethemonster.utils.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*


class LevelsViewModel(application: Application) : AndroidViewModel(application), LifecycleObserver {

    //LiveData - can't be changed directly, only observed
    //MutableLiveData - can be changed and observed

    companion object {
        private const val MOVE_BACK_DURATION: Long = 600
        private const val MOVE_AHEAD_DURATION: Long = 1000
        private const val SCREAMER_DURATION: Long = 150
    }

    //Levels
    private var mLevels: LiveData<List<Level>>? = null
    private var mRepo: LevelsRepository? = null

    //Events
    private var _navigateToLevel: MutableLiveData<Event<Level>> = MutableLiveData<Event<Level>>()
    private var _userClickOnLevel: MutableLiveData<Event<Level>> = MutableLiveData<Event<Level>>()
    private var _navigateToGameEnd: MutableLiveData<Event<Boolean>> = MutableLiveData<Event<Boolean>>()
    private var _goAhead: MutableLiveData<Event<Boolean>> = MutableLiveData<Event<Boolean>>()
    private var _goBack: MutableLiveData<Event<Boolean>> = MutableLiveData<Event<Boolean>>()
    private var _enableUserInteraction: MutableLiveData<Event<Boolean>> = MutableLiveData<Event<Boolean>>()
    private var _showScreamer: MutableLiveData<Event<Boolean>> = MutableLiveData<Event<Boolean>>()

    //Game Level
    private var mSelectedLevel: MutableLiveData<Level> = MutableLiveData<Level>()
    private var mPassedScaryPlaces: MutableLiveData<Int> = MutableLiveData<Int>()
    private var mLevelPlacesQueue: Queue<Place> = LinkedList()
    private var mCurrentPagerPlaces: MutableLiveData<PagerPlaces> = MutableLiveData<PagerPlaces>()
    private var mTimeLeft: MutableLiveData<Long> = MutableLiveData<Long>()
    private var mMaxTime: MutableLiveData<Long> = MutableLiveData<Long>()
    private var mMoveBackDuration: MutableLiveData<Long> = MutableLiveData<Long>()
    private var mMoveAheadDuration: MutableLiveData<Long> = MutableLiveData<Long>()

    //Timer
    private var countDownTimer: CountDownTimer? = null

    //Game state
    private var isGameOver = false
    private var isGameWin = false

    init {
        if (mLevels == null) {
            val levelsDao = AppDatabase.getDatabase(application, viewModelScope).levelsDao()
            mRepo =
                LevelsRepository.newInstance(levelsDao)
            mLevels = mRepo?.getLevels()

//            mSelectedLevel = mRepo?.getSelectedLevel()//TODO
        }
    }

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    private fun insert(level: Level) = viewModelScope.launch(Dispatchers.IO) {
        mRepo?.insert(level)
    }

    private fun updateLevelStars(id: Int, stars: Int) = viewModelScope.launch(Dispatchers.IO) {
        mRepo?.updateLevelStars(id, stars)
    }

    private fun updateLevelLock(id: Int, locked: Boolean) = viewModelScope.launch(Dispatchers.IO) {
        mRepo?.updateLevelLock(id, locked)
    }

    private fun updateLevel(level: Level) = viewModelScope.launch(Dispatchers.IO) {
        mRepo?.updateLevel(level)
    }

    //Levels
    fun getLevels(): LiveData<List<Level>>? = mLevels

    fun getSelectedLevel(): LiveData<Level> = mSelectedLevel

    fun getPassedScaryPlaces(): LiveData<Int> = mPassedScaryPlaces

    fun setSelectedLevel(level: Level?) {
        mSelectedLevel.value = level
    }

    fun setSelectedLevelAchievements(achievements: Achievements) {
        mSelectedLevel.value?.achievements = achievements
    }

    //Events
    fun navigateToLevel(): LiveData<Event<Level>> = _navigateToLevel

    fun navigateToGameEnd(): LiveData<Event<Boolean>> = _navigateToGameEnd

    fun userClickOnLevel(): LiveData<Event<Level>> = _userClickOnLevel

    fun goAhead(): LiveData<Event<Boolean>> = _goAhead

    fun goBack(): LiveData<Event<Boolean>> = _goBack

    fun enableUserInteraction(): LiveData<Event<Boolean>> = _enableUserInteraction

    fun showScreamer(): LiveData<Event<Boolean>> = _showScreamer

    //Game Level
    fun getPagerPlaces(): LiveData<PagerPlaces> = mCurrentPagerPlaces

    fun getTimeLeft(): LiveData<Long> = mTimeLeft

    fun getMaxTime(): LiveData<Long> = mMaxTime

    fun getMoveBackDuration(): LiveData<Long> = mMoveBackDuration

    fun getMoveAheadDuration(): LiveData<Long> = mMoveAheadDuration

    fun prepareStartSelectedLevel() {
        isGameOver = false
        isGameWin = false

        startLevel()

        mMoveAheadDuration.value = MOVE_AHEAD_DURATION
        mMoveBackDuration.value = MOVE_BACK_DURATION

        stopTimer()
        mTimeLeft.value = INFINITE_TIME
        mMaxTime.value = INFINITE_TIME

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
            nextLevelPlaces()

            mMaxTime.value = mCurrentPagerPlaces.value?.currentPlace?.milliseconds ?: INFINITE_TIME
            mTimeLeft.value = mMaxTime.value
            startTimer(mTimeLeft.value ?: INFINITE_TIME)

        } else {
            setGameOverPlaces()

            //TODO if screamer is allowed than show screamer else onGameEnd
            _showScreamer.value = Event(true)
        }
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
            nextLevelPlaces()

            mMaxTime.value = mCurrentPagerPlaces.value?.currentPlace?.milliseconds ?: INFINITE_TIME
            mTimeLeft.value = mMaxTime.value
            startTimer(mTimeLeft.value ?: INFINITE_TIME)

        } else {
            setGameOverPlaces()

            //TODO if screamer is allowed than show screamer else onGameEnd
            _showScreamer.value = Event(true)
        }
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

    //game controls
    private fun startLevel() {
        //TODO shuffle mechanism
        mLevelPlacesQueue.clear()
        mSelectedLevel.value?.safePlaces?.let { mLevelPlacesQueue.addAll(it) }
        mSelectedLevel.value?.scaryPlaces?.let { mLevelPlacesQueue.addAll(it) }
        mLevelPlacesQueue.add(
            Place(
                "",
                false,
                3000,
                PlaceState.GAME_WIN_PLACE
            )
        )

        mPassedScaryPlaces.value = 0

        nextLevelPlaces()
    }

    private fun nextLevelPlaces() {
        //TODO logic

        val currentPlace = mLevelPlacesQueue.poll()
        val backPlace = if (currentPlace?.isMonster == true) mLevelPlacesQueue.peek() else Place(
            "",
            true,
            INFINITE_TIME,
            PlaceState.GAME_OVER_PLACE
        )
        val nextPlace = if (currentPlace?.isMonster == false) mLevelPlacesQueue.peek() else Place(
            "",
            true,
            INFINITE_TIME,
            PlaceState.GAME_OVER_PLACE
        )
        mCurrentPagerPlaces.value =
            PagerPlaces(
                backPlace,
                currentPlace,
                nextPlace
            )
    }

    private fun setGameOverPlaces() {
        mCurrentPagerPlaces.value =
            PagerPlaces(
                Place(
                    "",
                    true,
                    INFINITE_TIME,
                    PlaceState.GAME_OVER_PLACE
                ),
                Place(
                    "",
                    true,
                    INFINITE_TIME,
                    PlaceState.GAME_OVER_PLACE
                ),
                Place(
                    "",
                    true,
                    INFINITE_TIME,
                    PlaceState.GAME_OVER_PLACE
                )
            )
    }

    //Events
    fun onLevelImagesReady(level: Level) {
        _navigateToLevel.value = Event(level)
    }

    fun userClickOnLevel(level: Level) {
        _userClickOnLevel.value = Event(level)
    }

    fun onScreamerEnd() {
        onGameEnd()
    }

    private fun onGameEnd() {//level: Level//TODO
        Log.e("LevelsViewModel", "onGameEnd")

        updateCurrentLevel()

        _navigateToGameEnd.value = Event(true)
    }

    private fun updateCurrentLevel() {
        var countOfAchievements = 0
        val countOfCurrentLevelAchievements = mSelectedLevel.value?.stars ?: 0

        val passedScaryPlaces = mPassedScaryPlaces.value ?: 0
        val achievementPosition1 = mSelectedLevel.value?.achievements?.achievementPosition1
            ?: Achievements.DEFAULT_ACHIEVEMENT_POSITION
        val achievementPosition2 = mSelectedLevel.value?.achievements?.achievementPosition2
            ?: Achievements.DEFAULT_ACHIEVEMENT_POSITION
        val achievementPosition3 = mSelectedLevel.value?.achievements?.achievementPosition3
            ?: Achievements.DEFAULT_ACHIEVEMENT_POSITION
        if (achievementPosition1 != Achievements.DEFAULT_ACHIEVEMENT_POSITION
            && passedScaryPlaces >= achievementPosition1
        ) {
            countOfAchievements++
        }
        if (achievementPosition2 != Achievements.DEFAULT_ACHIEVEMENT_POSITION
            && passedScaryPlaces >= achievementPosition2
        ) {
            countOfAchievements++
        }
        if (achievementPosition3 != Achievements.DEFAULT_ACHIEVEMENT_POSITION
            && passedScaryPlaces >= achievementPosition3
        ) {
            countOfAchievements++
        }

        Log.e("LevelsViewModel", "countOfAchievements = $countOfAchievements")
        Log.e("LevelsViewModel", "countOfCurrentLevelAchievements = $countOfCurrentLevelAchievements")

        if (countOfAchievements > countOfCurrentLevelAchievements) {
            //todo update level stars, unlock next level
            val currentLevelId = mSelectedLevel.value?.id ?: -1
            val countOfLevels = mLevels?.value?.size ?: 0
            val nextLevelId = if (currentLevelId + 1 <= countOfLevels) currentLevelId + 1 else -1
            if (currentLevelId != -1) {
                updateLevelStars(currentLevelId, countOfAchievements)

//                val updatedLevel = Level(currentLevelId)
//                updatedLevel.stars = countOfAchievements
//                mSelectedLevel.value?.stars = countOfAchievements
//                updateLevel(mSelectedLevel.value)
            }
            if (nextLevelId != -1) {
                updateLevelLock(nextLevelId, false)

//                val updatedLevel = Level(nextLevelId)
//                updatedLevel.locked = false
//                updateLevel(updatedLevel)
            }
            Log.e("LevelsViewModel", "countOfLevels = $countOfLevels")
            Log.e("LevelsViewModel", "currentLevelId = $currentLevelId")
            Log.e("LevelsViewModel", "nextLevelId = $nextLevelId")
        }
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

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
        Log.e("LevelsViewModel", "ON_RESUME")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause() {
        Log.e("LevelsViewModel", "ON_PAUSE")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop() {
        Log.e("LevelsViewModel", "ON_STOP")

    }

}