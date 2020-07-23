package com.asynctaskcoffee.moviestemplate.ui.components.movies

import com.asynctaskcoffee.moviestemplate.data.DataRepository
import com.asynctaskcoffee.moviestemplate.data.remotemodels.MoviesListResponse
import com.asynctaskcoffee.moviestemplate.data.remotemodels.ResultsItemSeries
import com.asynctaskcoffee.moviestemplate.data.remotemodels.TvSeriesListResponse
import com.asynctaskcoffee.moviestemplate.ui.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MoviesPresenter @Inject constructor(private val dataRepository: DataRepository) :
    BasePresenter<MoviesContract.View>(), MoviesContract.Presenter {

    override fun onCreated() {
        super.onCreated()
        getView()?.initUI()
        fetchMoviesData(1)
    }

    private fun fetchMoviesData(pageIndex: Int) {
        disposables?.addAll(
            dataRepository.getMoviesByPage(pageIndex)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnSubscribe {
                    getView()?.showProgressDialog()
                }
                .doFinally {
                    getView()?.hideProgressDialog()
                }.subscribeWith(object : DisposableSingleObserver<MoviesListResponse>() {
                    override fun onSuccess(t: MoviesListResponse) {
                        if (t.results != null)
                            getView()?.onMoviesFetched(t.results)
                    }

                    override fun onError(e: Throwable) {

                    }
                })
        )
    }

    override fun onItemClicked(item: ResultsItemSeries) {

    }
}