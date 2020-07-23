package com.asynctaskcoffee.moviestemplate.ui.components.series

import com.asynctaskcoffee.moviestemplate.data.remotemodels.ResultsItemSeries
import com.asynctaskcoffee.moviestemplate.ui.base.BaseContract

class SeriesContract {

    interface View : BaseContract.View {
        fun initUI()
        fun onSeriesFetched(items: List<ResultsItemSeries?>?)
        fun openDetailsActivity()
        fun showProgressDialog()
        fun hideProgressDialog()
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun onItemClicked(item: ResultsItemSeries)
    }

}