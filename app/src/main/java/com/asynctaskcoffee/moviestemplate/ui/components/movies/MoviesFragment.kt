package com.asynctaskcoffee.moviestemplate.ui.components.movies

import android.widget.Toast
import com.asynctaskcoffee.moviestemplate.R
import com.asynctaskcoffee.moviestemplate.data.remotemodels.ResultsItemMovies
import com.asynctaskcoffee.moviestemplate.data.remotemodels.ResultsItemSeries
import com.asynctaskcoffee.moviestemplate.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_movies.*
import kotlinx.android.synthetic.main.fragment_series.*
import javax.inject.Inject

class MoviesFragment : BaseFragment<MoviesContract.View, MoviesContract.Presenter>(),
    MoviesContract.View {

    @Inject
    lateinit var moviesPresenter: MoviesPresenter

    override fun initPresenter() = moviesPresenter

    override fun injectDependencies() = getApplicationComponent().inject(this)

    override fun getLayoutResId(): Int = R.layout.fragment_movies

    override fun initUI() {

    }

    override fun onMoviesFetched(items: List<ResultsItemMovies?>?) {
        recyclerViewMovies.adapter = MoviesAdapter(items)
    }

    override fun openDetailsActivity() {
    }

    override fun showProgressDialog() {
    }

    override fun hideProgressDialog() {
    }

    override fun showToast(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }
}