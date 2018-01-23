package com.andraskloczl.movies.home

import com.andraskloczl.movies.domain.models.DisplayedMovie
import com.andraskloczl.movies.domain.models.GetTopRatedMoviesRequest
import com.andraskloczl.movies.domain.usecases.GetTopRatedMovies
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

class HomePresenter(
	val view: HomeContract.View,
	val getTopRatedMovies: GetTopRatedMovies
) : HomeContract.Presenter {

	val compositeDisposable = CompositeDisposable()
	private var currentPage = 1

	override fun subscribe() {
		loadMovies()
	}

	override fun unsubscribe() {
		compositeDisposable.clear()
	}

	private fun loadMovies() {
		currentPage!!
		compositeDisposable.add(getTopRatedMovies.execute(GetTopRatedMoviesRequest(currentPage))
			.observeOn(AndroidSchedulers.mainThread())
			.subscribe({ movieDataPage ->
				currentPage = movieDataPage.pageIndex
				view.displayMovies(movieDataPage.pageItems)
			}, { error ->
				view.displayError("error while loading")
			}))
	}

	override fun onMovieClicked(clickedMovie: DisplayedMovie) {
		view.goToDetailsScreen(clickedMovie)
	}
}