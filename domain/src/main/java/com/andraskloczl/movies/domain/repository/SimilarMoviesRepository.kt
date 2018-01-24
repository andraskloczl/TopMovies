package com.andraskloczl.movies.domain.repository

import com.andraskloczl.movies.domain.models.DataPage
import com.andraskloczl.movies.domain.models.GetSimilarMoviesRequest
import com.andraskloczl.movies.domain.models.Movie
import io.reactivex.Observable

interface SimilarMoviesRepository {

	fun getSimilarMovies(request: GetSimilarMoviesRequest): Observable<DataPage<Movie>>
}