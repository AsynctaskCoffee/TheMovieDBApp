package com.asynctaskcoffee.moviestemplate.ui.components.movies

import android.util.DisplayMetrics
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.asynctaskcoffee.moviestemplate.R
import com.asynctaskcoffee.moviestemplate.data.remotemodels.ResultsItemMovies
import com.asynctaskcoffee.moviestemplate.ui.base.BaseFragment
import com.asynctaskcoffee.moviestemplate.ui.components.showroom.ShowRoomFragment
import kotlinx.android.synthetic.main.fragment_movies.*
import javax.inject.Inject
import kotlin.math.roundToInt

class MoviesFragment : BaseFragment<MoviesContract.View, MoviesContract.Presenter>(),
    MoviesContract.View {

    @Inject
    lateinit var moviesPresenter: MoviesPresenter

    @Inject
    lateinit var displayMetrics: DisplayMetrics

    private var moviesList: ArrayList<ResultsItemMovies?>? = ArrayList()


    override fun initPresenter() = moviesPresenter

    override fun injectDependencies() = getApplicationComponent().inject(this)

    override fun getLayoutResId(): Int = R.layout.fragment_movies

    override fun initUI() {
        verticalViewPagerMovies.apply {
            adapter = MoviesViewPagerAdapter(childFragmentManager, moviesList)
            offscreenPageLimit = 2
        }
    }

    override fun onMoviesFetched(items: List<ResultsItemMovies?>?) {
        if (items != null) {
            moviesList?.addAll(items)
            verticalViewPagerMovies.adapter?.notifyDataSetChanged()
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

    fun nextPageRequest(page: Int, totalItemsCount: Int, view: RecyclerView) {
        moviesPresenter.fetchMoviesData(page)
    }

    override fun onBackPressed(): Boolean {
        (verticalViewPagerMovies?.adapter?.instantiateItem(
            verticalViewPagerMovies,
            verticalViewPagerMovies.currentItem
        ) as ShowRoomFragment).onBackPressed()
        return super.onBackPressed()
    }
}