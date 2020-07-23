package com.asynctaskcoffee.moviestemplate.ui.components.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.asynctaskcoffee.moviestemplate.ui.components.movies.MoviesFragment
import com.asynctaskcoffee.moviestemplate.ui.components.series.SeriesFragment

class MainViewPagerAdapter(manager: FragmentManager?) :
    FragmentPagerAdapter(manager!!, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    var seriesFragment = newFragmentInstance<SeriesFragment>()
    var moviesFragment = newFragmentInstance<MoviesFragment>()

    override fun getItem(position: Int): Fragment {
        return if (position == 0) moviesFragment else seriesFragment
    }

    override fun getCount(): Int {
        return 2
    }

    private inline fun <reified T : Fragment> newFragmentInstance() = T::class.java.newInstance()
}