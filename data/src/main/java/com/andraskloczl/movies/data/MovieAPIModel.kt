package com.andraskloczl.movies.data

import com.google.gson.annotations.SerializedName

data class MovieAPIModel(
	@SerializedName("id")
	val id: Int,
	@SerializedName("title")
	val title: String,
	@SerializedName("vote_average")
	val voteAverage: Float,
	@SerializedName("poster_path")
	val posterPath: String
)