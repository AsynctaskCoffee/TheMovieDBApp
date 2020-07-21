package com.asynctaskcoffee.moviestemplate.data

import android.content.ClipData
import io.reactivex.Single
import retrofit2.http.GET

interface RemoteEndPoints {
    @GET("getMovies")
    fun getMovies(): Single<List<Any>>
}