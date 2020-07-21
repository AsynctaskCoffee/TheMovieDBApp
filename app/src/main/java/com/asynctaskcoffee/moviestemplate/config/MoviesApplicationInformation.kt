package com.asynctaskcoffee.moviestemplate.config

import android.os.Build
import com.asynctaskcoffee.moviestemplate.BuildConfig

data class MoviesApplicationInformation(
    val version: String = BuildConfig.VERSION_NAME,
    val isDev: Boolean = BuildConfig.IS_DEVELOPMENT,
    val platformVersion: String = Build.VERSION.RELEASE
)