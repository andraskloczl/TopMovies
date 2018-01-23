package com.andraskloczl.movies.domain.usecases

import com.andraskloczl.movies.domain.models.DataPage
import com.andraskloczl.movies.domain.models.DisplayedMovie
import com.andraskloczl.movies.domain.models.GetTopRatedMoviesRequest
import com.andraskloczl.movies.domain.repository.TopRatedMovieRepository
import io.reactivex.Observable

class GetTopRatedMoviesImpl(
	val topRatedMovieRepository: TopRatedMovieRepository
) : GetTopRatedMovies {

	override fun execute(data: GetTopRatedMoviesRequest): Observable<DataPage<DisplayedMovie>>  =
		topRatedMovieRepository.getTopRatedMovies(data)

}