package com.andraskloczl.movies.data.models

import com.google.gson.annotations.SerializedName

data class PaginatedMoviesResponse(
	@SerializedName("page")
	val page: Int,
	@SerializedName("total_results")
	val totalResults: Int,
	@SerializedName("total_pages")
	val totalPages: Int,
	@SerializedName("results")
	val results: List<MovieAPIModel>
)