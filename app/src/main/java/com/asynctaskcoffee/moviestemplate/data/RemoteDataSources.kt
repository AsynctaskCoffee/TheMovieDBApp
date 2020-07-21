package com.asynctaskcoffee.moviestemplate.data

import javax.inject.Inject

open class RemoteDataSources @Inject constructor(
    private val remoteEndPoints: RemoteEndPoints
) {
    fun getItems() = remoteEndPoints.getMovies()
}