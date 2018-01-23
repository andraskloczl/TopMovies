package com.andraskloczl.movies.data.toprated

import com.andraskloczl.movies.data.MovieAPIModel
import com.google.gson.annotations.SerializedName

data class GetTopRatedMoviesResponse (
	@SerializedName("page")
	val page: Int,
	@SerializedName("total_results")
	val totalResults: Int,
	@SerializedName("total_pages")
	val totalPages: Int,
	@SerializedName("results")
	val results: List<MovieAPIModel>
)