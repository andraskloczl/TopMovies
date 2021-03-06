package com.andraskloczl.movies.data.models

import com.google.gson.annotations.SerializedName

data class MovieAPIModel(
	@SerializedName("id")
	val id: Int,
	@SerializedName("title")
	val title: String,
	@SerializedName("overview")
	val overview: String,
	@SerializedName("vote_average")
	val voteAverage: Float,
	@SerializedName("popularity")
	val popularity: Float,
	@SerializedName("poster_path")
	val posterPath: String,
	@SerializedName("backdrop_path")
	val backdropPath: String
)