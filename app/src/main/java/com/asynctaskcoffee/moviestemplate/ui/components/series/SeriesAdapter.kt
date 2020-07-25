package com.asynctaskcoffee.moviestemplate.ui.components.series

import android.app.ActivityOptions
import android.content.Intent
import android.util.Pair
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.asynctaskcoffee.moviestemplate.R
import com.asynctaskcoffee.moviestemplate.data.remotemodels.ResultsItemMovies
import com.asynctaskcoffee.moviestemplate.data.remotemodels.ResultsItemSeries
import com.asynctaskcoffee.moviestemplate.ui.components.details.DetailsActivity
import com.asynctaskcoffee.moviestemplate.ui.components.movies.MoviesFragment
import com.asynctaskcoffee.moviestemplate.utils.ConvertCommonResult
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_card_movies.view.*
import kotlinx.android.synthetic.main.item_card_series.view.*
import kotlinx.android.synthetic.main.item_card_series.view.cardStarHolder

class SeriesAdapter(
    private val seriesList: List<ResultsItemSeries?>?,
    private val seriesFragment: SeriesFragment
) :
    RecyclerView.Adapter<SeriesAdapter.SeriesViewHolder>() {

    class SeriesViewHolder(view: View) : RecyclerView.ViewHolder(view) {

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
                    itemView.cardStarHolder,
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeriesViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_card_series, parent, false)
        return SeriesViewHolder(view)
    }

    override fun getItemCount(): Int {
        return seriesList?.size ?: 0
    }

    override fun onBindViewHolder(holder: SeriesViewHolder, position: Int) {
        holder.bindTransitionIntent(seriesFragment, seriesList?.get(position)!!)
        holder.bindItems(seriesList?.get(position)!!)
    }
}