package com.asynctaskcoffee.moviestemplate.ui.components.series

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.asynctaskcoffee.moviestemplate.R
import com.asynctaskcoffee.moviestemplate.data.remotemodels.ResultsItemMovies
import com.asynctaskcoffee.moviestemplate.data.remotemodels.ResultsItemSeries
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_card_series.view.*

class SeriesAdapter(private val seriesList: List<ResultsItemSeries?>?) :
    RecyclerView.Adapter<SeriesAdapter.SeriesViewHolder>() {

    class SeriesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindItems(item: ResultsItemSeries) {
            Glide.with(itemView.seriesImage.context)
                .load("https://image.tmdb.org/t/p/w300" + item.posterPath)
                .into(itemView.seriesImage)
            itemView.seriesTitle.text = item.originalName
            itemView.seriesSubTitle.text = item.overview
            itemView.seriesDate.text = item.firstAirDate
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
        holder.bindItems(seriesList?.get(position)!!)
    }
}