package com.tilseier.escapethemonster.ui.extensions

import com.tilseier.escapethemonster.data.database.Level
import kotlin.math.ceil

fun List<Level>.toPagesWithLevels(countOfLevelsOnPage: Int): List<List<Level>> {
    val levelsPages: MutableList<List<Level>> = mutableListOf()
    levelsPages.clear()
    val repeatTimes = ceil(this.size.toFloat() / countOfLevelsOnPage).toInt()
    var firstIndex = 0
    repeat(repeatTimes) {
        val repeatCount = it + 1
        var secondIndex = repeatCount * countOfLevelsOnPage
        if (repeatCount == repeatTimes) {
            secondIndex -= secondIndex - this.size
        }
        this.subList(firstIndex, secondIndex).let { levelsPages.add(it) }
        firstIndex += countOfLevelsOnPage
    }
    return levelsPages
}
