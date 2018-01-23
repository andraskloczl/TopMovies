package com.andraskloczl.movies.data.toprated

import com.andraskloczl.movies.domain.models.DataPage
import com.andraskloczl.movies.domain.models.GetTopRatedMoviesRequest
import com.andraskloczl.movies.domain.models.DisplayedMovie
import com.andraskloczl.movies.domain.models.Movie
import com.andraskloczl.movies.domain.repository.TopRatedMovieRepository
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TopRatedMovieRepositoryImpl
@Inject constructor(
	private val remoteDataSource: TopRatedMovieDataSource,
	private val localDataSource: TopRatedMovieDataSource
) : TopRatedMovieRepository {

	override fun getTopRatedMovies(request: GetTopRatedMoviesRequest): Observable<DataPage<Movie>> {
		return remoteDataSource.getTopRatedMovies(request)
	}

}


