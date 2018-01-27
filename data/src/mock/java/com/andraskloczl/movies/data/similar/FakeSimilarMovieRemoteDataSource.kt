package com.andraskloczl.movies.data.similar

import com.andraskloczl.movies.data.R
import com.andraskloczl.movies.data.RawFileReader
import com.andraskloczl.movies.data.mapper.MovieDataMapper
import com.andraskloczl.movies.data.models.PaginatedMoviesResponse
import com.andraskloczl.movies.domain.models.DataPage
import com.andraskloczl.movies.domain.models.GetSimilarMoviesRequest
import com.andraskloczl.movies.domain.models.Movie
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class FakeSimilarMovieRemoteDataSource @Inject constructor(
	val rawFileReader: RawFileReader,
	val dataMapper: MovieDataMapper
) : SimilarMoviesDataSource {

	override fun getSimilarMovies(request: GetSimilarMoviesRequest): Observable<DataPage<Movie>> =
		rawFileReader.readFile(R.raw.similarmovies, PaginatedMoviesResponse::class.java)
			.toObservable()
			.map { dataMapper.transform(it) }
			.subscribeOn(Schedulers.io())

}
