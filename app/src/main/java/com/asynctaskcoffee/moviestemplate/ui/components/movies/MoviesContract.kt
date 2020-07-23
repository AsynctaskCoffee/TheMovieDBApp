package com.asynctaskcoffee.moviestemplate.ui.components.movies

import com.asynctaskcoffee.moviestemplate.data.remotemodels.ResultsItemMovies
import com.asynctaskcoffee.moviestemplate.data.remotemodels.ResultsItemSeries
import com.asynctaskcoffee.moviestemplate.ui.base.BaseContract

class MoviesContract {

    interface View : BaseContract.View {
        fun initUI()
        fun onMoviesFetched(items: List<ResultsItemMovies?>?)
        fun openDetailsActivity()
        fun showProgressDialog()
        fun hideProgressDialog()
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun onItemClicked(item: ResultsItemSeries)
    }

}