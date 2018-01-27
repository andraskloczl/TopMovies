package com.andraskloczl.movies.detail

import com.andraskloczl.movies.domain.mapper.MovieMapper
import com.andraskloczl.movies.domain.repository.SimilarMoviesRepository
import com.andraskloczl.movies.domain.usecases.getsimilarmovies.GetSimilarMoviesImpl
import com.andraskloczl.movies.domain.utils.PopularityRankCalculator
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class DetailActivityModule {

	@Binds
	abstract fun view(detailActivity: DetailActivity): DetailContract.View

	@Module
	companion object {

		@JvmStatic
		@Provides
		internal fun providePresenter(view: DetailContract.View,
									  similarMoviesRepository: SimilarMoviesRepository
		): DetailContract.Presenter {
			return DetailPresenter(view, GetSimilarMoviesImpl(similarMoviesRepository, MovieMapper(),
				PopularityRankCalculator()))
		}
	}
}