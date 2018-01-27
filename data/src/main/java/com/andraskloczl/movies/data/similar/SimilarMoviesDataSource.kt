package com.andraskloczl.movies.data.similar

import com.andraskloczl.movies.domain.models.DataPage
import com.andraskloczl.movies.domain.models.GetSimilarMoviesRequest
import com.andraskloczl.movies.domain.models.Movie
import io.reactivex.Observable

interface SimilarMoviesDataSource {
	fun getSimilarMovies(request: GetSimilarMoviesRequest): Observable<DataPage<Movie>>
}