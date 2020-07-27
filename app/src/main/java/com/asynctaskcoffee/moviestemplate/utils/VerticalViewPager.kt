package com.asynctaskcoffee.moviestemplate.utils

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import kotlin.math.abs

class VerticalViewPager : ViewPager {

    private var lock = false
    fun lock() {
        lock = true
    }

    fun unlock() {
        lock = false
    }

    constructor(context: Context?) : super(context!!) {
        init()
    }

    private fun init() {
        setPageTransformer(true, VerticalViewPagerTransform())
        overScrollMode = View.OVER_SCROLL_NEVER
    }

    constructor(context: Context?, attributeSet: AttributeSet?) : super(context!!, attributeSet) {
        init()
    }

    private fun swapXY(event: MotionEvent): MotionEvent {
        val newX = event.y
        val newY = event.x
        event.setLocation(newX, newY)
        return event
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        return if (lock)
            false
        else {
            val intercept = super.onInterceptTouchEvent(swapXY(ev))
            swapXY(ev)
            intercept
        }

    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(ev: MotionEvent): Boolean {
        return if (lock)
            false
        else
            super.onTouchEvent(swapXY(ev))
    }

    private inner class VerticalViewPagerTransform : PageTransformer {
        override fun transformPage(@NonNull page: View, position: Float) {
            when {
                position < -1 -> {
                    page.alpha = 0F
                }
                position <= 0 -> {
                    page.alpha = 1F
                    page.translationX = page.width * -position
                    page.translationY = page.height * position
                    page.scaleX = 1F
                    page.scaleY = 1F
                }
                position <= 1 -> {
                    page.translationX = page.width * -position
                    page.translationY = page.height * position
                    val scaleFactor =
                        Companion.Min_Scale + (1 - Companion.Min_Scale) * (1 - abs(
                            position
                        ))
                    page.scaleX = scaleFactor
                    page.scaleY = scaleFactor
                }
                position > 1 -> {
                    page.alpha = 0F
                }
            }
        }

    }

    companion object {
        private const val Min_Scale = 0.50f
    }
}