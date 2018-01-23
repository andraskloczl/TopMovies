package com.andraskloczl.movies.domain.usecases

import com.andraskloczl.movies.domain.models.DataPage
import com.andraskloczl.movies.domain.models.DisplayedMovie
import com.andraskloczl.movies.domain.models.GetTopRatedMoviesRequest
import io.reactivex.Observable

interface GetTopRatedMovies {
	fun execute(data: GetTopRatedMoviesRequest): Observable<DataPage<DisplayedMovie>>
}