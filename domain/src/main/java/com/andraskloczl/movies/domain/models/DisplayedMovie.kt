package com.andraskloczl.movies.domain.models

data class DisplayedMovie(
	val id: Int,
	val title : String,
	val imagePath: String,
	val voteAverage: Float
)