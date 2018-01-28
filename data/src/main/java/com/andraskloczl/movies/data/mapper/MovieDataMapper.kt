package com.andraskloczl.movies.data.mapper

import com.andraskloczl.movies.data.image.ImageUrlProvider
import com.andraskloczl.movies.data.models.PaginatedMoviesResponse
import com.andraskloczl.movies.domain.models.DataPage
import com.andraskloczl.movies.domain.models.Movie
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieDataMapper @Inject constructor(
	private val imageUrlProvider: ImageUrlProvider
) {

	fun transform(response: PaginatedMoviesResponse): DataPage<Movie> =
		DataPage(response.page, response.results.map {
			Movie(
				it.id,
				it.title,
				it.overview,
				imageUrlProvider.getFullUrl(it.posterPath),
				imageUrlProvider.getFullUrl(it.backdropPath),
				it.voteAverage,
				it.popularity
			)
		})

}