package com.tilseier.escapethemonster.data

import com.tilseier.escapethemonster.R
import com.tilseier.escapethemonster.data.database.Level
import com.tilseier.escapethemonster.data.model.Place

class HardcodedLevels {

    companion object {

        //TODO update database only if version has changed
        const val HARDCODED_LEVELS_VERSION = 1

        fun getAllLevels(): List<Level> {

            val level1 = Level(
                id = 1,
                level = "1",
                seconds = 20,
                safePlaces = listOf(
                    Place(imageResource = R.drawable.test_road, count = 10),
                    Place(imageResource = R.drawable.test_road_2, count = 5),
                    Place(imageResource = R.drawable.test_road_3, count = 15)
                ),
                scaryPlaces = listOf(
                    Place(imageResource = R.drawable.test_road_3, count = 15)
                )
            )
            level1.locked = false

            return listOf(
                //1 - 9 levels
                level1,
                Level(2, "2", listOf(
                    Place("https://i.ibb.co/2vCGcVw/y-img-10-lvl-16.jpg", false),
                    Place("https://i.ibb.co/T28VtK9/y-img-10-lvl-17.jpg", false),
                    Place("https://i.ibb.co/5WPdnfj/y-img-10-lvl-18.jpg", false),
                    Place("https://i.ibb.co/v44mVkf/y-img-10-lvl-19.jpg", false),
                    Place("https://i.ibb.co/1z9x85j/y-img-10-lvl-20.jpg", false)
                ), listOf(
                    Place("https://i.ibb.co/R4QJd1y/n-img-1-lvl-1.jpg", true),
                    Place("https://i.ibb.co/rm5MgL2/n-img-1-lvl-2.jpg", true),
                    Place("https://i.ibb.co/jM8hzym/n-img-1-lvl-3.jpg", true),
                    Place("https://i.ibb.co/6rk8KK5/n-img-1-lvl-4.jpg", true),
                    Place("https://i.ibb.co/c8wMgq3/n-img-1-lvl-5.jpg", true)
                )),//,false, 1
                Level(3, "3", listOf(
                    Place("https://d34ip4tojxno3w.cloudfront.net/app/uploads/Revontulet-j%C3%91rvell%C3%91-heijastus_ThomasKast-400x300.jpg", false),
                    Place("https://i.imgur.com/U4Oadyu.png", false),
                    Place("https://www.recursografico.com/wp-content/uploads/2009/01/97442874_b06d05cc0e_b-400x300.jpg", false)
                ), listOf(
                    Place("https://s.4pda.to/1y0q6sA4dy7vULHZW9hRTmg40tVYeKQ9lkPk.png", true),
                    Place("https://upload.wikimedia.org/wikipedia/commons/4/4e/Sementales_H-B_monchina_400x300.jpg", true),
                    Place("https://greenparty.ua/wp-content/uploads/2020/04/1180606-400x300.jpg", true)
                )),//, false, 0
                Level(4, "4", listOf(
                    Place("https://d34ip4tojxno3w.cloudfront.net/app/uploads/Revontulet-j%C3%91rvell%C3%91-heijastus_ThomasKast-400x300.jpg", false),
                    Place("https://i.imgur.com/U4Oadyu.png", false),
                    Place("https://www.recursografico.com/wp-content/uploads/2009/01/97442874_b06d05cc0e_b-400x300.jpg", false)
                ), listOf(
                    Place("https://s.4pda.to/1y0q6sA4dy7vULHZW9hRTmg40tVYeKQ9lkPk.png", true),
                    Place("https://upload.wikimedia.org/wikipedia/commons/4/4e/Sementales_H-B_monchina_400x300.jpg", true),
                    Place("https://greenparty.ua/wp-content/uploads/2020/04/1180606-400x300.jpg", true)
                )),//, false, 3
                Level(5, "5", listOf(
                    Place("https://d34ip4tojxno3w.cloudfront.net/app/uploads/Revontulet-j%C3%91rvell%C3%91-heijastus_ThomasKast-400x300.jpg", false),
                    Place("https://i.imgur.com/U4Oadyu.png", false),
                    Place("https://www.recursografico.com/wp-content/uploads/2009/01/97442874_b06d05cc0e_b-400x300.jpg", false)
                ), listOf(
                    Place("https://s.4pda.to/1y0q6sA4dy7vULHZW9hRTmg40tVYeKQ9lkPk.png", true),
                    Place("https://upload.wikimedia.org/wikipedia/commons/4/4e/Sementales_H-B_monchina_400x300.jpg", true),
                    Place("https://greenparty.ua/wp-content/uploads/2020/04/1180606-400x300.jpg", true)
                )),//, false, 5
                Level(6, "6", listOf(
                    Place("https://d34ip4tojxno3w.cloudfront.net/app/uploads/Revontulet-j%C3%91rvell%C3%91-heijastus_ThomasKast-400x300.jpg", false),
                    Place("https://i.imgur.com/U4Oadyu.png", false),
                    Place("https://www.recursografico.com/wp-content/uploads/2009/01/97442874_b06d05cc0e_b-400x300.jpg", false)
                ), listOf(
                    Place("https://s.4pda.to/1y0q6sA4dy7vULHZW9hRTmg40tVYeKQ9lkPk.png", true),
                    Place("https://upload.wikimedia.org/wikipedia/commons/4/4e/Sementales_H-B_monchina_400x300.jpg", true),
                    Place("https://greenparty.ua/wp-content/uploads/2020/04/1180606-400x300.jpg", true)
                )),//, false, 1
                Level(7, "7", listOf(
                    Place("https://i.picsum.photos/id/1/700/600.jpg", false),
                    Place("https://i.picsum.photos/id/2/700/600.jpg", false),
                    Place("https://i.picsum.photos/id/3/700/600.jpg", false)
                ), listOf(
                    Place("https://s.4pda.to/1y0q6sA4dy7vULHZW9hRTmg40tVYeKQ9lkPk.png", true),
                    Place("https://i.picsum.photos/id/210/700/600.jpg", true),
                    Place("https://i.picsum.photos/id/220/700/600.jpg", true)
                )),//, false, -1
                Level(8, "8", listOf(
                    Place("https://i.picsum.photos/id/1/700/600.jpg", false),
                    Place("https://i.picsum.photos/id/2/700/600.jpg", false),
                    Place("https://i.picsum.photos/id/3/700/600.jpg", false)
                ), listOf(
                    Place("https://s.4pda.to/1y0q6sA4dy7vULHZW9hRTmg40tVYeKQ9lkPk.png", true),
                    Place("https://i.picsum.photos/id/210/700/600.jpg", true),
                    Place("https://i.picsum.photos/id/220/700/600.jpg", true)
                )),//, false, 3
                Level(9, "9", listOf(
                    Place("https://i.picsum.photos/id/1/700/600.jpg", false),
                    Place("https://i.picsum.photos/id/2/700/600.jpg", false),
                    Place("https://i.picsum.photos/id/3/700/600.jpg", false)
                ), listOf(
                    Place("https://s.4pda.to/1y0q6sA4dy7vULHZW9hRTmg40tVYeKQ9lkPk.png", true),
                    Place("https://i.picsum.photos/id/210/700/600.jpg", true),
                    Place("https://i.picsum.photos/id/220/700/600.jpg", true)
                )),//, false, 1
                Level(10, "10", listOf(
//                Place("https://d34ip4tojxno3w.cloudfront.net/app/uploads/Revontulet-j%C3%91rvell%C3%91-heijastus_ThomasKast-400x300.jpg", false),
//                Place("https://i.imgur.com/U4Oadyu.png", false),
//                Place("https://imagestudio.hr/2013/wp-content/uploads/2014/02/open_doors-400x300_c.jpg", false),
//                Place("https://mediad.publicbroadcasting.net/p/wbgo/files/styles/small/public/201710/File_004_0.jpeg", false),
//                Place("https://i0.wp.com/www.lethoperise.me/wp-content/uploads/2012/09/doors.jpg", false),
//                Place("https://wallpapershome.ru/images/pages/pic_v/21488.jpg", false),//TODO check 640 x 1138
//                Place("https://html5css.ru/css/img_lights.jpg", false),//TODO check 600 x 400
//                Place("https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__340.jpg", false),//TODO check 546 x 340
//                Place("https://i.picsum.photos/id/234/480/360.jpg", false),//TODO check 480 x 360
//                Place("https://filedn.com/ltOdFv1aqz1YIFhf4gTY8D7/ingus-info/BLOGS/Photography-stocks3/stock-photography-slider.jpg", false),//TODO check 1600 x 806
//                Place("https://www.hd-freewallpapers.com/latest-wallpapers/desktop-image-of-a-parrot-wallpaper.jpg", false),//TODO check 1600 x 1200
//                Place("https://lokeshdhakar.com/projects/lightbox2/images/image-3.jpg", false),//TODO check 1250 x 1250
//                Place("https://tinyjpg.com/images/social/website.jpg", false),//TODO check 1020 x 510
//                Place("https://html5box.com/html5lightbox/images/lakeandballoon.jpg", false),//TODO check 1024 x 694
//                Place("https://maground.com/assets/themes/theme-ab/assets/resources/compositing.jpg", false),//TODO check 640 x 520
//                Place("https://wallpapershome.ru/images/wallpapers/kartinki-pro-lyubov-2560x1440-kartinki-pro-lyubov-21488.jpg", false),//TODO check 2560 x 1440
//                Place("https://static01.nyt.com/images/2019/11/05/science/28TB-SUNSET1/merlin_163473282_fe17fc6b-78b6-4cdd-b301-6f63e6ebdd7a-superJumbo.jpg", false),//TODO check 2048 x 1152
//                Place("https://i.pinimg.com/originals/93/a9/f2/93a9f2f88511c36f6bd8819fbcc03b7b.jpg", false),//TODO check 4000 x 2000

                    Place("https://i.picsum.photos/id/1/700/600.jpg", false),//TODO check 4000 x 2000
                    Place("https://i.picsum.photos/id/2/700/600.jpg", false),//TODO check 4000 x 2000
                    Place("https://i.picsum.photos/id/3/700/600.jpg", false),//TODO check 4000 x 2000
                    Place("https://i.picsum.photos/id/4/700/600.jpg", false),//TODO check 4000 x 2000
                    Place("https://i.picsum.photos/id/5/700/600.jpg", false),//TODO check 4000 x 2000
                    Place("https://i.picsum.photos/id/6/700/600.jpg", false),//TODO check 4000 x 2000
                    Place("https://i.picsum.photos/id/7/700/600.jpg", false),//TODO check 4000 x 2000
                    Place("https://i.picsum.photos/id/8/700/600.jpg", false),//TODO check 4000 x 2000
                    Place("https://i.picsum.photos/id/9/700/600.jpg", false),//TODO check 4000 x 2000

                    Place("https://i.picsum.photos/id/10/700/600.jpg", false),//TODO check 4000 x 2000
//                Place("https://i.picsum.photos/id/11/700/600.jpg", false),//TODO check 4000 x 2000
//                Place("https://i.picsum.photos/id/12/700/600.jpg", false),//TODO check 4000 x 2000
//                Place("https://i.picsum.photos/id/13/700/600.jpg", false),//TODO check 4000 x 2000
//                Place("https://i.picsum.photos/id/14/700/600.jpg", false),//TODO check 4000 x 2000
//                Place("https://i.picsum.photos/id/15/700/600.jpg", false),//TODO check 4000 x 2000
//                Place("https://i.picsum.photos/id/16/700/600.jpg", false),//TODO check 4000 x 2000
//                Place("https://i.picsum.photos/id/17/700/600.jpg", false),//TODO check 4000 x 2000
//                Place("https://i.picsum.photos/id/18/700/600.jpg", false),//TODO check 4000 x 2000
//                Place("https://i.picsum.photos/id/19/700/600.jpg", false),//TODO check 4000 x 2000
//                Place("https://i.picsum.photos/id/20/700/600.jpg", false),//TODO check 4000 x 2000
//                Place("https://i.picsum.photos/id/21/700/600.jpg", false),//TODO check 4000 x 2000
//                Place("https://i.picsum.photos/id/22/700/600.jpg", false),//TODO check 4000 x 2000
//                Place("https://i.picsum.photos/id/23/700/600.jpg", false),//TODO check 4000 x 2000
//                Place("https://i.picsum.photos/id/24/700/600.jpg", false),//TODO check 4000 x 2000
//                Place("https://i.picsum.photos/id/25/700/600.jpg", false),//TODO check 4000 x 2000
//                Place("https://i.picsum.photos/id/26/700/600.jpg", false),//TODO check 4000 x 2000
//                Place("https://i.picsum.photos/id/27/700/600.jpg", false),//TODO check 4000 x 2000
//                Place("https://i.picsum.photos/id/28/700/600.jpg", false),//TODO check 4000 x 2000
////                Place("https://i.picsum.photos/id/27/406/200.jpg", false),//TODO check 4000 x 2000
//                Place("https://i.picsum.photos/id/30/700/600.jpg", false),//TODO check 4000 x 2000
//                Place("https://i.picsum.photos/id/31/700/600.jpg", false),//TODO check 4000 x 2000
//                Place("https://i.picsum.photos/id/32/700/600.jpg", false),//TODO check 4000 x 2000
//                Place("https://i.picsum.photos/id/33/700/600.jpg", false),//TODO check 4000 x 2000
//                Place("https://i.picsum.photos/id/34/700/600.jpg", false),//TODO check 4000 x 2000
//                Place("https://i.picsum.photos/id/35/700/600.jpg", false),//TODO check 4000 x 2000
//                Place("https://i.picsum.photos/id/36/700/600.jpg", false),//TODO check 4000 x 2000
//                Place("https://i.picsum.photos/id/37/700/600.jpg", false),//TODO check 4000 x 2000
//                Place("https://i.picsum.photos/id/38/700/600.jpg", false),//TODO check 4000 x 2000
//                Place("https://i.picsum.photos/id/39/700/600.jpg", false),//TODO check 4000 x 2000
//                Place("https://i.picsum.photos/id/40/700/600.jpg", false),//TODO check 4000 x 2000
//                Place("https://i.picsum.photos/id/41/700/600.jpg", false),//TODO check 4000 x 2000
//                Place("https://i.picsum.photos/id/42/700/600.jpg", false),//TODO check 4000 x 2000
//                Place("https://i.picsum.photos/id/43/700/600.jpg", false),//TODO check 4000 x 2000
//                Place("https://i.picsum.photos/id/44/700/600.jpg", false),//TODO check 4000 x 2000
//                Place("https://i.picsum.photos/id/45/700/600.jpg", false),//TODO check 4000 x 2000
//                Place("https://i.picsum.photos/id/46/700/600.jpg", false),//TODO check 4000 x 2000
//                Place("https://i.picsum.photos/id/47/700/600.jpg", false),//TODO check 4000 x 2000
//                Place("https://i.picsum.photos/id/48/700/600.jpg", false),//TODO check 4000 x 2000
//                Place("https://i.picsum.photos/id/49/700/600.jpg", false),//TODO check 4000 x 2000
//                Place("https://i.picsum.photos/id/50/700/600.jpg", false),//TODO check 4000 x 2000
//                Place("https://i.picsum.photos/id/51/700/600.jpg", false),//TODO check 4000 x 2000
//                Place("https://i.picsum.photos/id/52/700/600.jpg", false),//TODO check 4000 x 2000
//                Place("https://i.picsum.photos/id/53/700/600.jpg", false),//TODO check 4000 x 2000
//                Place("https://i.picsum.photos/id/54/700/600.jpg", false),//TODO check 4000 x 2000
//                Place("https://i.picsum.photos/id/55/700/600.jpg", false),//TODO check 4000 x 2000
//                Place("https://i.picsum.photos/id/56/700/600.jpg", false),//TODO check 4000 x 2000
//                Place("https://i.picsum.photos/id/57/700/600.jpg", false),//TODO check 4000 x 2000
//                Place("https://i.picsum.photos/id/58/700/600.jpg", false),//TODO check 4000 x 2000
//                Place("https://i.picsum.photos/id/59/700/600.jpg", false),//TODO check 4000 x 2000
//                Place("https://i.picsum.photos/id/60/700/600.jpg", false),//TODO check 4000 x 2000
//                Place("https://i.picsum.photos/id/61/700/600.jpg", false),//TODO check 4000 x 2000
//                Place("https://i.picsum.photos/id/62/700/600.jpg", false),//TODO check 4000 x 2000
//                Place("https://i.picsum.photos/id/63/700/600.jpg", false),//TODO check 4000 x 2000
//                Place("https://i.picsum.photos/id/64/700/600.jpg", false),//TODO check 4000 x 2000
//                Place("https://i.picsum.photos/id/65/700/600.jpg", false),//TODO check 4000 x 2000
//                Place("https://i.picsum.photos/id/66/700/600.jpg", false),//TODO check 4000 x 2000
//                Place("https://i.picsum.photos/id/67/700/600.jpg", false),//TODO check 4000 x 2000
//                Place("https://i.picsum.photos/id/68/700/600.jpg", false),//TODO check 4000 x 2000
//                Place("https://i.picsum.photos/id/69/700/600.jpg", false),//TODO check 4000 x 2000
//                Place("https://i.picsum.photos/id/70/700/600.jpg", false),//TODO check 4000 x 2000
//                Place("https://i.picsum.photos/id/71/700/600.jpg", false),//TODO check 4000 x 2000
//                Place("https://i.picsum.photos/id/72/700/600.jpg", false),//TODO check 4000 x 2000
//                Place("https://i.picsum.photos/id/73/700/600.jpg", false),//TODO check 4000 x 2000
//                Place("https://i.picsum.photos/id/74/700/600.jpg", false),//TODO check 4000 x 2000
//                Place("https://i.picsum.photos/id/75/700/600.jpg", false),//TODO check 4000 x 2000
//                Place("https://i.picsum.photos/id/76/700/600.jpg", false),//TODO check 4000 x 2000
//                Place("https://i.picsum.photos/id/77/700/600.jpg", false),//TODO check 4000 x 2000
//                Place("https://i.picsum.photos/id/78/700/600.jpg", false),//TODO check 4000 x 2000
//                Place("https://i.picsum.photos/id/79/700/600.jpg", false),//TODO check 4000 x 2000
//                Place("https://i.picsum.photos/id/80/700/600.jpg", false),//TODO check 4000 x 2000
//                Place("https://i.picsum.photos/id/81/700/600.jpg", false),//TODO check 4000 x 2000
//                Place("https://i.picsum.photos/id/82/700/600.jpg", false),//TODO check 4000 x 2000
//                Place("https://i.picsum.photos/id/83/700/600.jpg", false),//TODO check 4000 x 2000
//                Place("https://i.picsum.photos/id/84/700/600.jpg", false),//TODO check 4000 x 2000
//                Place("https://i.picsum.photos/id/85/700/600.jpg", false),//TODO check 4000 x 2000
////                Place("https://i.picsum.photos/id/87/406/200.jpg", false),//TODO check 4000 x 2000
//                Place("https://i.picsum.photos/id/87/700/600.jpg", false),//TODO check 4000 x 2000
//                Place("https://i.picsum.photos/id/88/700/600.jpg", false),//TODO check 4000 x 2000
//                Place("https://i.picsum.photos/id/89/700/600.jpg", false),//TODO check 4000 x 2000
//                Place("https://i.picsum.photos/id/90/700/600.jpg", false),//TODO check 4000 x 2000
//                Place("https://i.picsum.photos/id/91/700/600.jpg", false),//TODO check 4000 x 2000
//                Place("https://i.picsum.photos/id/92/700/600.jpg", false),//TODO check 4000 x 2000
//                Place("https://i.picsum.photos/id/93/700/600.jpg", false),//TODO check 4000 x 2000
//                Place("https://i.picsum.photos/id/94/700/600.jpg", false),//TODO check 4000 x 2000
//                Place("https://i.picsum.photos/id/95/700/600.jpg", false),//TODO check 4000 x 2000
//                Place("https://i.picsum.photos/id/96/700/600.jpg", false),//TODO check 4000 x 2000
////                Place("https://i.picsum.photos/id/97/406/200.jpg", false),//TODO check 4000 x 2000
//                Place("https://i.picsum.photos/id/98/700/600.jpg", false),//TODO check 4000 x 2000
//                Place("https://i.picsum.photos/id/99/700/600.jpg", false),//TODO check 4000 x 2000
//
//                Place("https://i.picsum.photos/id/100/700/600.jpg", false),//TODO check 4000 x 2000


//                Place("https://cdn.dribbble.com/users/82243/screenshots/1335271/screen_shot_2013-12-04_at_7.22.54_pm_1x.png", false),
                    Place("https://www.recursografico.com/wp-content/uploads/2009/01/97442874_b06d05cc0e_b-400x300.jpg", false)
                ), listOf(
                    Place("https://s.4pda.to/1y0q6sA4dy7vULHZW9hRTmg40tVYeKQ9lkPk.png", true),

                    Place("https://i.picsum.photos/id/210/700/600.jpg", true),
                    Place("https://i.picsum.photos/id/220/700/600.jpg", true),
                    Place("https://i.picsum.photos/id/230/700/600.jpg", true),
                    Place("https://i.picsum.photos/id/240/700/600.jpg", true),
                    Place("https://i.picsum.photos/id/250/700/600.jpg", true),
                    Place("https://i.picsum.photos/id/260/700/600.jpg", true),
                    Place("https://i.picsum.photos/id/270/700/600.jpg", true),
                    Place("https://i.picsum.photos/id/280/700/600.jpg", true),
                    Place("https://i.picsum.photos/id/290/700/600.jpg", true),
                    Place("https://i.picsum.photos/id/300/700/600.jpg", true)

//                Place("https://s.4pda.to/1y0q6sA4dy7vULHZW9hRTmg40tVYeKQ9lkPk.png", true),
//                Place("https://upload.wikimedia.org/wikipedia/commons/4/4e/Sementales_H-B_monchina_400x300.jpg", true),
//                Place("https://greenparty.ua/wp-content/uploads/2020/04/1180606-400x300.jpg", true)
                ))
            )

//        levels = mutableListOf(
//            //1 - 9 levels
//            Level("1", listOf(
////                Place("https://d34ip4tojxno3w.cloudfront.net/app/uploads/Revontulet-j%C3%91rvell%C3%91-heijastus_ThomasKast-400x300.jpg", false),
////                Place("https://i.imgur.com/U4Oadyu.png", false),
////                Place("https://imagestudio.hr/2013/wp-content/uploads/2014/02/open_doors-400x300_c.jpg", false),
////                Place("https://mediad.publicbroadcasting.net/p/wbgo/files/styles/small/public/201710/File_004_0.jpeg", false),
////                Place("https://i0.wp.com/www.lethoperise.me/wp-content/uploads/2012/09/doors.jpg", false),
////                Place("https://wallpapershome.ru/images/pages/pic_v/21488.jpg", false),//TODO check 640 x 1138
////                Place("https://html5css.ru/css/img_lights.jpg", false),//TODO check 600 x 400
////                Place("https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__340.jpg", false),//TODO check 546 x 340
////                Place("https://i.picsum.photos/id/234/480/360.jpg", false),//TODO check 480 x 360
////                Place("https://filedn.com/ltOdFv1aqz1YIFhf4gTY8D7/ingus-info/BLOGS/Photography-stocks3/stock-photography-slider.jpg", false),//TODO check 1600 x 806
////                Place("https://www.hd-freewallpapers.com/latest-wallpapers/desktop-image-of-a-parrot-wallpaper.jpg", false),//TODO check 1600 x 1200
////                Place("https://lokeshdhakar.com/projects/lightbox2/images/image-3.jpg", false),//TODO check 1250 x 1250
////                Place("https://tinyjpg.com/images/social/website.jpg", false),//TODO check 1020 x 510
////                Place("https://html5box.com/html5lightbox/images/lakeandballoon.jpg", false),//TODO check 1024 x 694
////                Place("https://maground.com/assets/themes/theme-ab/assets/resources/compositing.jpg", false),//TODO check 640 x 520
////                Place("https://wallpapershome.ru/images/wallpapers/kartinki-pro-lyubov-2560x1440-kartinki-pro-lyubov-21488.jpg", false),//TODO check 2560 x 1440
////                Place("https://static01.nyt.com/images/2019/11/05/science/28TB-SUNSET1/merlin_163473282_fe17fc6b-78b6-4cdd-b301-6f63e6ebdd7a-superJumbo.jpg", false),//TODO check 2048 x 1152
////                Place("https://i.pinimg.com/originals/93/a9/f2/93a9f2f88511c36f6bd8819fbcc03b7b.jpg", false),//TODO check 4000 x 2000
//
//                Place("https://i.picsum.photos/id/1/700/600.jpg", false),//TODO check 4000 x 2000
//                Place("https://i.picsum.photos/id/2/700/600.jpg", false),//TODO check 4000 x 2000
//                Place("https://i.picsum.photos/id/3/700/600.jpg", false),//TODO check 4000 x 2000
//                Place("https://i.picsum.photos/id/4/700/600.jpg", false),//TODO check 4000 x 2000
//                Place("https://i.picsum.photos/id/5/700/600.jpg", false),//TODO check 4000 x 2000
//                Place("https://i.picsum.photos/id/6/700/600.jpg", false),//TODO check 4000 x 2000
//                Place("https://i.picsum.photos/id/7/700/600.jpg", false),//TODO check 4000 x 2000
//                Place("https://i.picsum.photos/id/8/700/600.jpg", false),//TODO check 4000 x 2000
//                Place("https://i.picsum.photos/id/9/700/600.jpg", false),//TODO check 4000 x 2000
//
//                Place("https://i.picsum.photos/id/10/700/600.jpg", false),//TODO check 4000 x 2000
////                Place("https://i.picsum.photos/id/11/700/600.jpg", false),//TODO check 4000 x 2000
////                Place("https://i.picsum.photos/id/12/700/600.jpg", false),//TODO check 4000 x 2000
////                Place("https://i.picsum.photos/id/13/700/600.jpg", false),//TODO check 4000 x 2000
////                Place("https://i.picsum.photos/id/14/700/600.jpg", false),//TODO check 4000 x 2000
////                Place("https://i.picsum.photos/id/15/700/600.jpg", false),//TODO check 4000 x 2000
////                Place("https://i.picsum.photos/id/16/700/600.jpg", false),//TODO check 4000 x 2000
////                Place("https://i.picsum.photos/id/17/700/600.jpg", false),//TODO check 4000 x 2000
////                Place("https://i.picsum.photos/id/18/700/600.jpg", false),//TODO check 4000 x 2000
////                Place("https://i.picsum.photos/id/19/700/600.jpg", false),//TODO check 4000 x 2000
////                Place("https://i.picsum.photos/id/20/700/600.jpg", false),//TODO check 4000 x 2000
////                Place("https://i.picsum.photos/id/21/700/600.jpg", false),//TODO check 4000 x 2000
////                Place("https://i.picsum.photos/id/22/700/600.jpg", false),//TODO check 4000 x 2000
////                Place("https://i.picsum.photos/id/23/700/600.jpg", false),//TODO check 4000 x 2000
////                Place("https://i.picsum.photos/id/24/700/600.jpg", false),//TODO check 4000 x 2000
////                Place("https://i.picsum.photos/id/25/700/600.jpg", false),//TODO check 4000 x 2000
////                Place("https://i.picsum.photos/id/26/700/600.jpg", false),//TODO check 4000 x 2000
////                Place("https://i.picsum.photos/id/27/700/600.jpg", false),//TODO check 4000 x 2000
////                Place("https://i.picsum.photos/id/28/700/600.jpg", false),//TODO check 4000 x 2000
//////                Place("https://i.picsum.photos/id/27/406/200.jpg", false),//TODO check 4000 x 2000
////                Place("https://i.picsum.photos/id/30/700/600.jpg", false),//TODO check 4000 x 2000
////                Place("https://i.picsum.photos/id/31/700/600.jpg", false),//TODO check 4000 x 2000
////                Place("https://i.picsum.photos/id/32/700/600.jpg", false),//TODO check 4000 x 2000
////                Place("https://i.picsum.photos/id/33/700/600.jpg", false),//TODO check 4000 x 2000
////                Place("https://i.picsum.photos/id/34/700/600.jpg", false),//TODO check 4000 x 2000
////                Place("https://i.picsum.photos/id/35/700/600.jpg", false),//TODO check 4000 x 2000
////                Place("https://i.picsum.photos/id/36/700/600.jpg", false),//TODO check 4000 x 2000
////                Place("https://i.picsum.photos/id/37/700/600.jpg", false),//TODO check 4000 x 2000
////                Place("https://i.picsum.photos/id/38/700/600.jpg", false),//TODO check 4000 x 2000
////                Place("https://i.picsum.photos/id/39/700/600.jpg", false),//TODO check 4000 x 2000
////                Place("https://i.picsum.photos/id/40/700/600.jpg", false),//TODO check 4000 x 2000
////                Place("https://i.picsum.photos/id/41/700/600.jpg", false),//TODO check 4000 x 2000
////                Place("https://i.picsum.photos/id/42/700/600.jpg", false),//TODO check 4000 x 2000
////                Place("https://i.picsum.photos/id/43/700/600.jpg", false),//TODO check 4000 x 2000
////                Place("https://i.picsum.photos/id/44/700/600.jpg", false),//TODO check 4000 x 2000
////                Place("https://i.picsum.photos/id/45/700/600.jpg", false),//TODO check 4000 x 2000
////                Place("https://i.picsum.photos/id/46/700/600.jpg", false),//TODO check 4000 x 2000
////                Place("https://i.picsum.photos/id/47/700/600.jpg", false),//TODO check 4000 x 2000
////                Place("https://i.picsum.photos/id/48/700/600.jpg", false),//TODO check 4000 x 2000
////                Place("https://i.picsum.photos/id/49/700/600.jpg", false),//TODO check 4000 x 2000
////                Place("https://i.picsum.photos/id/50/700/600.jpg", false),//TODO check 4000 x 2000
////                Place("https://i.picsum.photos/id/51/700/600.jpg", false),//TODO check 4000 x 2000
////                Place("https://i.picsum.photos/id/52/700/600.jpg", false),//TODO check 4000 x 2000
////                Place("https://i.picsum.photos/id/53/700/600.jpg", false),//TODO check 4000 x 2000
////                Place("https://i.picsum.photos/id/54/700/600.jpg", false),//TODO check 4000 x 2000
////                Place("https://i.picsum.photos/id/55/700/600.jpg", false),//TODO check 4000 x 2000
////                Place("https://i.picsum.photos/id/56/700/600.jpg", false),//TODO check 4000 x 2000
////                Place("https://i.picsum.photos/id/57/700/600.jpg", false),//TODO check 4000 x 2000
////                Place("https://i.picsum.photos/id/58/700/600.jpg", false),//TODO check 4000 x 2000
////                Place("https://i.picsum.photos/id/59/700/600.jpg", false),//TODO check 4000 x 2000
////                Place("https://i.picsum.photos/id/60/700/600.jpg", false),//TODO check 4000 x 2000
////                Place("https://i.picsum.photos/id/61/700/600.jpg", false),//TODO check 4000 x 2000
////                Place("https://i.picsum.photos/id/62/700/600.jpg", false),//TODO check 4000 x 2000
////                Place("https://i.picsum.photos/id/63/700/600.jpg", false),//TODO check 4000 x 2000
////                Place("https://i.picsum.photos/id/64/700/600.jpg", false),//TODO check 4000 x 2000
////                Place("https://i.picsum.photos/id/65/700/600.jpg", false),//TODO check 4000 x 2000
////                Place("https://i.picsum.photos/id/66/700/600.jpg", false),//TODO check 4000 x 2000
////                Place("https://i.picsum.photos/id/67/700/600.jpg", false),//TODO check 4000 x 2000
////                Place("https://i.picsum.photos/id/68/700/600.jpg", false),//TODO check 4000 x 2000
////                Place("https://i.picsum.photos/id/69/700/600.jpg", false),//TODO check 4000 x 2000
////                Place("https://i.picsum.photos/id/70/700/600.jpg", false),//TODO check 4000 x 2000
////                Place("https://i.picsum.photos/id/71/700/600.jpg", false),//TODO check 4000 x 2000
////                Place("https://i.picsum.photos/id/72/700/600.jpg", false),//TODO check 4000 x 2000
////                Place("https://i.picsum.photos/id/73/700/600.jpg", false),//TODO check 4000 x 2000
////                Place("https://i.picsum.photos/id/74/700/600.jpg", false),//TODO check 4000 x 2000
////                Place("https://i.picsum.photos/id/75/700/600.jpg", false),//TODO check 4000 x 2000
////                Place("https://i.picsum.photos/id/76/700/600.jpg", false),//TODO check 4000 x 2000
////                Place("https://i.picsum.photos/id/77/700/600.jpg", false),//TODO check 4000 x 2000
////                Place("https://i.picsum.photos/id/78/700/600.jpg", false),//TODO check 4000 x 2000
////                Place("https://i.picsum.photos/id/79/700/600.jpg", false),//TODO check 4000 x 2000
////                Place("https://i.picsum.photos/id/80/700/600.jpg", false),//TODO check 4000 x 2000
////                Place("https://i.picsum.photos/id/81/700/600.jpg", false),//TODO check 4000 x 2000
////                Place("https://i.picsum.photos/id/82/700/600.jpg", false),//TODO check 4000 x 2000
////                Place("https://i.picsum.photos/id/83/700/600.jpg", false),//TODO check 4000 x 2000
////                Place("https://i.picsum.photos/id/84/700/600.jpg", false),//TODO check 4000 x 2000
////                Place("https://i.picsum.photos/id/85/700/600.jpg", false),//TODO check 4000 x 2000
//////                Place("https://i.picsum.photos/id/87/406/200.jpg", false),//TODO check 4000 x 2000
////                Place("https://i.picsum.photos/id/87/700/600.jpg", false),//TODO check 4000 x 2000
////                Place("https://i.picsum.photos/id/88/700/600.jpg", false),//TODO check 4000 x 2000
////                Place("https://i.picsum.photos/id/89/700/600.jpg", false),//TODO check 4000 x 2000
////                Place("https://i.picsum.photos/id/90/700/600.jpg", false),//TODO check 4000 x 2000
////                Place("https://i.picsum.photos/id/91/700/600.jpg", false),//TODO check 4000 x 2000
////                Place("https://i.picsum.photos/id/92/700/600.jpg", false),//TODO check 4000 x 2000
////                Place("https://i.picsum.photos/id/93/700/600.jpg", false),//TODO check 4000 x 2000
////                Place("https://i.picsum.photos/id/94/700/600.jpg", false),//TODO check 4000 x 2000
////                Place("https://i.picsum.photos/id/95/700/600.jpg", false),//TODO check 4000 x 2000
////                Place("https://i.picsum.photos/id/96/700/600.jpg", false),//TODO check 4000 x 2000
//////                Place("https://i.picsum.photos/id/97/406/200.jpg", false),//TODO check 4000 x 2000
////                Place("https://i.picsum.photos/id/98/700/600.jpg", false),//TODO check 4000 x 2000
////                Place("https://i.picsum.photos/id/99/700/600.jpg", false),//TODO check 4000 x 2000
////
////                Place("https://i.picsum.photos/id/100/700/600.jpg", false),//TODO check 4000 x 2000
//
//
////                Place("https://cdn.dribbble.com/users/82243/screenshots/1335271/screen_shot_2013-12-04_at_7.22.54_pm_1x.png", false),
//                Place("https://www.recursografico.com/wp-content/uploads/2009/01/97442874_b06d05cc0e_b-400x300.jpg", false)
//            ), listOf(
//                Place("https://s.4pda.to/1y0q6sA4dy7vULHZW9hRTmg40tVYeKQ9lkPk.png", true),
//
//                Place("https://i.picsum.photos/id/210/700/600.jpg", true),
//                Place("https://i.picsum.photos/id/220/700/600.jpg", true),
//                Place("https://i.picsum.photos/id/230/700/600.jpg", true),
//                Place("https://i.picsum.photos/id/240/700/600.jpg", true),
//                Place("https://i.picsum.photos/id/250/700/600.jpg", true),
//                Place("https://i.picsum.photos/id/260/700/600.jpg", true),
//                Place("https://i.picsum.photos/id/270/700/600.jpg", true),
//                Place("https://i.picsum.photos/id/280/700/600.jpg", true),
//                Place("https://i.picsum.photos/id/290/700/600.jpg", true),
//                Place("https://i.picsum.photos/id/300/700/600.jpg", true)
//
////                Place("https://s.4pda.to/1y0q6sA4dy7vULHZW9hRTmg40tVYeKQ9lkPk.png", true),
////                Place("https://upload.wikimedia.org/wikipedia/commons/4/4e/Sementales_H-B_monchina_400x300.jpg", true),
////                Place("https://greenparty.ua/wp-content/uploads/2020/04/1180606-400x300.jpg", true)
//            ), false, 2),
//            Level("2", listOf(
//                Place("https://i.ibb.co/2vCGcVw/y-img-10-lvl-16.jpg", false),
//                Place("https://i.ibb.co/T28VtK9/y-img-10-lvl-17.jpg", false),
//                Place("https://i.ibb.co/5WPdnfj/y-img-10-lvl-18.jpg", false),
//                Place("https://i.ibb.co/v44mVkf/y-img-10-lvl-19.jpg", false),
//                Place("https://i.ibb.co/1z9x85j/y-img-10-lvl-20.jpg", false)
//            ), listOf(
//                Place("https://i.ibb.co/R4QJd1y/n-img-1-lvl-1.jpg", true),
//                Place("https://i.ibb.co/rm5MgL2/n-img-1-lvl-2.jpg", true),
//                Place("https://i.ibb.co/jM8hzym/n-img-1-lvl-3.jpg", true),
//                Place("https://i.ibb.co/6rk8KK5/n-img-1-lvl-4.jpg", true),
//                Place("https://i.ibb.co/c8wMgq3/n-img-1-lvl-5.jpg", true)
//            ),false, 1),
//            Level("3", listOf(
//                Place("https://d34ip4tojxno3w.cloudfront.net/app/uploads/Revontulet-j%C3%91rvell%C3%91-heijastus_ThomasKast-400x300.jpg", false),
//                Place("https://i.imgur.com/U4Oadyu.png", false),
//                Place("https://www.recursografico.com/wp-content/uploads/2009/01/97442874_b06d05cc0e_b-400x300.jpg", false)
//            ), listOf(
//                Place("https://s.4pda.to/1y0q6sA4dy7vULHZW9hRTmg40tVYeKQ9lkPk.png", true),
//                Place("https://upload.wikimedia.org/wikipedia/commons/4/4e/Sementales_H-B_monchina_400x300.jpg", true),
//                Place("https://greenparty.ua/wp-content/uploads/2020/04/1180606-400x300.jpg", true)
//            ), false, 0),
//            Level("4", listOf(
//                Place("https://d34ip4tojxno3w.cloudfront.net/app/uploads/Revontulet-j%C3%91rvell%C3%91-heijastus_ThomasKast-400x300.jpg", false),
//                Place("https://i.imgur.com/U4Oadyu.png", false),
//                Place("https://www.recursografico.com/wp-content/uploads/2009/01/97442874_b06d05cc0e_b-400x300.jpg", false)
//            ), listOf(
//                Place("https://s.4pda.to/1y0q6sA4dy7vULHZW9hRTmg40tVYeKQ9lkPk.png", true),
//                Place("https://upload.wikimedia.org/wikipedia/commons/4/4e/Sementales_H-B_monchina_400x300.jpg", true),
//                Place("https://greenparty.ua/wp-content/uploads/2020/04/1180606-400x300.jpg", true)
//            ), false, 3),
//            Level("5", listOf(
//                Place("https://d34ip4tojxno3w.cloudfront.net/app/uploads/Revontulet-j%C3%91rvell%C3%91-heijastus_ThomasKast-400x300.jpg", false),
//                Place("https://i.imgur.com/U4Oadyu.png", false),
//                Place("https://www.recursografico.com/wp-content/uploads/2009/01/97442874_b06d05cc0e_b-400x300.jpg", false)
//            ), listOf(
//                Place("https://s.4pda.to/1y0q6sA4dy7vULHZW9hRTmg40tVYeKQ9lkPk.png", true),
//                Place("https://upload.wikimedia.org/wikipedia/commons/4/4e/Sementales_H-B_monchina_400x300.jpg", true),
//                Place("https://greenparty.ua/wp-content/uploads/2020/04/1180606-400x300.jpg", true)
//            ), false, 5),
//            Level("6", listOf(
//                Place("https://d34ip4tojxno3w.cloudfront.net/app/uploads/Revontulet-j%C3%91rvell%C3%91-heijastus_ThomasKast-400x300.jpg", false),
//                Place("https://i.imgur.com/U4Oadyu.png", false),
//                Place("https://www.recursografico.com/wp-content/uploads/2009/01/97442874_b06d05cc0e_b-400x300.jpg", false)
//            ), listOf(
//                Place("https://s.4pda.to/1y0q6sA4dy7vULHZW9hRTmg40tVYeKQ9lkPk.png", true),
//                Place("https://upload.wikimedia.org/wikipedia/commons/4/4e/Sementales_H-B_monchina_400x300.jpg", true),
//                Place("https://greenparty.ua/wp-content/uploads/2020/04/1180606-400x300.jpg", true)
//            ), false, 1),
//            Level("7", listOf(
//                Place("", false),
//                Place("", false),
//                Place("", false)
//            ), listOf(
//                Place("", true),
//                Place("", true),
//                Place("", true)
//            ), false, -1),
//            Level("8", listOf(
//                Place("", false),
//                Place("", false),
//                Place("", false)
//            ), listOf(
//                Place("", true),
//                Place("", true),
//                Place("", true)
//            ), false, 3),
//            Level("9", listOf(
//                Place("", false),
//                Place("", true),
//                Place("", false)
//            ), listOf(
//                Place("", true),
//                Place("", true),
//                Place("", true)
//            ), false, 2)
////            //10 - 18 levels
////            Level(10, listOf(
////                Place("", true),
////                Place("", false),
////                Place("", false)
////            ), false, 7),
////            Level(11, listOf(
////                Place("", true),
////                Place("", false),
////                Place("", true)
////            ), false, 1),
////            Level(12, listOf(
////                Place("", true),
////                Place("", false),
////                Place("", true)
////            ), false, 1),
////            Level(13, listOf(
////                Place("", true),
////                Place("", true),
////                Place("", false)
////            ), false, 3),
////            Level(14, listOf(
////                Place("", true),
////                Place("", true),
////                Place("", false)
////            ), false, 2),
////            Level(15, listOf(
////                Place("", false),
////                Place("", false),
////                Place("", true)
////            ), false, 3),
////            Level(16, listOf(
////                Place("", false),
////                Place("", false),
////                Place("", true)
////            ), false, 3),
////            Level(17, listOf(
////                Place("", true),
////                Place("", false),
////                Place("", false)
////            ), false, 2),
////            Level(18, listOf(
////                Place("", false),
////                Place("", true),
////                Place("", true)
////            ), false, 1),
////            //19 - 27 levels
////            Level(19, listOf(
////                Place("", true),
////                Place("", false),
////                Place("", false)
////            ), false, 7),
////            Level(20, listOf(
////                Place("", true),
////                Place("", false),
////                Place("", true)
////            ), false, 1),
////            Level(21, listOf(
////                Place("", true),
////                Place("", false),
////                Place("", true)
////            ), false, 1),
////            Level(22, listOf(
////                Place("", true),
////                Place("", true),
////                Place("", false)
////            ), false, 3),
////            Level(23, listOf(
////                Place("", true),
////                Place("", true),
////                Place("", false)
////            ), false, 2),
////            Level(24, listOf(
////                Place("", false),
////                Place("", false),
////                Place("", true)
////            ), false, 3),
////            Level(25, listOf(
////                Place("", false),
////                Place("", false),
////                Place("", true)
////            ), false, 3),
////            Level(26, listOf(
////                Place("", true),
////                Place("", false),
////                Place("", false)
////            ), false, 2),
////            Level(27, listOf(
////                Place("", false),
////                Place("", true),
////                Place("", true)
////            ), false, 1)
//        )


        }
    }
}