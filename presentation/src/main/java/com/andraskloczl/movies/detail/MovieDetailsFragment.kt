package com.andraskloczl.movies.detail

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife
import com.andraskloczl.movies.R
import com.andraskloczl.movies.domain.models.DisplayedMovie
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_movie_details.*

class MovieDetailsFragment : Fragment() {

	companion object {
		const val MOVIE_KEY = "movie"

		fun newInstance(movie: DisplayedMovie): MovieDetailsFragment {
			val bundle = Bundle().apply { putParcelable(MovieDetailsFragment.MOVIE_KEY, movie) }
			return MovieDetailsFragment().apply { arguments = bundle }
		}
	}

	lateinit var movie: DisplayedMovie

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		val view = inflater.inflate(R.layout.fragment_movie_details, container, false) as ViewGroup
		ButterKnife.bind(this, view)
		return view
	}

	override fun onActivityCreated(savedInstanceState: Bundle?) {
		super.onActivityCreated(savedInstanceState)

		movie = arguments!!.getParcelable(MOVIE_KEY) as DisplayedMovie
		initUI()
	}

	private fun initUI() {
		if (isDetached) return

		collapsingToolbar.title = movie.title

		movieRatingTextView.text = getString(R.string.movie_rating, movie.voteAverage)
		movieOverViewTextView.text = movie.overview

		Glide.with(activity!!)
			.load(movie.backdropImageUrl)
			.into(movieBackdropImageView)

		Glide.with(activity!!)
			.load(movie.posterImageUrl)
			.into(moviePosterImageView)
	}

}