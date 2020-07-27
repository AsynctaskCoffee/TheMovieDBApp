package com.asynctaskcoffee.moviestemplate.ui.components.series

import android.app.ActivityOptions
import android.os.Handler
import android.util.Pair
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.asynctaskcoffee.moviestemplate.R
import com.asynctaskcoffee.moviestemplate.data.remotemodels.ResultsItemSeries
import com.asynctaskcoffee.moviestemplate.ui.components.details.DetailsActivity
import com.asynctaskcoffee.moviestemplate.utils.ConvertCommonResult
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_card_movies.view.*
import kotlinx.android.synthetic.main.item_card_series.view.*

class SeriesAdapter(
    private val seriesList: List<ResultsItemSeries?>?,
    private val seriesFragment: SeriesFragment
) :

    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var viewType: Int = 0

    class SeriesLinearViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindTransitionIntent(seriesFragment: SeriesFragment, item: ResultsItemSeries) {
            itemView.setOnClickListener {

                val pairImage = Pair<View, String>(
                    itemView.moviesImage,
                    itemView.context.resources.getString(R.string.transitionImage)
                )
                val pairTitle = Pair<View, String>(
                    itemView.moviesTitle,
                    itemView.context.resources.getString(R.string.transitionTitle)
                )
                val pairStar = Pair<View, String>(
                    itemView.cardStarHolder,
                    itemView.context.resources.getString(R.string.transitionCard)
                )

                val activityOptions = ActivityOptions.makeSceneTransitionAnimation(
                    seriesFragment.activity,
                    pairImage,
                    pairTitle,
                    pairStar
                )

                itemView.moviesMotionLayout.transitionToEnd()
                Handler().postDelayed({
                    DetailsActivity.startMe(
                        seriesFragment.requireActivity(),
                        activityOptions.toBundle(),
                        ConvertCommonResult().convert(item)
                    )
                }, 100)
            }
        }

        fun bindItems(item: ResultsItemSeries) {
            Glide.with(itemView.moviesImage.context)
                .load("https://image.tmdb.org/t/p/w300" + item.posterPath)
                .into(itemView.moviesImage)
            itemView.moviesTitle.text = item.name
            itemView.moviesSubTitle.text = item.overview
            itemView.moviesDate.text = item.firstAirDate
            itemView.moviesRating.text = item.voteAverage.toString()
        }
    }

    class SeriesNormalViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bindTransitionIntent(seriesFragment: SeriesFragment, item: ResultsItemSeries) {
            itemView.setOnClickListener {

                val pairImageHolder = Pair<View, String>(
                    itemView.seriesImageHolder,
                    itemView.context.resources.getString(R.string.transitionImageLayout)
                )
                val pairTitle = Pair<View, String>(
                    itemView.seriesTitle,
                    itemView.context.resources.getString(R.string.transitionTitle)
                )
                val pairStar = Pair<View, String>(
                    itemView.cardStarHolderSeries,
                    itemView.context.resources.getString(R.string.transitionCard)
                )

                val activityOptions = ActivityOptions.makeSceneTransitionAnimation(
                    seriesFragment.activity,
                    pairImageHolder,
                    pairTitle,
                    pairStar
                )

                DetailsActivity.startMe(
                    seriesFragment.requireActivity(),
                    activityOptions.toBundle(),
                    ConvertCommonResult().convert(item)
                )
            }
        }

        fun bindItems(item: ResultsItemSeries) {
            Glide.with(itemView.seriesImage.context)
                .load("https://image.tmdb.org/t/p/w300" + item.posterPath)
                .into(itemView.seriesImage)
            itemView.seriesTitle.text = item.name
            itemView.seriesRating.text = item.voteAverage.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == 0) {
            val view =
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_card_series,
                    parent,
                    false
                )
            return SeriesNormalViewHolder(view)
        } else {
            val view =
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_card_movies,
                    parent,
                    false
                )
            return SeriesLinearViewHolder(view)
        }
    }

    override fun getItemCount(): Int {
        return seriesList?.size ?: 0
    }

    override fun getItemViewType(position: Int): Int {
        return viewType
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is SeriesNormalViewHolder) {
            holder.bindTransitionIntent(seriesFragment, seriesList?.get(position)!!)
            holder.bindItems(seriesList[position]!!)
        } else if (holder is SeriesLinearViewHolder) {
            holder.bindTransitionIntent(seriesFragment, seriesList?.get(position)!!)
            holder.bindItems(seriesList[position]!!)
        }
    }
}