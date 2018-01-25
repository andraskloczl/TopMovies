package com.andraskloczl.movies.data.similar.remote

import com.andraskloczl.movies.data.Keys
import com.andraskloczl.movies.data.MovieApi
import com.andraskloczl.movies.data.RequestParamsProvider
import com.andraskloczl.movies.data.mapper.MovieDataMapper
import com.andraskloczl.movies.data.similar.SimilarMoviesDataSource
import com.andraskloczl.movies.domain.models.DataPage
import com.andraskloczl.movies.domain.models.GetSimilarMoviesRequest
import com.andraskloczl.movies.domain.models.Movie
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SimilarMoviesRemoteDataSource @Inject constructor(
	val movieApi: MovieApi,
	val paramsProvider: RequestParamsProvider,
	val dataMapper: MovieDataMapper

) : SimilarMoviesDataSource {

	override fun getSimilarMovies(request: GetSimilarMoviesRequest): Observable<DataPage<Movie>> =
		paramsProvider.provideParams().flatMap { params ->
			params.put(Keys.Remote.PAGE, request.page.toString())
			movieApi.getSimilarMovies(request.movieId, params)
		}
			.toObservable()
			.map { dataMapper.transform(it) }
			.subscribeOn(Schedulers.io())
}