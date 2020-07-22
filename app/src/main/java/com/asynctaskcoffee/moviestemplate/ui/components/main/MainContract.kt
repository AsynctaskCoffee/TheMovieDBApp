package com.asynctaskcoffee.moviestemplate.ui.components.main

import com.asynctaskcoffee.moviestemplate.ui.base.BaseContract

interface MainContract {

    interface View : BaseContract.View {
        fun initUI()
        fun initLastIndexes(
            lastTabIndex: Int,
            lastTabPageIndex: Int
        )
    }

    interface Presenter : BaseContract.Presenter<View>
}