package com.andraskloczl.movies.detail

import android.util.Log
import com.andraskloczl.movies.domain.models.DataPage
import com.andraskloczl.movies.domain.models.DisplayedMovie
import com.andraskloczl.movies.domain.models.GetSimilarMoviesRequest
import com.andraskloczl.movies.domain.usecases.getsimilarmovies.GetSimilarMovies
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

class DetailPresenter(
	val view: DetailContract.View,
	val getSimilarMovies: GetSimilarMovies
) : DetailContract.Presenter {

	companion object {
		val TAG = DetailPresenter::class.java.simpleName
		const val REMAINING_ITEMS_COUNT_THRESHOLD_BEFORE_LOAD = 5
	}

	lateinit var movie: DisplayedMovie

	private var currentPage = 0
	private var hasItemsToLoad = true

	val compositeDisposable = CompositeDisposable()

	override fun setSelectedMovie(movie: DisplayedMovie) {
		this.movie = movie
	}

	override fun subscribe() {
		if (::movie.isInitialized.not()) error("Movie has not been passed")
		if (currentPage == 0) {
			view.displaySimilarMovies(listOf(movie))
			loadSimilarMovies()
		}
	}

	private fun loadSimilarMovies() {
		if (compositeDisposable.size() > 0 || hasItemsToLoad.not()) return

		val getSimilarMoviesRequest = GetSimilarMoviesRequest(movie.id.toString(), ++currentPage)
		compositeDisposable.add(getSimilarMovies.execute(getSimilarMoviesRequest)
			.observeOn(AndroidSchedulers.mainThread())
			.subscribe({ similarMovies ->
				onSimilarMoviesLoaded(similarMovies)
			}, { error ->
				currentPage--
				compositeDisposable.clear()
				Log.e(TAG, "loadMovies", error)
				view.displayError("")
			}))
	}

	private fun onSimilarMoviesLoaded(similarMovies: DataPage<DisplayedMovie>) {
		if (similarMovies.pageItems.isEmpty()) {
			hasItemsToLoad = false
		} else {
			view.displaySimilarMovies(similarMovies.pageItems)
		}
		currentPage = similarMovies.pageIndex
		compositeDisposable.clear()
	}

	override fun unsubscribe() {
		compositeDisposable.clear()
	}

	override fun onPageSelected(position: Int, remainingItemsCount: Int) {
		if (remainingItemsCount <= REMAINING_ITEMS_COUNT_THRESHOLD_BEFORE_LOAD) {
			loadSimilarMovies()
		}
	}
}