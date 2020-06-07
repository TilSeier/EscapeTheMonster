package com.tilseier.escapethemonster.data.model

data class Achievements(var achievementPosition1: Int = DEFAULT_ACHIEVEMENT_POSITION,
                        var achievementPosition2: Int = DEFAULT_ACHIEVEMENT_POSITION,
                        var achievementPosition3: Int = DEFAULT_ACHIEVEMENT_POSITION)
{
    companion object {
        const val DEFAULT_ACHIEVEMENT_POSITION = -1
    }
}