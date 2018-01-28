package com.andraskloczl.movies.domain.usecases.gettopratedmovies

import android.support.test.filters.SmallTest
import com.andraskloczl.movies.common.BaseUnitTest
import com.andraskloczl.movies.domain.TestDomainModelGenerator
import com.andraskloczl.movies.domain.mapper.MovieMapper
import com.andraskloczl.movies.domain.models.GetTopRatedMoviesRequest
import com.andraskloczl.movies.domain.repository.TopRatedMovieRepository
import com.andraskloczl.movies.domain.utils.PopularityRankCalculator
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.Spy

@SmallTest
class GetTopRatedMoviesImplTest : BaseUnitTest() {

	@Mock
	private
	lateinit var mockTopRatedMovieRepository: TopRatedMovieRepository
	@Spy
	private var movieMapper = MovieMapper()
	@Spy
	private var popularityRankCalculator = PopularityRankCalculator()

	private lateinit var getTopRatedMoviesImpl: GetTopRatedMoviesImpl

	@Before
	fun setup() {
		MockitoAnnotations.initMocks(this)
		getTopRatedMoviesImpl = GetTopRatedMoviesImpl(mockTopRatedMovieRepository,
			movieMapper, popularityRankCalculator)
	}

	@Test
	fun `When repository returns proper values Then it returns the mapped objects`() {
		val pageIndex = 1
		val movieListSize = 3
		val requestDummy = GetTopRatedMoviesRequest(pageIndex)

		val testData = TestDomainModelGenerator.getMoviesDataPage(pageIndex, movieListSize)

		Mockito.`when`(mockTopRatedMovieRepository.getTopRatedMovies(any()))
			.thenReturn(Observable.just(testData))

		val testObserver = getTopRatedMoviesImpl.execute(requestDummy).test()

		testObserver.awaitTerminalEvent()

		testObserver
			.assertNoErrors()
			.assertValue { it.pageIndex == pageIndex && it.pageItems.size == movieListSize }

		Mockito.verify(movieMapper, Mockito.times(movieListSize)).transform(any(), same(popularityRankCalculator))
		Mockito.verify(popularityRankCalculator).init(testData.pageItems)
	}

}