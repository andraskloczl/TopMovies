package com.andraskloczl.movies.home.list

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.andraskloczl.movies.R
import com.andraskloczl.movies.domain.models.DisplayedMovie
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.list_item_movie.view.*


class MovieListAdapter(
	private val context: Context,
	private val movies: List<DisplayedMovie>,
	private val movieSelectListener: (DisplayedMovie) -> Unit
) : RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {


	override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
		val itemView = LayoutInflater.from(context).inflate(R.layout.list_item_movie, parent, false)
		val viewHolder = ViewHolder(itemView)

		itemView.setOnClickListener { movieSelectListener.invoke(it.tag as DisplayedMovie) }

		return viewHolder
	}

	override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
		if (holder == null) return

		val movie = movies[position]

		val layoutParams = holder.itemView.layoutParams as StaggeredGridLayoutManager.LayoutParams

		val heightDimen = when (movie.popularityRank) {
			in 8..10 -> R.dimen.movie_list_item_image_height_large
			in 4..7 -> R.dimen.movie_list_item_image_height_medium
			else -> R.dimen.movie_list_item_image_height_small
		}

		layoutParams.height = context.resources.getDimension(heightDimen).toInt()

		Glide.with(context)
			.load(movie.posterImageUrl)
			.into(holder.movieImageView)

		holder.movieTitleTextView.text = movie.title
		holder.movieRatingTextView.text = movie.voteAverage.toString()
		holder.itemView.tag = movie
	}

	override fun getItemCount(): Int = movies.size


	class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
		val movieImageView = itemView.movieImageView
		val movieTitleTextView = itemView.movieTitleTextView
		val movieRatingTextView = itemView.movieRatingTextView
	}
}