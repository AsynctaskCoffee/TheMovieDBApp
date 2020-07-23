package com.asynctaskcoffee.moviestemplate.utils

import com.asynctaskcoffee.moviestemplate.data.localmodels.CommonResultItem
import com.asynctaskcoffee.moviestemplate.data.localmodels.REQUEST
import com.asynctaskcoffee.moviestemplate.data.remotemodels.ResultsItemMovies
import com.asynctaskcoffee.moviestemplate.data.remotemodels.ResultsItemSeries

class ConvertCommonResult {
    fun convert(resultsItemSeries: ResultsItemSeries): CommonResultItem = CommonResultItem(
        resultsItemSeries.id,
        resultsItemSeries.firstAirDate,
        resultsItemSeries.name,
        resultsItemSeries.voteAverage.toString(),
        resultsItemSeries.genreIds,
        resultsItemSeries.posterPath,
        resultsItemSeries.backdropPath,
        REQUEST.SERIES
    )

    fun convert(resultItemMovies: ResultsItemMovies): CommonResultItem = CommonResultItem(
        resultItemMovies.id,
        resultItemMovies.releaseDate,
        resultItemMovies.originalTitle,
        resultItemMovies.voteAverage.toString(),
        resultItemMovies.genreIds,
        resultItemMovies.posterPath,
        resultItemMovies.backdropPath,
        REQUEST.MOVIES
    )
}