package com.andraskloczl.movies.domain.usecases.getsimilarmovies

import com.andraskloczl.movies.domain.models.DataPage
import com.andraskloczl.movies.domain.models.DisplayedMovie
import com.andraskloczl.movies.domain.models.GetSimilarMoviesRequest
import io.reactivex.Observable

interface GetSimilarMovies {
	fun execute(data: GetSimilarMoviesRequest): Observable<DataPage<DisplayedMovie>>
}