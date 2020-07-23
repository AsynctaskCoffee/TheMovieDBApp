package com.asynctaskcoffee.moviestemplate.ui.components.details

import com.asynctaskcoffee.moviestemplate.data.localmodels.AdditionalResultItem
import com.asynctaskcoffee.moviestemplate.ui.base.BaseContract

class DetailsContract {
    interface View : BaseContract.View {
        fun initUI()
        fun activateShimmers()
        fun deActivateShimmers()
        fun onAdditionalInformationReady(additionalResultItem: AdditionalResultItem)
    }

    interface Presenter : BaseContract.Presenter<View>
}