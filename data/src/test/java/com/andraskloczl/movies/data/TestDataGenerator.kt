package com.andraskloczl.movies.data

import com.andraskloczl.movies.data.models.MovieAPIModel
import com.andraskloczl.movies.domain.models.DataPage
import com.andraskloczl.movies.domain.models.Movie

object TestDataGenerator {

	fun getMovieApiModelsList(size: Int): List<MovieAPIModel> {
		val movies = mutableListOf<MovieAPIModel>()

		repeat(size, { index ->
			movies.add(MovieAPIModel(index, "", "", 5f, 20f,
				"", ""))
		})

		return movies
	}

	fun getMoviesDataPage(page: Int, size: Int): DataPage<Movie> {

		val movies = mutableListOf<Movie>()

		repeat(size, { index ->
			movies.add(Movie(index, "", "", "",
				"", 1f, 1f))
		})

		return DataPage(page, movies)
	}
}