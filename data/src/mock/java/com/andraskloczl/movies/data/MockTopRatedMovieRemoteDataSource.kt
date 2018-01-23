package com.andraskloczl.movies.data

import android.content.Context
import com.andraskloczl.movies.data.toprated.GetTopRatedMoviesResponse
import com.andraskloczl.movies.data.toprated.TopRatedMovieDataSource
import com.andraskloczl.movies.data.toprated.TopRatedMoviesResponseDataMapper
import com.andraskloczl.movies.domain.models.DataPage
import com.andraskloczl.movies.domain.models.GetTopRatedMoviesRequest
import com.andraskloczl.movies.domain.models.Movie
import com.google.gson.Gson
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MockTopRatedMovieRemoteDataSource @Inject constructor(
	val context: Context,
	val gson: Gson,
	val dataMapper: TopRatedMoviesResponseDataMapper
) : TopRatedMovieDataSource {

	override fun getTopRatedMovies(request: GetTopRatedMoviesRequest): Observable<DataPage<Movie>> =
		Observable.fromCallable {
			val inputStream = context.resources.openRawResource(R.raw.topratedmovies)
			val jsonString = inputStream.bufferedReader().use { it.readText() }  // defaults to UTF-8
			gson.fromJson(jsonString, GetTopRatedMoviesResponse::class.java)
		}
			.map { dataMapper.transform(it) }
			.subscribeOn(Schedulers.io())


}
