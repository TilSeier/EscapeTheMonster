package com.tilseier.escapethemonster.screens.levels

import androidx.lifecycle.MutableLiveData
import com.tilseier.escapethemonster.models.Level
import com.tilseier.escapethemonster.models.Place

class LevelsRepository {

    companion object {
        var instance: LevelsRepository? = null

        fun newInstance(): LevelsRepository? {
            if (instance == null) {
                instance = LevelsRepository()
            }
            return instance
        }
    }

    private var levels: MutableList<Level> = mutableListOf()
    private var levelsPages: MutableList<List<Level>> = mutableListOf()

    //Pretend to get data from a webservice or online source
    fun getLevels(): MutableLiveData<List<Level>>? {
        setLevels() //instead of retrieve data from the data source
        val data: MutableLiveData<List<Level>> = MutableLiveData<List<Level>>()
        data.value = levels
        return data
    }

    fun getLevelsPages(): MutableLiveData<List<List<Level>>>? {
        setLevels() //instead of retrieve data from the data source
        val data: MutableLiveData<List<List<Level>>> = MutableLiveData<List<List<Level>>>()
        data.value = levelsPages
        return data
    }

    private fun setLevels() {
        levels = mutableListOf(
            //1 - 9 levels
            Level(1, listOf(
                Place("https://d34ip4tojxno3w.cloudfront.net/app/uploads/Revontulet-j%C3%91rvell%C3%91-heijastus_ThomasKast-400x300.jpg", false),
                Place("https://i.imgur.com/U4Oadyu.png", false),
                Place("https://www.recursografico.com/wp-content/uploads/2009/01/97442874_b06d05cc0e_b-400x300.jpg", false)
            ), listOf(
                Place("https://s.4pda.to/1y0q6sA4dy7vULHZW9hRTmg40tVYeKQ9lkPk.png", true),
                Place("https://upload.wikimedia.org/wikipedia/commons/4/4e/Sementales_H-B_monchina_400x300.jpg", true),
                Place("https://greenparty.ua/wp-content/uploads/2020/04/1180606-400x300.jpg", true)
            ), false, 2),
            Level(2, listOf(
                Place("https://d34ip4tojxno3w.cloudfront.net/app/uploads/Revontulet-j%C3%91rvell%C3%91-heijastus_ThomasKast-400x300.jpg", false),
                Place("https://i.imgur.com/U4Oadyu.png", false),
                Place("https://www.recursografico.com/wp-content/uploads/2009/01/97442874_b06d05cc0e_b-400x300.jpg", false)
            ), listOf(
                Place("https://s.4pda.to/1y0q6sA4dy7vULHZW9hRTmg40tVYeKQ9lkPk.png", true),
                Place("https://upload.wikimedia.org/wikipedia/commons/4/4e/Sementales_H-B_monchina_400x300.jpg", true),
                Place("https://greenparty.ua/wp-content/uploads/2020/04/1180606-400x300.jpg", true)
            ),false, 1),
            Level(3, listOf(
                Place("https://d34ip4tojxno3w.cloudfront.net/app/uploads/Revontulet-j%C3%91rvell%C3%91-heijastus_ThomasKast-400x300.jpg", false),
                Place("https://i.imgur.com/U4Oadyu.png", false),
                Place("https://www.recursografico.com/wp-content/uploads/2009/01/97442874_b06d05cc0e_b-400x300.jpg", false)
            ), listOf(
                Place("https://s.4pda.to/1y0q6sA4dy7vULHZW9hRTmg40tVYeKQ9lkPk.png", true),
                Place("https://upload.wikimedia.org/wikipedia/commons/4/4e/Sementales_H-B_monchina_400x300.jpg", true),
                Place("https://greenparty.ua/wp-content/uploads/2020/04/1180606-400x300.jpg", true)
            ), false, 0),
            Level(4, listOf(
                Place("https://d34ip4tojxno3w.cloudfront.net/app/uploads/Revontulet-j%C3%91rvell%C3%91-heijastus_ThomasKast-400x300.jpg", false),
                Place("https://i.imgur.com/U4Oadyu.png", false),
                Place("https://www.recursografico.com/wp-content/uploads/2009/01/97442874_b06d05cc0e_b-400x300.jpg", false)
            ), listOf(
                Place("https://s.4pda.to/1y0q6sA4dy7vULHZW9hRTmg40tVYeKQ9lkPk.png", true),
                Place("https://upload.wikimedia.org/wikipedia/commons/4/4e/Sementales_H-B_monchina_400x300.jpg", true),
                Place("https://greenparty.ua/wp-content/uploads/2020/04/1180606-400x300.jpg", true)
            ), false, 3),
            Level(5, listOf(
                Place("https://d34ip4tojxno3w.cloudfront.net/app/uploads/Revontulet-j%C3%91rvell%C3%91-heijastus_ThomasKast-400x300.jpg", false),
                Place("https://i.imgur.com/U4Oadyu.png", false),
                Place("https://www.recursografico.com/wp-content/uploads/2009/01/97442874_b06d05cc0e_b-400x300.jpg", false)
            ), listOf(
                Place("https://s.4pda.to/1y0q6sA4dy7vULHZW9hRTmg40tVYeKQ9lkPk.png", true),
                Place("https://upload.wikimedia.org/wikipedia/commons/4/4e/Sementales_H-B_monchina_400x300.jpg", true),
                Place("https://greenparty.ua/wp-content/uploads/2020/04/1180606-400x300.jpg", true)
            ), false, 5),
            Level(6, listOf(
                Place("https://d34ip4tojxno3w.cloudfront.net/app/uploads/Revontulet-j%C3%91rvell%C3%91-heijastus_ThomasKast-400x300.jpg", false),
                Place("https://i.imgur.com/U4Oadyu.png", false),
                Place("https://www.recursografico.com/wp-content/uploads/2009/01/97442874_b06d05cc0e_b-400x300.jpg", false)
            ), listOf(
                Place("https://s.4pda.to/1y0q6sA4dy7vULHZW9hRTmg40tVYeKQ9lkPk.png", true),
                Place("https://upload.wikimedia.org/wikipedia/commons/4/4e/Sementales_H-B_monchina_400x300.jpg", true),
                Place("https://greenparty.ua/wp-content/uploads/2020/04/1180606-400x300.jpg", true)
            ), false, 1),
            Level(7, listOf(
                Place("", false),
                Place("", false),
                Place("", false)
            ), listOf(
                Place("", true),
                Place("", true),
                Place("", true)
            ), false, -1),
            Level(8, listOf(
                Place("", false),
                Place("", false),
                Place("", false)
            ), listOf(
                Place("", true),
                Place("", true),
                Place("", true)
            ), false, 3),
            Level(9, listOf(
                Place("", false),
                Place("", true),
                Place("", false)
            ), listOf(
                Place("", true),
                Place("", true),
                Place("", true)
            ), false, 2)
//            //10 - 18 levels
//            Level(10, listOf(
//                Place("", true),
//                Place("", false),
//                Place("", false)
//            ), false, 7),
//            Level(11, listOf(
//                Place("", true),
//                Place("", false),
//                Place("", true)
//            ), false, 1),
//            Level(12, listOf(
//                Place("", true),
//                Place("", false),
//                Place("", true)
//            ), false, 1),
//            Level(13, listOf(
//                Place("", true),
//                Place("", true),
//                Place("", false)
//            ), false, 3),
//            Level(14, listOf(
//                Place("", true),
//                Place("", true),
//                Place("", false)
//            ), false, 2),
//            Level(15, listOf(
//                Place("", false),
//                Place("", false),
//                Place("", true)
//            ), false, 3),
//            Level(16, listOf(
//                Place("", false),
//                Place("", false),
//                Place("", true)
//            ), false, 3),
//            Level(17, listOf(
//                Place("", true),
//                Place("", false),
//                Place("", false)
//            ), false, 2),
//            Level(18, listOf(
//                Place("", false),
//                Place("", true),
//                Place("", true)
//            ), false, 1),
//            //19 - 27 levels
//            Level(19, listOf(
//                Place("", true),
//                Place("", false),
//                Place("", false)
//            ), false, 7),
//            Level(20, listOf(
//                Place("", true),
//                Place("", false),
//                Place("", true)
//            ), false, 1),
//            Level(21, listOf(
//                Place("", true),
//                Place("", false),
//                Place("", true)
//            ), false, 1),
//            Level(22, listOf(
//                Place("", true),
//                Place("", true),
//                Place("", false)
//            ), false, 3),
//            Level(23, listOf(
//                Place("", true),
//                Place("", true),
//                Place("", false)
//            ), false, 2),
//            Level(24, listOf(
//                Place("", false),
//                Place("", false),
//                Place("", true)
//            ), false, 3),
//            Level(25, listOf(
//                Place("", false),
//                Place("", false),
//                Place("", true)
//            ), false, 3),
//            Level(26, listOf(
//                Place("", true),
//                Place("", false),
//                Place("", false)
//            ), false, 2),
//            Level(27, listOf(
//                Place("", false),
//                Place("", true),
//                Place("", true)
//            ), false, 1)
        )

        levelsPages.clear()
        //TODO SEPARATE ON PAGES PROPERLY
        levelsPages.add(levels.subList(0, 9))
//        levelsPages.add(levels.subList(9, 18))
//        levelsPages.add(levels.subList(18, 27))
    }

}