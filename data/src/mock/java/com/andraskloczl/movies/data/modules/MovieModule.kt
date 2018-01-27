package com.andraskloczl.movies.data.modules

import com.andraskloczl.movies.data.similar.FakeSimilarMovieRemoteDataSource
import com.andraskloczl.movies.data.toprated.FakeTopRatedMovieRemoteDataSource
import com.andraskloczl.movies.data.similar.SimilarMoviesDataSource
import com.andraskloczl.movies.data.similar.SimilarMoviesRepositoryImpl
import com.andraskloczl.movies.data.similar.remote.SimilarMoviesRemoteDataSource
import com.andraskloczl.movies.data.toprated.TopRatedMovieDataSource
import com.andraskloczl.movies.data.toprated.TopRatedMovieRepositoryImpl
import com.andraskloczl.movies.domain.repository.SimilarMoviesRepository
import com.andraskloczl.movies.domain.repository.TopRatedMovieRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Singleton
@Module
class MovieModule {

	@Provides
	@Singleton
	fun provideTopRatedMovieRemoteDataSource(fakeRemoteSource: FakeTopRatedMovieRemoteDataSource): TopRatedMovieDataSource = fakeRemoteSource

	@Provides
	@Singleton
	fun provideTopRatedMovieRepository(topRatedMovieRepositoryImpl: TopRatedMovieRepositoryImpl): TopRatedMovieRepository = topRatedMovieRepositoryImpl

	@Provides
	@Singleton
	fun provideSimilarMoviesRemoteDataSource(fakeRemoteSource: FakeSimilarMovieRemoteDataSource): SimilarMoviesDataSource = fakeRemoteSource

	@Provides
	@Singleton
	fun provideSimilarMoviesRepository(repository: SimilarMoviesRepositoryImpl): SimilarMoviesRepository = repository

}