package com.andraskloczl.movies.domain.models

data class DisplayedMovie(
	val id: Int,
	val title : String,
	val posterImageUrl: String,
	val backdropImageUrl: String,
	val voteAverage: Float,
	val popularityRank: Int
)