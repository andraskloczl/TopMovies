package com.andraskloczl.movies.data.similar

import com.andraskloczl.movies.domain.models.DataPage
import com.andraskloczl.movies.domain.models.GetSimilarMoviesRequest
import com.andraskloczl.movies.domain.models.Movie
import com.andraskloczl.movies.domain.repository.SimilarMoviesRepository
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SimilarMoviesRepositoryImpl
@Inject constructor(
	private val remoteDataSource: SimilarMoviesDataSource,
	private val localDataSource: SimilarMoviesDataSource
) : SimilarMoviesRepository {

	override fun getSimilarMovies(request: GetSimilarMoviesRequest): Observable<DataPage<Movie>> {
		return remoteDataSource.getSimilarMovies(request)
	}
}


