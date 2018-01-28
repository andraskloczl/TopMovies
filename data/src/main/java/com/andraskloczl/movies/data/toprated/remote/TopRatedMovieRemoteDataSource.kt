package com.andraskloczl.movies.data.toprated.remote

import com.andraskloczl.movies.data.Keys
import com.andraskloczl.movies.data.mapper.MovieDataMapper
import com.andraskloczl.movies.data.network.MovieApi
import com.andraskloczl.movies.data.network.RequestParamsProvider
import com.andraskloczl.movies.data.toprated.TopRatedMovieDataSource
import com.andraskloczl.movies.domain.models.DataPage
import com.andraskloczl.movies.domain.models.GetTopRatedMoviesRequest
import com.andraskloczl.movies.domain.models.Movie
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class TopRatedMovieRemoteDataSource @Inject constructor(
	private val movieApi: MovieApi,
	private val paramsProvider: RequestParamsProvider,
	private val dataMapper: MovieDataMapper

) : TopRatedMovieDataSource {

	override fun getTopRatedMovies(request: GetTopRatedMoviesRequest): Observable<DataPage<Movie>> {
		val params = paramsProvider.provideParams()
			.apply { put(Keys.Remote.PAGE, request.page.toString()) }
		return movieApi.getTopRatedMovies(params)
			.toObservable()
			.map { dataMapper.transform(it) }
			.subscribeOn(Schedulers.io())
	}
}