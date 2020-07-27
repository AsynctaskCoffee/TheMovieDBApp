package com.asynctaskcoffee.moviestemplate.ui.components.movies

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.asynctaskcoffee.moviestemplate.data.remotemodels.ResultsItemMovies
import com.asynctaskcoffee.moviestemplate.ui.components.showroom.ShowRoomFragment
import com.asynctaskcoffee.moviestemplate.utils.ConvertCommonResult

class MoviesViewPagerAdapter(
    manager: FragmentManager?,
    var moviesList: List<ResultsItemMovies?>?
) :
    FragmentPagerAdapter(manager!!, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return ShowRoomFragment.newInstance(ConvertCommonResult().convert(moviesList?.get(position)!!))
    }

    override fun getCount(): Int {
        return moviesList?.size ?: 0
    }
}