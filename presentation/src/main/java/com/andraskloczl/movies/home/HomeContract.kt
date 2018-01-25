package com.andraskloczl.movies.home

import com.andraskloczl.movies.BasePresenter
import com.andraskloczl.movies.domain.models.DisplayedMovie
import com.andraskloczl.movies.home.list.ScrollState
import io.reactivex.Observable

interface HomeContract {

	interface View {
		fun displayMovies(movies: List<DisplayedMovie>)
		fun displayError(errorText: String)
		fun goToDetailsScreen(movieToShow: DisplayedMovie)
		fun listenForScroll(): Observable<ScrollState>
	}

	interface Presenter : BasePresenter {
		fun onMovieClicked(clickedMovie: DisplayedMovie)
	}
}