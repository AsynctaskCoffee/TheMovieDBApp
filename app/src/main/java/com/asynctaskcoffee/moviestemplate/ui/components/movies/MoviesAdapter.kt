package com.asynctaskcoffee.moviestemplate.ui.components.movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.asynctaskcoffee.moviestemplate.R
import com.asynctaskcoffee.moviestemplate.data.remotemodels.ResultsItemMovies
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_card_movies.view.*

class MoviesAdapter(private val moviesList: List<ResultsItemMovies?>?) :
    RecyclerView.Adapter<MoviesAdapter.SeriesViewHolder>() {

    class SeriesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindItems(item: ResultsItemMovies) {
            Glide.with(itemView.moviesImage.context)
                .load("https://image.tmdb.org/t/p/w300" + item.posterPath)
                .into(itemView.moviesImage)
            itemView.moviesTitle.text = item.title
            itemView.moviesSubTitle.text = item.overview
            itemView.moviesDate.text = item.releaseDate
            itemView.moviesRating.text = item.voteAverage.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeriesViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_card_movies, parent, false)
        return SeriesViewHolder(view)
    }

    override fun getItemCount(): Int {
        return moviesList?.size ?: 0
    }

    override fun onBindViewHolder(holder: SeriesViewHolder, position: Int) {
        holder.bindItems(moviesList?.get(position)!!)
    }
}