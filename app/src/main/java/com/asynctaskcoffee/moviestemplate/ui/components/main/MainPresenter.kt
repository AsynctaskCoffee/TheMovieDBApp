package com.asynctaskcoffee.moviestemplate.ui.components.main

import com.asynctaskcoffee.moviestemplate.data.DataRepository
import com.asynctaskcoffee.moviestemplate.ui.base.BasePresenter
import javax.inject.Inject

class MainPresenter @Inject constructor(private val dataRepository: DataRepository) :
    BasePresenter<MainContract.View>(), MainContract.Presenter {

    override fun onCreated() {
        super.onCreated()
        getView()?.initUI()
        letSetLastIndexes()
    }

    private fun letSetLastIndexes() {
        val lastTabIndex: Int = dataRepository.getLastTabIndex()
        val lastTabPageIndex: Int = dataRepository.getLastTabPageIndex()
        getView()?.initLastIndexes(lastTabIndex, lastTabPageIndex)
    }

    fun saveLastTabIndex(lastTabIndex: Int) = dataRepository.setLastTabIndex(lastTabIndex)

    fun saveLastTabPageIndex(lastTabPageIndex: Int) =
        dataRepository.setLastTabPageIndex(lastTabPageIndex)
}