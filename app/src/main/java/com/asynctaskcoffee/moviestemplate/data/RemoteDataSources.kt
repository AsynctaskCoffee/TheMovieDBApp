package com.asynctaskcoffee.moviestemplate.data

import javax.inject.Inject

open class RemoteDataSources @Inject constructor(
    private val remoteEndPoints: RemoteEndPoints,
    private val bearerTokenInterceptor: BearerTokenInterceptor
) {
    fun setBearerToken(bearerToken: String) {
        bearerTokenInterceptor.bearerToken = bearerToken
    }

    fun getMoviesByPage(pageIndex: Int) = remoteEndPoints.getMovies(pageIndex)
    fun getSeriesByPage(pageIndex: Int) = remoteEndPoints.getSeries(pageIndex)

    fun getMoviesByPage(movieId: String) = remoteEndPoints.getSingleMovie(movieId)
    fun getSeriesByPage(seriesId: String) = remoteEndPoints.getSingleSeries(seriesId)
}