package com.asynctaskcoffee.moviestemplate.ui.components.series

import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.asynctaskcoffee.moviestemplate.R
import com.asynctaskcoffee.moviestemplate.data.remotemodels.ResultsItemSeries
import com.asynctaskcoffee.moviestemplate.ui.base.BaseFragment
import com.asynctaskcoffee.moviestemplate.utils.EndlessRecyclerViewScrollListener
import kotlinx.android.synthetic.main.fragment_series.*
import javax.inject.Inject

class SeriesFragment : BaseFragment<SeriesContract.View, SeriesContract.Presenter>(),
    SeriesContract.View, EndlessRecyclerViewScrollListener.OnNextPageListener {

    @Inject
    lateinit var seriesPresenter: SeriesPresenter

    private var seriesList: ArrayList<ResultsItemSeries?>? = ArrayList()

    override fun initPresenter() = seriesPresenter

    override fun injectDependencies() = getApplicationComponent().inject(this)

    override fun getLayoutResId(): Int = R.layout.fragment_series

    override fun initUI() {
        recyclerViewSeries.adapter = SeriesAdapter(seriesList, this)
        recyclerViewSeries.addOnScrollListener(
            EndlessRecyclerViewScrollListener(
                recyclerViewSeries.layoutManager as GridLayoutManager,
                this
            )
        )
    }

    override fun onSeriesFetched(items: List<ResultsItemSeries?>?) {
        if (items != null) {
            seriesList?.addAll(items)
            recyclerViewSeries.adapter?.notifyDataSetChanged()
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
        seriesPresenter.fetchSeriesData(page)
    }
}
