package com.andraskloczl.movies.data.toprated

import com.andraskloczl.movies.data.R
import com.andraskloczl.movies.data.RawFileReader
import com.andraskloczl.movies.data.mapper.MovieDataMapper
import com.andraskloczl.movies.data.models.PaginatedMoviesResponse
import com.andraskloczl.movies.domain.models.DataPage
import com.andraskloczl.movies.domain.models.GetTopRatedMoviesRequest
import com.andraskloczl.movies.domain.models.Movie
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class FakeTopRatedMovieRemoteDataSource @Inject constructor(
	val rawFileReader: RawFileReader,
	val dataMapper: MovieDataMapper
) : TopRatedMovieDataSource {

	override fun getTopRatedMovies(request: GetTopRatedMoviesRequest): Observable<DataPage<Movie>> =
		rawFileReader.readFile(R.raw.topratedmovies, PaginatedMoviesResponse::class.java)
			.toObservable()
			.map { dataMapper.transform(it) }
			.subscribeOn(Schedulers.io())


}
