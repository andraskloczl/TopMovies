package com.andraskloczl.movies.domain.utils

import com.andraskloczl.movies.domain.models.Movie

class PopularityRankCalculator {

	companion object {
		const val MAX_RANK = 10;
		const val MIN_RANK = 1;
	}

	var minPopularity: Float = 0f
	var popularityPerRank: Float = 0f

	fun init(movies: List<Movie>) {
		val popularities = movies.map { it.popularity }
		minPopularity = popularities.min() ?: 0f
		val maxPopularity = popularities.max() ?: 0f
		popularityPerRank = (maxPopularity - minPopularity) / (MAX_RANK - MIN_RANK)
	}

	fun calculate(movie: Movie): Int {
		val pointsFromMin = movie.popularity - minPopularity
		return (pointsFromMin / popularityPerRank).toInt()
	}
}