package com.asynctaskcoffee.moviestemplate.ui.components.main

import android.widget.TextView
import androidx.fragment.app.FragmentManager
import com.asynctaskcoffee.moviestemplate.ui.base.BaseContract
import com.google.android.material.bottomnavigation.BottomNavigationView

interface MainContract {

    interface View : BaseContract.View {
        fun initUI()
        fun initLastIndexes(
            lastTabIndex: Int,
            lastTabPageIndex: Int
        )

        fun getMainToolbarTitleTextView(): TextView

        fun getMainFragmentManager(): FragmentManager

        fun getMainFragmentHolder(): Int

        fun getMainNavigationView(): BottomNavigationView

        fun pushMoviesFragment()

        fun pushSeriesFragment()
    }

    interface Presenter : BaseContract.Presenter<View>
}