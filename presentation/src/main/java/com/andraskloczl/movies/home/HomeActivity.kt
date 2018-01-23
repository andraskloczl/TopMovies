package com.andraskloczl.movies.home

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.andraskloczl.movies.R
import com.andraskloczl.movies.AbstractActivity
import com.andraskloczl.movies.domain.models.DisplayedMovie
import com.andraskloczl.movies.home.list.MovieListAdapter
import kotlinx.android.synthetic.main.activity_home.*
import javax.inject.Inject


class HomeActivity : AbstractActivity(), HomeContract.View {

	override val layoutResourceId: Int
		get() = R.layout.activity_home

	@Inject lateinit var presenter: HomeContract.Presenter

	lateinit var listAdapter: MovieListAdapter

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		presenter.subscribe()
	}

	override fun onResume() {
		super.onResume()
		presenter.subscribe()
	}

	override fun onPause() {
		super.onPause()
		presenter.unsubscribe()
	}

//	override fun onDestroy() {
//		super.onDestroy()
//		presenter.unsubscribe()
//	}

	override fun displayMovies(movies: List<DisplayedMovie>) {
		recyclerView.setHasFixedSize(true)
		recyclerView.layoutManager = LinearLayoutManager(this)

		listAdapter = MovieListAdapter(this, movies, { clickedPost ->
			presenter.onMovieClicked(clickedPost)
		})
		recyclerView.adapter = listAdapter
	}

	override fun displayError(errorText: String) {
		Toast.makeText(this, errorText, Toast.LENGTH_SHORT).show()
	}

	override fun goToDetailsScreen(movie: DisplayedMovie) {
//		startActivity(DetailActivity.newIntent(this, postToShow))
	}
}
