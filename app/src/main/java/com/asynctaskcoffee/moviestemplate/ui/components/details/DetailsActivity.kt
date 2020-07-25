package com.asynctaskcoffee.moviestemplate.ui.components.details

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import com.asynctaskcoffee.moviestemplate.R
import com.asynctaskcoffee.moviestemplate.data.localmodels.AdditionalResultItem
import com.asynctaskcoffee.moviestemplate.data.localmodels.CommonResultItem
import com.asynctaskcoffee.moviestemplate.data.localmodels.REQUEST
import com.asynctaskcoffee.moviestemplate.data.remotemodels.GenresItem
import com.asynctaskcoffee.moviestemplate.ui.base.BaseActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_details_demo_design.*
import java.io.Serializable
import javax.inject.Inject

class DetailsActivity : BaseActivity<DetailsContract.View, DetailsContract.Presenter>(),
    DetailsContract.View {

    @Inject
    lateinit var detailsPresenter: DetailsPresenter

    override fun initPresenter(): DetailsContract.Presenter = detailsPresenter

    override fun injectDependencies() = getApplicationComponent().inject(this)

    override fun getLayoutResId(): Int = R.layout.activity_details_demo_design

    override fun initUI() {
        val commonResultItem = intent.getSerializableExtra("common") as? CommonResultItem

        Glide.with(this).load("https://image.tmdb.org/t/p/w500" + commonResultItem?.posterPath)
            .into(detailsImage)

        Glide.with(this).load("https://image.tmdb.org/t/p/w500" + commonResultItem?.backdropPath)
            .into(detailsCoverImage)

        detailsSubTitle.text = ""

        detailsTitle.text = commonResultItem?.name
        detailsRating.text = commonResultItem?.rating
        detailsDate.text = commonResultItem?.dateString

        if (commonResultItem?.type == REQUEST.MOVIES)
            detailsPresenter.getMoviesDetails(commonResultItem.id.toString())

        if (commonResultItem?.type == REQUEST.SERIES)
            detailsPresenter.getSeriesDetails(commonResultItem.id.toString())
    }

    override fun activateShimmers() {

    }

    override fun deActivateShimmers() {

    }

    @SuppressLint("SetTextI18n")
    override fun onAdditionalInformationReady(additionalResultItem: AdditionalResultItem) {
        detailsInformation.text = additionalResultItem.information
        detailsDuration.text = additionalResultItem.duration + " min"
        for (item in additionalResultItem.genres!!) {
            detailsSubTitle.append(item?.name + " ")
        }
    }

    override fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    companion object {
        fun startMe(activity: Activity, bundle: Bundle, commonResultItem: CommonResultItem) {
            val intent = Intent(activity, DetailsActivity::class.java)
            intent.putExtra("common", commonResultItem as Serializable)
            Handler().postDelayed({
                activity.startActivity(intent, bundle)
            }, 100)
        }
    }
}