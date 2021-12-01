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
        private const val MOVE_BACK_DURATION: Long = 300
        private const val MOVE_AHEAD_DURATION: Long = 250 // TODO maybe change to 0 and make road as a gif animation
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
    private var mPassedPlaces: MutableLiveData<Int> = MutableLiveData<Int>()
    private var mCountOfPlaces: MutableLiveData<Int> = MutableLiveData<Int>()
    private var mLevelAchievements: MutableLiveData<Achievements> = MutableLiveData<Achievements>()
    private var mLevelPlacesQueue: Queue<Place> = LinkedList()
    private var mCurrentPagerPlaces: MutableLiveData<PagerPlaces> = MutableLiveData<PagerPlaces>()
    private var mLevelTimeLeft: MutableLiveData<Long> = MutableLiveData<Long>()
    private var mLevelTime: MutableLiveData<Long> = MutableLiveData<Long>()
    private var mLevelTimerIsRunning: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
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

    fun getLevelAchievements(): LiveData<Achievements> = mLevelAchievements

    fun getPassedPlaces(): LiveData<Int> = mPassedPlaces

    fun getCountOfPlaces(): LiveData<Int> = mCountOfPlaces

    fun setSelectedLevel(level: Level?) {
        mSelectedLevel.value = level
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

    fun getLevelTimeLeft(): LiveData<Long> = mLevelTimeLeft

    fun getMaxTime(): LiveData<Long> = mLevelTime

    fun getMoveBackDuration(): LiveData<Long> = mMoveBackDuration

    fun getMoveAheadDuration(): LiveData<Long> = mMoveAheadDuration

    fun prepareStartSelectedLevel() {
        isGameOver = false
        isGameWin = false

        prepareLevelCountOfPlaces()
        prepareLevelAchievements()
        prepareLevel()

        mMoveAheadDuration.value = MOVE_AHEAD_DURATION
        mMoveBackDuration.value = MOVE_BACK_DURATION

        stopTimer()
        val time = mSelectedLevel.value?.seconds?.times(1000)
        mLevelTimeLeft.value = time
        mLevelTime.value = time

    }

    private fun prepareLevelCountOfPlaces() {
        var countOfPlaces = 0
        mSelectedLevel.value?.safePlaces?.forEach {
            countOfPlaces += it.count
        }
        mCountOfPlaces.value = countOfPlaces
    }

    private fun prepareLevelAchievements() {
        val maxAchievementProgress = mCountOfPlaces.value ?: 0 // level?.scaryPlaces?.size ?: 0

        val achievements: Achievements = Achievements(
            (maxAchievementProgress/1.66f).toInt(),
            (maxAchievementProgress/1.33f).toInt(),
            maxAchievementProgress)

        val defAchievementPosition1 = (maxAchievementProgress/1.66f).toInt()
        val defAchievementPosition2 = (maxAchievementProgress/1.33f).toInt()
        val defAchievementPosition3 = maxAchievementProgress

        achievements.achievementPosition1 = mSelectedLevel.value?.achievements?.achievementPosition1 ?: Achievements.DEFAULT_ACHIEVEMENT_POSITION
        achievements.achievementPosition2 = mSelectedLevel.value?.achievements?.achievementPosition2 ?: Achievements.DEFAULT_ACHIEVEMENT_POSITION
        achievements.achievementPosition3 = mSelectedLevel.value?.achievements?.achievementPosition3 ?: Achievements.DEFAULT_ACHIEVEMENT_POSITION

        achievements.achievementPosition1 = if(achievements.achievementPosition1 <= maxAchievementProgress && achievements.achievementPosition1 != Achievements.DEFAULT_ACHIEVEMENT_POSITION) achievements.achievementPosition1 else defAchievementPosition1
        achievements.achievementPosition2 = if(achievements.achievementPosition2 <= maxAchievementProgress && achievements.achievementPosition2 != Achievements.DEFAULT_ACHIEVEMENT_POSITION) achievements.achievementPosition2 else defAchievementPosition2
        achievements.achievementPosition3 = if(achievements.achievementPosition3 <= maxAchievementProgress && achievements.achievementPosition3 != Achievements.DEFAULT_ACHIEVEMENT_POSITION) achievements.achievementPosition3 else defAchievementPosition3

        mLevelAchievements.value = achievements
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
            val passed = mPassedPlaces.value ?: 0
            mPassedPlaces.value = passed + 1

            nextLevelPlaces()

//            mLevelTime.value = mCurrentPagerPlaces.value?.currentPlace?.milliseconds ?: INFINITE_TIME
//            mLevelTimeLeft.value = mLevelTime.value
//            startTimer(mLevelTimeLeft.value ?: INFINITE_TIME)

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
            val passed = mPassedPlaces.value ?: 0
            mPassedPlaces.value = passed + 1
            nextLevelPlaces()

//            mLevelTime.value = mCurrentPagerPlaces.value?.currentPlace?.milliseconds ?: INFINITE_TIME
//            mLevelTimeLeft.value = mLevelTime.value
//            startTimer(mLevelTimeLeft.value ?: INFINITE_TIME)

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
        _enableUserInteraction.value = Event(false) // TODO maybe enable user interaction

        if (mLevelTimerIsRunning.value == false) {
            startTimer(mSelectedLevel.value?.seconds?.times(1000) ?: INFINITE_TIME)
        }

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
    private fun prepareLevel() {
        //TODO shuffle mechanism
        mLevelPlacesQueue.clear()
        mSelectedLevel.value?.safePlaces?.let { listOfPlaces ->
            var placeIndex = 0
            val listOfAchievementsPlaces = listOf(mLevelAchievements.value?.achievementPosition1, mLevelAchievements.value?.achievementPosition2, mLevelAchievements.value?.achievementPosition3)
            val listOfNearAchievementsPlaces = listOf(mLevelAchievements.value?.achievementPosition1?.minus(1), mLevelAchievements.value?.achievementPosition2?.minus(1), mLevelAchievements.value?.achievementPosition3?.minus(1))
            val listOfFarFromAchievementsPlaces = listOf(mLevelAchievements.value?.achievementPosition1?.minus(2), mLevelAchievements.value?.achievementPosition2?.minus(2), mLevelAchievements.value?.achievementPosition3?.minus(2))
            listOfPlaces.forEach { place ->
                repeat(place.count) {
                    placeIndex++
                    when (placeIndex) {
                        in listOfAchievementsPlaces -> {
                            mLevelPlacesQueue.add(place.copy(state = PlaceState.REWARD_PLACE))
                        }
                        in listOfNearAchievementsPlaces -> {
                            mLevelPlacesQueue.add(place.copy(state = PlaceState.NEAR_REWARD_PLACE))
                        }
                        in listOfFarFromAchievementsPlaces -> {
                            mLevelPlacesQueue.add(place.copy(state = PlaceState.FAR_FROM_REWARD_PLACE))
                        }
                        else -> {
                            mLevelPlacesQueue.add(place)
                        }
                    }
                }
            }
        }

        val winPlace: Place = mLevelPlacesQueue.last().copy(state = PlaceState.GAME_WIN_PLACE)
        mLevelPlacesQueue.add(winPlace)
        mPassedPlaces.value = 0

        nextLevelPlaces()
    }

    private fun nextLevelPlaces() {
        //TODO logic

        val currentPlace = mLevelPlacesQueue.poll()
        val backPlace = Place(
            "",
            true,
            INFINITE_TIME,
            PlaceState.GAME_OVER_PLACE
        )
        val nextPlace = mLevelPlacesQueue.peek()

        Log.e("SQUID_TAG", "nextLevelPlaces: currentPlace?.imageResource = ${currentPlace?.imageResource}")
        Log.e("SQUID_TAG", "nextLevelPlaces: currentPlace?.count = ${currentPlace?.count}")
        Log.e("SQUID_TAG", "nextLevelPlaces: currentPlace?.state = ${currentPlace?.state}")
        Log.e("SQUID_TAG", "nextLevelPlaces: nextPlace?.imageResource = ${nextPlace?.imageResource}")
        Log.e("SQUID_TAG", "nextLevelPlaces: nextPlace?.count = ${nextPlace?.count}")
        Log.e("SQUID_TAG", "nextLevelPlaces: nextPlace?.state = ${nextPlace?.state}")

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

        stopTimer()
        updateCurrentLevel()

        _navigateToGameEnd.value = Event(true)
    }

    private fun updateCurrentLevel() {
        var countOfAchievements = 0
        val countOfCurrentLevelAchievements = mSelectedLevel.value?.stars ?: 0

        val passedPlaces = mPassedPlaces.value ?: 0
        val achievementPosition1 = mLevelAchievements.value?.achievementPosition1
            ?: Achievements.DEFAULT_ACHIEVEMENT_POSITION
        val achievementPosition2 = mLevelAchievements.value?.achievementPosition2
            ?: Achievements.DEFAULT_ACHIEVEMENT_POSITION
        val achievementPosition3 = mLevelAchievements.value?.achievementPosition3
            ?: Achievements.DEFAULT_ACHIEVEMENT_POSITION
        if (achievementPosition1 != Achievements.DEFAULT_ACHIEVEMENT_POSITION
            && passedPlaces >= achievementPosition1
        ) {
            countOfAchievements++
        }
        if (achievementPosition2 != Achievements.DEFAULT_ACHIEVEMENT_POSITION
            && passedPlaces >= achievementPosition2
        ) {
            countOfAchievements++
        }
        if (achievementPosition3 != Achievements.DEFAULT_ACHIEVEMENT_POSITION
            && passedPlaces >= achievementPosition3
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
                    mLevelTimerIsRunning.value = true
                    Log.e("CountDownTimer", "seconds remaining: " + millisUntilFinished / 1000)
                    //here you can have your logic to set text to edittext
                    mLevelTimeLeft.value = millisUntilFinished
                }

                override fun onFinish() {
                    mLevelTimerIsRunning.value = false
                    Log.e("CountDownTimer", "DONE!")
                    mLevelTimeLeft.value = 0

                    mMoveAheadDuration.value = SCREAMER_DURATION
                    mMoveBackDuration.value = SCREAMER_DURATION

                    //if screamer is allowed
                    _showScreamer.value = Event(true)

                    //if screamer is not allowed
//                    if (mCurrentPagerPlaces.value?.nextPlace?.state == PlaceState.GAME_OVER_PLACE) {
//                        userClickGoAhead()
//                    } else {
//                        userClickGoBack()
//                    }
                }
            }.start()
        } else {
            mLevelTimeLeft.value = INFINITE_TIME
        }
    }

    private fun stopTimer() {
        mLevelTimerIsRunning.value = false
        countDownTimer?.cancel()
    }

    fun onGameDestroyView() {
        Log.e("LevelsViewModel", "ON_GAME_DESTROY_VIEW")
        // TODO reset level if needed
        stopTimer()
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