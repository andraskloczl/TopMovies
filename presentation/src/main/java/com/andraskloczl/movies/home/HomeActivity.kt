package com.andraskloczl.movies.home

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.util.Log
import android.widget.Toast
import com.andraskloczl.movies.AbstractActivity
import com.andraskloczl.movies.R
import com.andraskloczl.movies.detail.DetailActivity
import com.andraskloczl.movies.domain.models.DisplayedMovie
import com.andraskloczl.movies.home.list.MovieListAdapter
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.toolbar.*
import javax.inject.Inject

class HomeActivity : AbstractActivity(), HomeContract.View {

	override val layoutResourceId: Int
		get() = R.layout.activity_home

	@Inject
	lateinit var presenter: HomeContract.Presenter

	lateinit var listAdapter: MovieListAdapter

	val movies = ArrayList<DisplayedMovie>()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		initUI()
	}

	private fun initUI() {
		setSupportActionBar(toolbar)
		supportActionBar!!.title = getString(R.string.app_name)

		val layoutManager = StaggeredGridLayoutManager(2, 1)
		recyclerView.layoutManager = layoutManager

		listAdapter = MovieListAdapter(this, movies, { clickedPost ->
			presenter.onMovieClicked(clickedPost)
		})
		recyclerView.adapter = listAdapter

		recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
			val DIRECTION_DOWN = 1
			override fun onScrollStateChanged(view: RecyclerView?, newState: Int) {
				super.onScrollStateChanged(recyclerView, newState)
				Log.d(TAG, "onScrollStateChanged: $newState ")
				if (newState == RecyclerView.SCROLL_STATE_IDLE &&
					!recyclerView.canScrollVertically(DIRECTION_DOWN)) {
					presenter.onScrolledToBottom()
				}
			}
		})
	}

	override fun onResume() {
		super.onResume()
		presenter.subscribe()
	}

	override fun onPause() {
		super.onPause()
		presenter.unsubscribe()
	}

	override fun displayMovies(newMovies: List<DisplayedMovie>) {
		val beforeSize = movies.size
		this.movies.addAll(newMovies)
		listAdapter.notifyItemRangeInserted(beforeSize, newMovies.size)
	}

	override fun displayError(errorText: String) {
		val errorMessage = if (errorText.isNotEmpty()) errorText else getString(R.string.default_error_message)
		Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
	}

	override fun goToDetailsScreen(movieToShow: DisplayedMovie) {
		startActivity(DetailActivity.newIntent(this, movieToShow))
	}
}
