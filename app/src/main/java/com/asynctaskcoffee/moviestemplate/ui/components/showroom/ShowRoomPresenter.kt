package com.asynctaskcoffee.moviestemplate.ui.components.showroom

import com.asynctaskcoffee.moviestemplate.data.DataRepository
import com.asynctaskcoffee.moviestemplate.data.localmodels.AdditionalResultItem
import com.asynctaskcoffee.moviestemplate.data.remotemodels.MoviesListResponse
import com.asynctaskcoffee.moviestemplate.data.remotemodels.MoviesSingleResponse
import com.asynctaskcoffee.moviestemplate.data.remotemodels.ResultsItemMovies
import com.asynctaskcoffee.moviestemplate.data.remotemodels.ResultsItemSeries
import com.asynctaskcoffee.moviestemplate.ui.base.BasePresenter
import com.asynctaskcoffee.moviestemplate.ui.components.movies.MoviesContract
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ShowRoomPresenter @Inject constructor(private val dataRepository: DataRepository) :
    BasePresenter<ShowRoomContract.View>(), ShowRoomContract.Presenter {

    override fun onCreated() {
        super.onCreated()
        getView()?.initUI()
    }

    fun getMoviesDetails(moviesId: String) {
        disposables?.addAll(
            dataRepository.getMoviesDetailsById(moviesId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnSubscribe {
                }
                .doFinally {
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