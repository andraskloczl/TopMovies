package com.andraskloczl.movies.data.toprated

import com.andraskloczl.movies.domain.models.DataPage
import com.andraskloczl.movies.domain.models.GetTopRatedMoviesRequest
import com.andraskloczl.movies.domain.models.Movie
import io.reactivex.Observable

interface TopRatedMovieDataSource {
	fun getTopRatedMovies(request: GetTopRatedMoviesRequest): Observable<DataPage<Movie>>
}