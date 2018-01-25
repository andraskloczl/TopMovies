package com.andraskloczl.movies.domain.models

data class GetSimilarMoviesRequest(
	val movieId: String,
	val page: Int
)
