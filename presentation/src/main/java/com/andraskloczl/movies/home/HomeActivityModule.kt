package com.andraskloczl.movies.home

import com.andraskloczl.movies.domain.usecases.GetTopRatedMovies
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
									  getTopRatedMovies: GetTopRatedMovies
		): HomeContract.Presenter {
			return HomePresenter(view, getTopRatedMovies)
		}
	}
}