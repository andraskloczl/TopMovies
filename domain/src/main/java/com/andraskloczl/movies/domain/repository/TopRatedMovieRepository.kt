package com.andraskloczl.movies.domain.repository

import com.andraskloczl.movies.domain.models.DataPage
import com.andraskloczl.movies.domain.models.GetTopRatedMoviesRequest
import com.andraskloczl.movies.domain.models.DisplayedMovie
import io.reactivex.Observable

interface TopRatedMovieRepository {

	fun getTopRatedMovies(request: GetTopRatedMoviesRequest): Observable<DataPage<DisplayedMovie>>
}