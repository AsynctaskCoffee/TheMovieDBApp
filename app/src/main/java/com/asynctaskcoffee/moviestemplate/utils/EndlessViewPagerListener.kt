package com.asynctaskcoffee.moviestemplate.utils

import androidx.viewpager.widget.ViewPager

class EndlessViewPagerListener(onNextPageListener: OnNextPageListener, var viewPager: ViewPager) :
    ViewPager.OnPageChangeListener {

    private var visibleThreshold = 5
    private var currentPage = 1
    private var previousTotalItemCount = 0
    private var loading = true
    private var startingPageIndex = 1
    private var itemPerRequest = 20
    private var onNextPageListener: OnNextPageListener? = onNextPageListener

    fun resetState() {
        this.currentPage = this.startingPageIndex
        this.previousTotalItemCount = 0
        this.loading = true
    }

    interface OnNextPageListener {
        fun nextPageRequest(page: Int, totalItemsCount: Int)
    }

    override fun onPageScrollStateChanged(state: Int) {
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }

    override fun onPageSelected(position: Int) {
        val totalItemCount = viewPager.adapter?.count!!

        if (totalItemCount < previousTotalItemCount) {
            this.currentPage = this.startingPageIndex
            this.previousTotalItemCount = totalItemCount
            if (totalItemCount == 0) {
                this.loading = true
            }
        }
        if (loading && (totalItemCount > previousTotalItemCount)) {
            loading = false
            previousTotalItemCount = totalItemCount
        }

        if (!loading && (position + visibleThreshold) > totalItemCount && (itemPerRequest * currentPage == totalItemCount)) {
            currentPage++
            if (onNextPageListener != null)
                onNextPageListener!!.nextPageRequest(currentPage, totalItemCount)
            loading = true
        }
    }

}