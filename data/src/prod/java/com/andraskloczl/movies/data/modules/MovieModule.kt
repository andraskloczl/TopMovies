package com.andraskloczl.movies.data.modules

import com.andraskloczl.movies.data.similar.SimilarMoviesDataSource
import com.andraskloczl.movies.data.similar.SimilarMoviesRepositoryImpl
import com.andraskloczl.movies.data.similar.remote.SimilarMoviesRemoteDataSource
import com.andraskloczl.movies.data.toprated.TopRatedMovieDataSource
import com.andraskloczl.movies.data.toprated.TopRatedMovieRepositoryImpl
import com.andraskloczl.movies.data.toprated.remote.TopRatedMovieRemoteDataSource
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
	fun provideTopRatedMovieRemoteDataSource(remoteSource: TopRatedMovieRemoteDataSource): TopRatedMovieDataSource = remoteSource

	@Provides
	@Singleton
	fun provideTopRatedMovieRepository(repository: TopRatedMovieRepositoryImpl): TopRatedMovieRepository = repository

	@Provides
	@Singleton
	fun provideSimilarMoviesRemoteDataSource(remoteSource: SimilarMoviesRemoteDataSource): SimilarMoviesDataSource = remoteSource

	@Provides
	@Singleton
	fun provideSimilarMoviesRepository(repository: SimilarMoviesRepositoryImpl): SimilarMoviesRepository = repository

}