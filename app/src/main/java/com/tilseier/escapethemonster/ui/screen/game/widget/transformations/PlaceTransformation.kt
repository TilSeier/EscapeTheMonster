package com.tilseier.escapethemonster.ui.screen.game.widget.transformations

import android.util.Log
import android.view.View
import androidx.viewpager.widget.ViewPager.PageTransformer

class PlaceTransformation : PageTransformer {
    override fun transformPage(
        page: View,
        position: Float
    ) {

        page.translationX = -position * page.width
        page.alpha = 1 - Math.abs(position)

        if (position < -1) {    // [-Infinity,-1)
            // This page is way off-screen to the left.
            page.alpha = 0f
        } else if (position <= 0) {    // [-1,0]
            page.scaleX = 1 + Math.abs(position)
            page.scaleY = 1 + Math.abs(position)
        } else if (position <= 1) {    // (0,1]
            page.scaleX = 1 - Math.abs(position)
            page.scaleY = 1 - Math.abs(position)
        } else {    // (1,+Infinity]
            // This page is way off-screen to the right.
            page.alpha = 0f
        }
    }
}