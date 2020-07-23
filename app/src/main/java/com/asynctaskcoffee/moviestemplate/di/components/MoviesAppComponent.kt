package com.asynctaskcoffee.moviestemplate.di.components

import com.asynctaskcoffee.moviestemplate.ui.components.main.MainActivity
import com.asynctaskcoffee.moviestemplate.di.modules.MoviesAppModule
import com.asynctaskcoffee.moviestemplate.di.modules.RemoteModule
import com.asynctaskcoffee.moviestemplate.di.scope.ApplicationScope
import com.asynctaskcoffee.moviestemplate.ui.components.movies.MoviesFragment
import com.asynctaskcoffee.moviestemplate.ui.components.series.SeriesFragment
import dagger.Component

@ApplicationScope
@Component(modules = [MoviesAppModule::class, RemoteModule::class])
interface MoviesAppComponent {
    fun inject(activity: MainActivity)
    fun inject(fragment: SeriesFragment)
    fun inject(fragment: MoviesFragment)
}