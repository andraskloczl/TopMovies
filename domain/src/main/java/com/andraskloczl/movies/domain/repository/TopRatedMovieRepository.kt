package com.andraskloczl.movies.domain.repository

import com.andraskloczl.movies.domain.models.DataPage
import com.andraskloczl.movies.domain.models.GetTopRatedMoviesRequest
import com.andraskloczl.movies.domain.models.DisplayedMovie
import com.andraskloczl.movies.domain.models.Movie
import io.reactivex.Observable

interface TopRatedMovieRepository {

	fun getTopRatedMovies(request: GetTopRatedMoviesRequest): Observable<DataPage<Movie>>
}