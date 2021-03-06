package com.andraskloczl.movies.domain.mapper

import com.andraskloczl.movies.domain.models.DisplayedMovie
import com.andraskloczl.movies.domain.models.Movie
import com.andraskloczl.movies.domain.utils.PopularityRankCalculator

class MovieMapper {

	fun transform(movie: Movie, popularityRankCalculator: PopularityRankCalculator): DisplayedMovie =
		DisplayedMovie(movie.id,
			movie.title,
			movie.overview,
			movie.posterImageUrl,
			movie.backdropImageUrl,
			movie.voteAverage,
			popularityRankCalculator.calculate(movie))

}