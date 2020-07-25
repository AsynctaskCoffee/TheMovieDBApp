package com.asynctaskcoffee.moviestemplate.ui.components.movies

import android.app.ActivityOptions
import android.os.Handler
import android.util.Pair
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.asynctaskcoffee.moviestemplate.R
import com.asynctaskcoffee.moviestemplate.data.remotemodels.ResultsItemMovies
import com.asynctaskcoffee.moviestemplate.ui.components.details.DetailsActivity
import com.asynctaskcoffee.moviestemplate.utils.ConvertCommonResult
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_card_movies.view.*

class MoviesAdapter(
    private val moviesList: List<ResultsItemMovies?>?, private val moviesFragment: MoviesFragment
) : RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {

    class MoviesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindTransitionIntent(moviesFragment: MoviesFragment, item: ResultsItemMovies) {
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
                    moviesFragment.activity,
                    pairImage,
                    pairTitle,
                    pairStar
                )

                itemView.moviesMotionLayout.transitionToEnd()
                Handler().postDelayed({
                    DetailsActivity.startMe(
                        moviesFragment.requireActivity(),
                        activityOptions.toBundle(),
                        ConvertCommonResult().convert(item)
                    )
                }, 100)
            }
        }

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_card_movies, parent, false)
        return MoviesViewHolder(view)
    }

    override fun getItemCount(): Int {
        return moviesList?.size ?: 0
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.bindTransitionIntent(moviesFragment, moviesList?.get(position)!!)
        holder.bindItems(moviesList[position]!!)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }
}