package com.asynctaskcoffee.moviestemplate.data

import com.asynctaskcoffee.moviestemplate.utils.MoviesPrefManager
import javax.inject.Inject

open class DataRepository @Inject constructor(
    private val remoteDataSources: RemoteDataSources,
    private val moviesPrefManager: MoviesPrefManager
) {
    fun getLastTabIndex() = moviesPrefManager.getIntVal("lastTabIndex", 0)
    fun getLastTabPageIndex() = moviesPrefManager.getIntVal("lastTabPageIndex", 0)

    fun setLastTabIndex(lastTabIndex: Int) = moviesPrefManager.save("lastTabIndex", lastTabIndex)
    fun setLastTabPageIndex(lastTabPageIndex: Int) =
        moviesPrefManager.save("lastTabPageIndex", lastTabPageIndex)

    fun getMoviesByPage(pageIndex: Int) = remoteDataSources.getMoviesByPage(pageIndex)
    fun getSeriesByPage(pageIndex: Int) = remoteDataSources.getSeriesByPage(pageIndex)

    fun getMoviesDetailsById(moviesId: String) = remoteDataSources.getMoviesDetailsById(moviesId)
    fun getSeriesDetailsById(seriesId: String) = remoteDataSources.getSeriesDetailsById(seriesId)
}