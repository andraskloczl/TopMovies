package com.andraskloczl.movies.data.toprated

import com.andraskloczl.movies.domain.models.DataPage
import com.andraskloczl.movies.domain.models.DisplayedMovie
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TopRatedMoviesResponseDataMapper @Inject constructor() {

	fun transform(response: GetTopRatedMoviesResponse): DataPage<DisplayedMovie> =
		DataPage(response.page, response.results.map {
			DisplayedMovie(it.id, it.title, it.posterPath, it.voteAverage)
		})

}