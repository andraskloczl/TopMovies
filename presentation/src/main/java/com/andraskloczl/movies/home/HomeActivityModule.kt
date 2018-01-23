package com.andraskloczl.movies.home

import com.andraskloczl.movies.PopularityRankCalculator
import com.andraskloczl.movies.domain.mapper.MovieMapper
import com.andraskloczl.movies.domain.repository.TopRatedMovieRepository
import com.andraskloczl.movies.domain.usecases.GetTopRatedMoviesImpl
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class HomeActivityModule {

	@Binds
	abstract fun view(homeActivity: HomeActivity): HomeContract.View

	@Module
	companion object {

		@JvmStatic
		@Provides
		internal fun providePresenter(view: HomeContract.View,
									  topRatedMovieRepository: TopRatedMovieRepository
		): HomeContract.Presenter {
			return HomePresenter(view, GetTopRatedMoviesImpl(topRatedMovieRepository, MovieMapper(),
				PopularityRankCalculator()))
		}
	}
}