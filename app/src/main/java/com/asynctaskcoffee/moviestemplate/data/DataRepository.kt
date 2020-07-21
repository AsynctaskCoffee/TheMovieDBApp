package com.asynctaskcoffee.moviestemplate.data

import com.asynctaskcoffee.moviestemplate.utils.MoviesPrefManager
import javax.inject.Inject

open class DataRepository @Inject constructor(
    private val remoteDataSources: RemoteDataSources,
    private val moviesPrefManager: MoviesPrefManager
) {
    fun getLastTabIndex() = moviesPrefManager.getIntVal("", 0)
    fun getLastTabPageIndex() = moviesPrefManager.getIntVal("", 0)
    fun getItemsFromRemote() = remoteDataSources.getItems()
}