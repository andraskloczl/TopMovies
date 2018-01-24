package com.andraskloczl.movies.domain.models

data class Movie(
	val id: Int,
	val title : String,
	val overview : String,
	val posterImageUrl: String,
	val backdropImageUrl: String,
	val voteAverage: Float,
	val popularity: Float
)