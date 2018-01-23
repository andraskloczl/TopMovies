package com.andraskloczl.movies.data.toprated

import com.andraskloczl.movies.data.image.ImageUrlProvider
import com.andraskloczl.movies.domain.models.DataPage
import com.andraskloczl.movies.domain.models.Movie
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TopRatedMoviesResponseDataMapper @Inject constructor(
	val imageUrlProvider: ImageUrlProvider
) {

	fun transform(response: GetTopRatedMoviesResponse): DataPage<Movie> =
		DataPage(response.page, response.results.map {
			Movie(it.id, it.title, imageUrlProvider.getFullUrl(it.posterPath),
				imageUrlProvider.getFullUrl(it.backdropPath), it.voteAverage, it.popularity)
		})

}