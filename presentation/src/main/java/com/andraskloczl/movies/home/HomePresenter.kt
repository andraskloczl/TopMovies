package com.andraskloczl.movies.home

import android.util.Log
import com.andraskloczl.movies.domain.models.DisplayedMovie
import com.andraskloczl.movies.domain.models.GetTopRatedMoviesRequest
import com.andraskloczl.movies.domain.usecases.gettopratedmovies.GetTopRatedMovies
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

class HomePresenter(
	val view: HomeContract.View,
	val getTopRatedMovies: GetTopRatedMovies
) : HomeContract.Presenter {

	companion object {
		val TAG = HomePresenter::class.java.simpleName
	}

	val compositeDisposable = CompositeDisposable()
	private var currentPage = 1

	override fun subscribe() {
		loadMovies()
	}

	override fun unsubscribe() {
		compositeDisposable.clear()
	}

	private fun loadMovies() {
		compositeDisposable.add(getTopRatedMovies.execute(GetTopRatedMoviesRequest(currentPage))
			.observeOn(AndroidSchedulers.mainThread())
			.subscribe({ movieDataPage ->
				currentPage = movieDataPage.pageIndex
				view.displayMovies(movieDataPage.pageItems)
			}, { error ->
				Log.e(TAG, "loadMovies", error)
				view.displayError("")
			}))
	}

	override fun onMovieClicked(clickedMovie: DisplayedMovie) {
		view.goToDetailsScreen(clickedMovie)
	}

	override fun onScrolledToBottom() {
		loadNextPage()
	}

	private fun loadNextPage() {
		currentPage++
		loadMovies()
	}
}