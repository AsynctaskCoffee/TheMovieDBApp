package com.asynctaskcoffee.moviestemplate

import android.app.Application
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ProcessLifecycleOwner
import com.asynctaskcoffee.moviestemplate.di.components.DaggerMoviesAppComponent
import com.asynctaskcoffee.moviestemplate.di.components.MoviesAppComponent
import com.asynctaskcoffee.moviestemplate.di.modules.MoviesAppModule
import com.asynctaskcoffee.moviestemplate.di.modules.RemoteModule

class MoviesApp : Application(), LifecycleObserver {

    override fun onCreate() {
        super.onCreate()
        ProcessLifecycleOwner.get().lifecycle.addObserver(this)
    }

    val applicationComponent: MoviesAppComponent =
        DaggerMoviesAppComponent
            .builder()
            .moviesAppModule(MoviesAppModule(this))
            .remoteModule(RemoteModule(this))
            .build()

}