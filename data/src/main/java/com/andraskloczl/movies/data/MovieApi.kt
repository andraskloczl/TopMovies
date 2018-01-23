package com.andraskloczl.movies.data

import com.andraskloczl.movies.data.toprated.GetTopRatedMoviesResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface MovieApi {

	@GET("/movie/top_rated")
	fun getTopRatedMovies(@QueryMap queryMap: Map<String, String>): Single<GetTopRatedMoviesResponse>
}