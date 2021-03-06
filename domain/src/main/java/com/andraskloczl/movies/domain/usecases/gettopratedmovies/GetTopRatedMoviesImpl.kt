package com.andraskloczl.movies.domain.usecases.gettopratedmovies

import com.andraskloczl.movies.domain.mapper.MovieMapper
import com.andraskloczl.movies.domain.models.DataPage
import com.andraskloczl.movies.domain.models.DisplayedMovie
import com.andraskloczl.movies.domain.models.GetTopRatedMoviesRequest
import com.andraskloczl.movies.domain.repository.TopRatedMovieRepository
import com.andraskloczl.movies.domain.utils.PopularityRankCalculator
import io.reactivex.Observable

class GetTopRatedMoviesImpl(
	private val topRatedMovieRepository: TopRatedMovieRepository,
	private val movieMapper: MovieMapper,
	private val popularityRankCalculator: PopularityRankCalculator
) : GetTopRatedMovies {

	override fun execute(data: GetTopRatedMoviesRequest): Observable<DataPage<DisplayedMovie>> =
		topRatedMovieRepository.getTopRatedMovies(data)
			.flatMap { movieDataPage ->
				popularityRankCalculator.init(movieDataPage.pageItems)
				Observable.fromIterable(movieDataPage.pageItems)
					.map { movieMapper.transform(it, popularityRankCalculator) }
					.toList()
					.map { DataPage(movieDataPage.pageIndex, it) }
					.toObservable()
			}

}