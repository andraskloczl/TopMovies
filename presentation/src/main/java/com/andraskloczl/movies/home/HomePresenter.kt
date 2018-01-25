package com.andraskloczl.movies.home

import android.util.Log
import com.andraskloczl.movies.domain.models.DataPage
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
		const val REMAINING_ITEMS_COUNT_THRESHOLD_BEFORE_LOAD = 10
	}

	val loadMovieDisposable = CompositeDisposable()
	val scrollDisposable = CompositeDisposable()

	private var currentPage = 0
	private var hasItemsToLoad = true

	override fun subscribe() {
		if(currentPage == 0) {
			loadMovies()
		}
		scrollDisposable.add(view.listenForScroll()
			.subscribe({ scrollState ->
				val remainingItemsCount = scrollState.totalItemsCount - scrollState.lastVisibleIndex - 1
				if (remainingItemsCount <= REMAINING_ITEMS_COUNT_THRESHOLD_BEFORE_LOAD) {
					loadMovies()
				}
			}))
	}

	override fun unsubscribe() {
		loadMovieDisposable.clear()
		scrollDisposable.clear()
	}

	private fun loadMovies() {
		if (loadMovieDisposable.size() > 0 || hasItemsToLoad.not()) return

		loadMovieDisposable.add(getTopRatedMovies.execute(GetTopRatedMoviesRequest(++currentPage))
			.observeOn(AndroidSchedulers.mainThread())
			.subscribe({ movieDataPage ->
				onMoviesLoaded(movieDataPage)
			}, { error ->
				currentPage--
				loadMovieDisposable.clear()
				Log.e(TAG, "loadMovies", error)
				view.displayError("")
			}))
	}

	private fun onMoviesLoaded(movieDataPage: DataPage<DisplayedMovie>) {
		if (movieDataPage.pageItems.isEmpty()) {
			hasItemsToLoad = false
		} else {
			view.displayMovies(movieDataPage.pageItems)
		}

		currentPage = movieDataPage.pageIndex
		loadMovieDisposable.clear()

	}

	override fun onMovieClicked(clickedMovie: DisplayedMovie) {
		view.goToDetailsScreen(clickedMovie)
	}
}