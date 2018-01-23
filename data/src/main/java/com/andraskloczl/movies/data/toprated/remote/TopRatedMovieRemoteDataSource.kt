package com.andraskloczl.movies.data.toprated.remote

import com.andraskloczl.movies.data.Keys
import com.andraskloczl.movies.data.MovieApi
import com.andraskloczl.movies.data.RequestParamsProvider
import com.andraskloczl.movies.data.toprated.TopRatedMovieDataSource
import com.andraskloczl.movies.data.toprated.TopRatedMoviesResponseDataMapper
import com.andraskloczl.movies.domain.models.DataPage
import com.andraskloczl.movies.domain.models.GetTopRatedMoviesRequest
import com.andraskloczl.movies.domain.models.DisplayedMovie
import com.andraskloczl.movies.domain.models.Movie
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class TopRatedMovieRemoteDataSource @Inject constructor(
	val movieApi: MovieApi,
	val paramsProvider: RequestParamsProvider,
	val dataMapper: TopRatedMoviesResponseDataMapper

) : TopRatedMovieDataSource {

	override fun getTopRatedMovies(request: GetTopRatedMoviesRequest): Observable<DataPage<Movie>> =
		paramsProvider.provideParams().flatMap { params ->
			params.put(Keys.Remote.PAGE, request.page.toString())
			movieApi.getTopRatedMovies(params)
		}
			.toObservable()
			.map { dataMapper.transform(it) }
			.subscribeOn(Schedulers.io())
}