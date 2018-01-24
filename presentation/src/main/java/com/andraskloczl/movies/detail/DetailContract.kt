package com.andraskloczl.movies.detail

import com.andraskloczl.movies.BasePresenter
import com.andraskloczl.movies.domain.models.DataPage
import com.andraskloczl.movies.domain.models.DisplayedMovie

interface DetailContract {

	interface View {
		fun displaySimilarMovies(similarMovies: List<DisplayedMovie>)
		fun displayError(errorText: String)
	}

	interface Presenter : BasePresenter {
		fun setSelectedMovie(movie: DisplayedMovie)
		fun onPageSelected(position: Int, remainingItemsCount: Int)
	}
}