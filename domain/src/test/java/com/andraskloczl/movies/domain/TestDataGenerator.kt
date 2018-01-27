package com.andraskloczl.movies.domain

import com.andraskloczl.movies.domain.models.DataPage
import com.andraskloczl.movies.domain.models.Movie

object TestDataGenerator {

	fun getMoviesDataPage(page: Int, size: Int): DataPage<Movie> {

		val movies = mutableListOf<Movie>()

		repeat(size, { index ->
			movies.add(Movie(index, "", "", "",
				"", 1f, 1f))
		})

		return DataPage(page, movies)
	}

	fun getMovie() = Movie(0, "", "", "",
		"", 0f, 0f)

	fun getMovie(popularity: Number) = Movie(0, "", "", "",
		"", 0f, popularity.toFloat())
}