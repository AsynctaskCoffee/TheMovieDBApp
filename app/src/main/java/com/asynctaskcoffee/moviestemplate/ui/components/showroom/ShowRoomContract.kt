package com.asynctaskcoffee.moviestemplate.ui.components.showroom

import com.asynctaskcoffee.moviestemplate.data.localmodels.AdditionalResultItem
import com.asynctaskcoffee.moviestemplate.data.remotemodels.ResultsItemMovies
import com.asynctaskcoffee.moviestemplate.data.remotemodels.ResultsItemSeries
import com.asynctaskcoffee.moviestemplate.ui.base.BaseContract

class ShowRoomContract {

    interface View : BaseContract.View {
        fun initUI()
        fun onAdditionalInformationReady(additionalResultItem: AdditionalResultItem)
        fun showProgressDialog()
        fun hideProgressDialog()
    }

    interface Presenter : BaseContract.Presenter<View>
}