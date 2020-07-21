package com.asynctaskcoffee.moviestemplate.di.modules

import android.app.Application
import com.asynctaskcoffee.moviestemplate.BuildConfig
import com.asynctaskcoffee.moviestemplate.di.scope.ApplicationScope
import com.asynctaskcoffee.moviestemplate.data.RemoteEndPoints
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named


@Module
class RemoteModule(private val application: Application) {

    companion object {
        const val CACHE_SIZE = 5 * 1024 * 1024L
    }

    @ApplicationScope
    @Provides
    fun provideGson() = GsonBuilder().create()

    @ApplicationScope
    @Provides
    fun provideOkHttpCache() = Cache(application.cacheDir, CACHE_SIZE)

    @ApplicationScope
    @Provides
    @Named("CommonOkHttpClient")
    fun provideOkHttpClient(cache: Cache) =
        with(OkHttpClient.Builder()) {
            cache(cache)
            if (BuildConfig.IS_DEVELOPMENT) {
                val logging = HttpLoggingInterceptor()
                logging.level = HttpLoggingInterceptor.Level.BASIC
                addInterceptor(logging)
            }
            build()
        }

    @ApplicationScope
    @Provides
    @Named("RemoteOkHttpClient")
    fun provideRemoteOkHttpClient(cache: Cache) =
        with(OkHttpClient.Builder()) {
            cache(cache)
            build()
        }

    @ApplicationScope
    @Provides
    @Named("Retrofit")
    fun provideRetrofit(gson: Gson, @Named("RemoteOkHttpClient") okHttpClient: OkHttpClient) =
        Retrofit.Builder()
            .baseUrl(BuildConfig.moviesServiceEndPointUrl)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

    @ApplicationScope
    @Provides
    fun provideRemoteApiService(@Named("Retrofit") retrofit: Retrofit) = retrofit.create(
        RemoteEndPoints::class.java
    )
}