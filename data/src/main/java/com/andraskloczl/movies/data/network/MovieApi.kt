package com.andraskloczl.movies.data.network

import com.andraskloczl.movies.data.models.PaginatedMoviesResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface MovieApi {

	@GET("movie/top_rated")
	fun getTopRatedMovies(@QueryMap(encoded = true) queryMap: Map<String, String>): Single<PaginatedMoviesResponse>

	@GET("movie/{movie_id}/similar")
	fun getSimilarMovies(@Path(value = "movie_id", encoded = true) movieId: String,
						 @QueryMap(encoded = true) queryMap: Map<String, String>): Single<PaginatedMoviesResponse>
}