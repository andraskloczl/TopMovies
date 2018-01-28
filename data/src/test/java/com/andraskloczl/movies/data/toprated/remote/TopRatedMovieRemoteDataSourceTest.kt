package com.andraskloczl.movies.data.toprated.remote

import android.support.test.filters.SmallTest
import com.andraskloczl.movies.data.BaseUnitTest
import com.andraskloczl.movies.data.Keys
import com.andraskloczl.movies.data.TestDataGenerator
import com.andraskloczl.movies.data.mapper.MovieDataMapper
import com.andraskloczl.movies.data.models.PaginatedMoviesResponse
import com.andraskloczl.movies.data.network.MovieApi
import com.andraskloczl.movies.data.network.RequestParamsProvider
import com.andraskloczl.movies.domain.models.GetTopRatedMoviesRequest
import io.reactivex.Single
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

@SmallTest
class TopRatedMovieRemoteDataSourceTest : BaseUnitTest() {

	@Mock
	private lateinit var movieApi: MovieApi
	@Mock
	private lateinit var requestParamsProvider: RequestParamsProvider
	@Mock
	private lateinit var dataMapper: MovieDataMapper

	private lateinit var topRatedMovieRemoteDataSource: TopRatedMovieRemoteDataSource

	@Before
	fun setup() {
		MockitoAnnotations.initMocks(this)
		RxJavaPlugins.setIoSchedulerHandler { schedulerCallable -> Schedulers.trampoline() }
		topRatedMovieRemoteDataSource =
			TopRatedMovieRemoteDataSource(movieApi, requestParamsProvider, dataMapper)
	}

	@Test
	fun `When everything works fine Then it should return transformed movie objects`() {
		val testParams = mutableMapOf(("key1" to "value1"), ("key2" to "value2"))
		`when`(requestParamsProvider.provideParams()).thenReturn(testParams)

		val testPage = 1

		val testApiResponse = PaginatedMoviesResponse(testPage, 100, 10,
			com.andraskloczl.movies.data.TestDataGenerator.getMovieApiModelsList(10)
		)
		`when`(movieApi.getTopRatedMovies(any())).thenReturn(Single.just(testApiResponse))

		val testMappedObject = TestDataGenerator.getMoviesDataPage(testPage, 10)
		`when`(dataMapper.transform(any())).thenReturn(testMappedObject)

		val request = GetTopRatedMoviesRequest(testPage)
		val testObserver = topRatedMovieRemoteDataSource.getTopRatedMovies(request).test()

		testObserver.awaitTerminalEvent()

		testObserver
			.assertNoErrors()
			.assertValue(testMappedObject)

		assertEquals(testPage, testParams[Keys.Remote.PAGE]?.toInt())

		verify(movieApi, times(1)).getTopRatedMovies(same(testParams))
		verify(movieApi, never()).getSimilarMovies(any(), any())

		verify(requestParamsProvider, times(1)).provideParams()

		verify(dataMapper, times(1)).transform(same(testApiResponse))

	}
}