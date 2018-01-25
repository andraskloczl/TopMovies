package com.andraskloczl.movies.domain.models

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
data class DisplayedMovie(
	val id: Int,
	val title: String,
	val overview: String,
	val posterImageUrl: String,
	val backdropImageUrl: String,
	val voteAverage: Float,
	val popularityRank: Int
) : Parcelable