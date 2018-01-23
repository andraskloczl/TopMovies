package com.andraskloczl.movies.data.modules

import com.andraskloczl.movies.data.MockTopRatedMovieRemoteDataSource
import com.andraskloczl.movies.data.toprated.TopRatedMovieDataSource
import com.andraskloczl.movies.data.toprated.TopRatedMovieRepositoryImpl
import com.andraskloczl.movies.data.toprated.remote.TopRatedMovieRemoteDataSource
import com.andraskloczl.movies.domain.repository.TopRatedMovieRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Singleton
@Module
class MovieModule {

	@Provides
	@Singleton
	fun provideTopRatedMovieRemoteDataSource(mockRemoteSource: MockTopRatedMovieRemoteDataSource): TopRatedMovieDataSource = mockRemoteSource

	@Provides
	@Singleton
	fun provideTopRatedMovieRepository(topRatedMovieRepositoryImpl: TopRatedMovieRepositoryImpl) : TopRatedMovieRepository = topRatedMovieRepositoryImpl

}