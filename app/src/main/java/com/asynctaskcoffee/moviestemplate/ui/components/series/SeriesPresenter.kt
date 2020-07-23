package com.asynctaskcoffee.moviestemplate.ui.components.series

import com.asynctaskcoffee.moviestemplate.data.DataRepository
import com.asynctaskcoffee.moviestemplate.data.remotemodels.ResultsItemSeries
import com.asynctaskcoffee.moviestemplate.data.remotemodels.TvSeriesListResponse
import com.asynctaskcoffee.moviestemplate.ui.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SeriesPresenter @Inject constructor(private val dataRepository: DataRepository) :
    BasePresenter<SeriesContract.View>(), SeriesContract.Presenter {

    override fun onCreated() {
        super.onCreated()
        getView()?.initUI()
        fetchSeriesData(1)
    }

    private fun fetchSeriesData(pageIndex: Int) {
        disposables?.addAll(
            dataRepository.getSeriesByPage(pageIndex)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnSubscribe {
                    getView()?.showProgressDialog()
                }
                .doFinally {
                    getView()?.hideProgressDialog()
                }.subscribeWith(object : DisposableSingleObserver<TvSeriesListResponse>() {
                    override fun onSuccess(t: TvSeriesListResponse) {
                        if (t.results != null)
                            getView()?.onSeriesFetched(t.results)
                    }

                    override fun onError(e: Throwable) {

                    }
                })
        )
    }

    override fun onItemClicked(item: ResultsItemSeries) {

    }
}