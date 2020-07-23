package com.asynctaskcoffee.moviestemplate.data.localmodels

import android.os.Parcelable
import com.asynctaskcoffee.moviestemplate.data.remotemodels.GenresItem
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Parcelize
data class CommonResultItem(
    val id: Int? = null,
    val dateString: String? = null,
    val name: String? = null,
    val rating: String? = null,
    val genreIds: List<Int?>? = null,
    val posterPath: String? = null,
    val backdropPath: String? = null,
    val type: REQUEST
) : Parcelable, Serializable

@Parcelize
data class AdditionalResultItem(
    val duration: String? = null,
    val information: String? = null,
    val genreIds: List<GenresItem?>? = null
) : Parcelable, Serializable

enum class REQUEST { SERIES, MOVIES }
