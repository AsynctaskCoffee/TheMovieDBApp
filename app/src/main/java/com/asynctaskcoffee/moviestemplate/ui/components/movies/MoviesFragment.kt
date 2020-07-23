package com.asynctaskcoffee.moviestemplate.ui.components.movies

import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.asynctaskcoffee.moviestemplate.R
import com.asynctaskcoffee.moviestemplate.data.remotemodels.ResultsItemMovies
import com.asynctaskcoffee.moviestemplate.data.remotemodels.ResultsItemSeries
import com.asynctaskcoffee.moviestemplate.ui.base.BaseFragment
import com.asynctaskcoffee.moviestemplate.utils.EndlessRecyclerViewScrollListener
import kotlinx.android.synthetic.main.fragment_movies.*
import javax.inject.Inject

class MoviesFragment : BaseFragment<MoviesContract.View, MoviesContract.Presenter>(),
    MoviesContract.View, EndlessRecyclerViewScrollListener.OnNextPageListener {

    @Inject
    lateinit var moviesPresenter: MoviesPresenter

    private var moviesList: ArrayList<ResultsItemMovies?>? = ArrayList()


    override fun initPresenter() = moviesPresenter

    override fun injectDependencies() = getApplicationComponent().inject(this)

    override fun getLayoutResId(): Int = R.layout.fragment_movies

    override fun initUI() {
        recyclerViewMovies.adapter = MoviesAdapter(moviesList, this)
        recyclerViewMovies.addOnScrollListener(
            EndlessRecyclerViewScrollListener(
                recyclerViewMovies.layoutManager as LinearLayoutManager,
                this
            )
        )
    }

    override fun onMoviesFetched(items: List<ResultsItemMovies?>?) {
        if (items != null) {
            moviesList?.addAll(items)
            recyclerViewMovies.adapter?.notifyDataSetChanged()
        }
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

    override fun nextPageRequest(page: Int, totalItemsCount: Int, view: RecyclerView) {
        moviesPresenter.fetchMoviesData(page)
    }
}