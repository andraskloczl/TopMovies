package com.andraskloczl.movies.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.andraskloczl.movies.AbstractActivity
import com.andraskloczl.movies.R
import com.andraskloczl.movies.domain.models.DisplayedMovie
import com.andraskloczl.movies.util.Extensions.onPageChangeListener
import kotlinx.android.synthetic.main.activity_detail.*
import javax.inject.Inject

class DetailActivity : AbstractActivity(), DetailContract.View {

	companion object {
		const val MOVIE_KEY = "movie"
		const val VIEWPAGER_OFFSCREEN_PAGE_LIMIT = 2

		fun newIntent(context: Context, movie: DisplayedMovie): Intent =
			Intent(context, DetailActivity::class.java).apply {
				putExtra(MOVIE_KEY, movie)
			}
	}

	override val layoutResourceId: Int
		get() = R.layout.activity_detail


	lateinit var pagerAdapter: SimilarMoviesPagerAdapter

	val movies = ArrayList<DisplayedMovie>()

	@Inject
	lateinit var presenter: DetailContract.Presenter

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		initUI()

		val movie = intent.getParcelableExtra<DisplayedMovie>(MOVIE_KEY)
		if (movie == null) error("MOVIE NOT PASSED TO DETAIL SCREEN")

		presenter.setSelectedMovie(movie)
	}

	private fun initUI() {
		pagerAdapter = SimilarMoviesPagerAdapter(supportFragmentManager, movies)
		viewPager.adapter = pagerAdapter
		viewPager.offscreenPageLimit = VIEWPAGER_OFFSCREEN_PAGE_LIMIT

		viewPager.onPageChangeListener { position ->
			val remainingItemsCount = movies.size - position - 1
			presenter.onPageSelected(position, remainingItemsCount)
		}
	}

	override fun onBackPressed() {
		super.onBackPressed()
		overridePendingTransition(R.anim.slide_in_from_left, R.anim.slide_out_to_right)
	}

	override fun onResume() {
		super.onResume()
		presenter.subscribe()
	}

	override fun onPause() {
		super.onPause()
		presenter.unsubscribe()
	}

	override fun displaySimilarMovies(similarMovies: List<DisplayedMovie>) {
		movies.addAll(similarMovies)
		pagerAdapter.notifyDataSetChanged()
	}

	override fun displayError(errorText: String) {
		val errorMessage = if (errorText.isNotEmpty()) errorText else getString(R.string.default_error_message)
		Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
	}
}
