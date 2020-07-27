package com.asynctaskcoffee.moviestemplate.ui.components.movies

import android.util.DisplayMetrics
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.asynctaskcoffee.moviestemplate.R
import com.asynctaskcoffee.moviestemplate.data.remotemodels.ResultsItemMovies
import com.asynctaskcoffee.moviestemplate.ui.base.BaseFragment
import com.asynctaskcoffee.moviestemplate.ui.components.showroom.ShowRoomFragment
import com.asynctaskcoffee.moviestemplate.utils.EndlessViewPagerListener
import kotlinx.android.synthetic.main.fragment_movies.*
import javax.inject.Inject
import kotlin.math.roundToInt

class MoviesFragment : BaseFragment<MoviesContract.View, MoviesContract.Presenter>(),
    MoviesContract.View, EndlessViewPagerListener.OnNextPageListener {

    @Inject
    lateinit var moviesPresenter: MoviesPresenter

    @Inject
    lateinit var displayMetrics: DisplayMetrics

    private var moviesList: ArrayList<ResultsItemMovies?>? = ArrayList()


    override fun initPresenter() = moviesPresenter

    override fun injectDependencies() = getApplicationComponent().inject(this)

    override fun getLayoutResId(): Int = R.layout.fragment_movies

    override fun initUI() {


//        verticalViewPagerMovies.clipToPadding = false
//        verticalViewPagerMovies.pageMargin = -100
//        verticalViewPagerMovies.setPadding(0, 200, 0, 300)

        verticalViewPagerMovies.apply {
            adapter = MoviesViewPagerAdapter(childFragmentManager, moviesList)
            offscreenPageLimit = 2
        }
        verticalViewPagerMovies.addOnPageChangeListener(
            EndlessViewPagerListener(
                this,
                verticalViewPagerMovies
            )
        )
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

    override fun onBackPressed(): Boolean {
        (verticalViewPagerMovies?.adapter?.instantiateItem(
            verticalViewPagerMovies,
            verticalViewPagerMovies.currentItem
        ) as ShowRoomFragment).onBackPressed()
        return super.onBackPressed()
    }

    override fun nextPageRequest(page: Int, totalItemsCount: Int) {
        moviesPresenter.fetchMoviesData(page)
    }
}