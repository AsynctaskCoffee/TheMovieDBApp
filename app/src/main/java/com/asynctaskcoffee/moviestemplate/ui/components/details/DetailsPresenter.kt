package com.asynctaskcoffee.moviestemplate.ui.components.details

import com.asynctaskcoffee.moviestemplate.data.DataRepository
import com.asynctaskcoffee.moviestemplate.data.localmodels.AdditionalResultItem
import com.asynctaskcoffee.moviestemplate.data.remotemodels.MoviesListResponse
import com.asynctaskcoffee.moviestemplate.data.remotemodels.MoviesSingleResponse
import com.asynctaskcoffee.moviestemplate.data.remotemodels.TvSeriesSingleResponse
import com.asynctaskcoffee.moviestemplate.ui.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DetailsPresenter @Inject constructor(private val dataRepository: DataRepository) :
    BasePresenter<DetailsContract.View>(), DetailsContract.Presenter {

    override fun onCreated() {
        super.onCreated()
        getView()?.initUI()
    }

    fun getSeriesDetails(seriesId: String) {
        disposables?.addAll(
            dataRepository.getSeriesDetailsById(seriesId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnSubscribe {
                    getView()?.activateShimmers()
                }
                .doFinally {
                    getView()?.deActivateShimmers()
                }.subscribeWith(
                    object : DisposableSingleObserver<TvSeriesSingleResponse>() {
                        override fun onSuccess(t: TvSeriesSingleResponse) {
                            getView()?.onAdditionalInformationReady(
                                AdditionalResultItem(
                                    t.episodeRunTime?.get(t.episodeRunTime.size - 1)?.toString(),
                                    t.overview,
                                    t.genres
                                )
                            )
                        }

                        override fun onError(e: Throwable) {
                            getView()?.showToast(e.message.toString())
                        }
                    }
                )
        )

    }

    fun getMoviesDetails(moviesId: String) {
        disposables?.addAll(
            dataRepository.getMoviesDetailsById(moviesId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnSubscribe {
                    getView()?.activateShimmers()
                }
                .doFinally {
                    getView()?.deActivateShimmers()
                }.subscribeWith(
                    object : DisposableSingleObserver<MoviesSingleResponse>() {
                        override fun onSuccess(t: MoviesSingleResponse) {
                            getView()?.onAdditionalInformationReady(
                                AdditionalResultItem(
                                    t.runtime.toString(),
                                    t.overview,
                                    t.genres
                                )
                            )
                        }

                        override fun onError(e: Throwable) {
                            getView()?.showToast(e.message.toString())
                        }
                    }
                )
        )

    }
}