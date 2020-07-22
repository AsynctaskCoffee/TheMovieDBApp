package com.asynctaskcoffee.moviestemplate.data

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject

class BearerTokenInterceptor @Inject
constructor() : Interceptor {

    var bearerToken: String? = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJjMzcxZjJmNmEzYmZjZjZlNjgwNDc3NTYyMjY1M2I5NiIsInN1YiI6IjVmMTgyZDgzNTFlNmFiMDAzNWM4MGY2NSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.RIMUJJIwI6c14BH9DiXCzRnSP5YrghGz0LuTJV7pECo"

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        requestBuilder.addHeader("Authorization", "Bearer $bearerToken")
        requestBuilder.addHeader("Accept", "application/json")
        requestBuilder.addHeader("Content-Type", "application/json")
        return chain.proceed(requestBuilder.build())
    }
}