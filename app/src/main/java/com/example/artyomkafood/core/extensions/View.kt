package com.example.artyomkafood.core.extensions

import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2

fun ViewPager2.setOnPositionListener(action: (position: Int) -> Unit) {
    this.registerOnPageChangeCallback(
        object : ViewPager2.OnPageChangeCallback() {

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                action(position)
            }

        }
    )
}

fun ViewPager2.createCarousel() {

    this.apply {
        offscreenPageLimit = 5
        getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_ALWAYS

        setPageTransformer { page, position ->
            val r = 1 - kotlin.math.abs(position)
            page.scaleY = 0.87f + r + 0.14f
            page.scaleX = 0.87f + r + 0.14f
            page.alpha = r + 0.14f
        }
    }
}
