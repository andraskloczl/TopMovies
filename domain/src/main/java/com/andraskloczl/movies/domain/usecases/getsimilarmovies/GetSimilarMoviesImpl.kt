package com.andraskloczl.movies.domain.usecases.getsimilarmovies

import com.andraskloczl.movies.domain.mapper.MovieMapper
import com.andraskloczl.movies.domain.models.DataPage
import com.andraskloczl.movies.domain.models.DisplayedMovie
import com.andraskloczl.movies.domain.models.GetSimilarMoviesRequest
import com.andraskloczl.movies.domain.repository.SimilarMoviesRepository
import com.andraskloczl.movies.domain.utils.PopularityRankCalculator
import io.reactivex.Observable

class GetSimilarMoviesImpl(
	val similarMoviesRepository: SimilarMoviesRepository,
	val movieMapper: MovieMapper,
	val popularityRankCalculator: PopularityRankCalculator
) : GetSimilarMovies {

	override fun execute(data: GetSimilarMoviesRequest): Observable<DataPage<DisplayedMovie>> =
		similarMoviesRepository.getSimilarMovies(data)
			.flatMap { movieDataPage ->
				popularityRankCalculator.init(movieDataPage.pageItems)
				Observable.fromIterable(movieDataPage.pageItems)
					.map { movieMapper.transform(it, popularityRankCalculator) }
					.toList()
					.map { DataPage(movieDataPage.pageIndex, it) }
					.toObservable()
			}

}