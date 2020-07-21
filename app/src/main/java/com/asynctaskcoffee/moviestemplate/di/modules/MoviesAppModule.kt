package com.asynctaskcoffee.moviestemplate.di.modules

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.util.DisplayMetrics
import androidx.preference.PreferenceManager
import com.asynctaskcoffee.moviestemplate.config.MoviesApplicationInformation
import com.asynctaskcoffee.moviestemplate.data.DataRepository
import com.asynctaskcoffee.moviestemplate.data.RemoteDataSources
import com.asynctaskcoffee.moviestemplate.di.scope.ApplicationScope
import com.asynctaskcoffee.moviestemplate.utils.KtRxBus
import com.asynctaskcoffee.moviestemplate.utils.MoviesPrefManager
import dagger.Module
import dagger.Provides
import io.reactivex.subjects.PublishSubject

@Module
class MoviesAppModule(private val application: Application) {


    @ApplicationScope
    @Provides
    fun providesApplicationContext(): Context = application.applicationContext

    @ApplicationScope
    @Provides
    fun providesSharedPreferences(): SharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(application)

    @ApplicationScope
    @Provides
    fun providerRxBus(publisher: PublishSubject<Any>) = KtRxBus(publisher)

    @ApplicationScope
    @Provides
    fun providerDisplayMetrics(): DisplayMetrics = application.resources.displayMetrics

    @ApplicationScope
    @Provides
    fun providerPublishSubject(): PublishSubject<Any> = PublishSubject.create<Any>()

    @ApplicationScope
    @Provides
    fun providesDataRepository(
        remoteDataSources: RemoteDataSources,
        moviesPrefManager: MoviesPrefManager
    ) = DataRepository(
        remoteDataSources,
        moviesPrefManager
    )

    @ApplicationScope
    @Provides
    fun providesAppInfo() = MoviesApplicationInformation()

}