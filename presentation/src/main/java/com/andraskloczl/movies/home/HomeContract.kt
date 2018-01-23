package com.andraskloczl.movies.home

import com.andraskloczl.movies.BasePresenter
import com.andraskloczl.movies.domain.models.DisplayedMovie

interface HomeContract {

	interface View {
		fun displayMovies(movies: List<DisplayedMovie>)
		fun displayError(errorText: String)
		fun goToDetailsScreen(movieToShow: DisplayedMovie)
	}

	interface Presenter : BasePresenter {
		fun onMovieClicked(clickedMovie: DisplayedMovie)
		fun onScrolledToBottom()
	}
}