package com.andraskloczl.movies.domain.usecases.getsimilarmovies

import android.support.test.filters.SmallTest
import com.andraskloczl.movies.domain.BaseUnitTest
import com.andraskloczl.movies.domain.TestDataGenerator
import com.andraskloczl.movies.domain.mapper.MovieMapper
import com.andraskloczl.movies.domain.models.GetSimilarMoviesRequest
import com.andraskloczl.movies.domain.repository.SimilarMoviesRepository
import com.andraskloczl.movies.domain.utils.PopularityRankCalculator
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.Spy

@SmallTest
class GetSimilarMoviesImplTest : BaseUnitTest() {

	@Mock
	lateinit var mockSimilarMoviesRepository: SimilarMoviesRepository
	@Spy
	var movieMapper = MovieMapper()
	@Spy
	var popularityRankCalculator = PopularityRankCalculator()

	lateinit var getSimilarMoviesImpl: GetSimilarMoviesImpl

	@Before
	fun setup() {
		MockitoAnnotations.initMocks(this)
		getSimilarMoviesImpl = GetSimilarMoviesImpl(mockSimilarMoviesRepository,
			movieMapper, popularityRankCalculator)
	}

	@Test
	fun `When repository returns proper values Then it returns the mapped objects`() {
		val pageIndex = 1
		val movieListSize = 3
		val requestDummy = GetSimilarMoviesRequest("", pageIndex)

		val testData = TestDataGenerator.getMoviesDataPage(pageIndex, movieListSize)

		Mockito.`when`(mockSimilarMoviesRepository.getSimilarMovies(any()))
			.thenReturn(Observable.just(testData))

		val testObserver = getSimilarMoviesImpl.execute(requestDummy).test()

		testObserver.awaitTerminalEvent()

		testObserver
			.assertNoErrors()
			.assertValue { it.pageIndex == pageIndex && it.pageItems.size == movieListSize }

		Mockito.verify(movieMapper, Mockito.times(movieListSize)).transform(any(), same(popularityRankCalculator))
		Mockito.verify(popularityRankCalculator).init(testData.pageItems)

	}

}