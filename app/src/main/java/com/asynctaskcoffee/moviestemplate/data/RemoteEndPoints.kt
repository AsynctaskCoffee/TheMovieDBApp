package com.asynctaskcoffee.moviestemplate.data

import com.asynctaskcoffee.moviestemplate.data.remotemodels.MoviesListResponse
import com.asynctaskcoffee.moviestemplate.data.remotemodels.MoviesSingleResponse
import com.asynctaskcoffee.moviestemplate.data.remotemodels.TvSeriesListResponse
import com.asynctaskcoffee.moviestemplate.data.remotemodels.TvSeriesSingleResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RemoteEndPoints {
    @GET("3/discover/movie")
    fun getMovies(
        @Query("page") page: Int
    ): Single<MoviesListResponse>

    @GET("3/discover/tv")
    fun getSeries(
        @Query("page") page: Int
    ): Single<TvSeriesListResponse>

    @GET("3/tv/{tv_id}")
    fun getSingleSeries(
        @Path("tv_id") tvId: String
    ): Single<TvSeriesSingleResponse>

    @GET("3/movie/{movie_id}")
    fun getSingleMovie(
        @Path("movie_id") movieId: String
    ): Single<MoviesSingleResponse>
}