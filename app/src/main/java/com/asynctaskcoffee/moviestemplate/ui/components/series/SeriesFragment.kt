package com.asynctaskcoffee.moviestemplate.ui.components.series

import android.widget.Toast
import com.asynctaskcoffee.moviestemplate.R
import com.asynctaskcoffee.moviestemplate.data.remotemodels.ResultsItemSeries
import com.asynctaskcoffee.moviestemplate.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_series.*
import javax.inject.Inject

class SeriesFragment : BaseFragment<SeriesContract.View, SeriesContract.Presenter>(),
    SeriesContract.View {

    @Inject
    lateinit var seriesPresenter: SeriesPresenter

    override fun initPresenter() = seriesPresenter

    override fun injectDependencies() = getApplicationComponent().inject(this)

    override fun getLayoutResId(): Int = R.layout.fragment_series

    override fun initUI() {

    }

    override fun onSeriesFetched(items: List<ResultsItemSeries?>?) {
        recyclerViewSeries.adapter = SeriesAdapter(items)
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