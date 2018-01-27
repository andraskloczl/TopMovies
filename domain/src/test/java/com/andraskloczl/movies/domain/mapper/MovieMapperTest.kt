package com.andraskloczl.movies.domain.mapper

import android.support.test.filters.SmallTest
import com.andraskloczl.movies.domain.BaseUnitTest
import com.andraskloczl.movies.domain.TestDataGenerator.getMovie
import com.andraskloczl.movies.domain.models.Movie
import com.andraskloczl.movies.domain.utils.PopularityRankCalculator
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

@SmallTest
class MovieMapperTest : BaseUnitTest() {

	@Mock
	lateinit var popularityRankCalculator: PopularityRankCalculator

	val movieMapper = MovieMapper()

	@Before
	fun setup() {
		MockitoAnnotations.initMocks(this)
	}

	@Test
	fun `When transforming Then should ask the calculator for the calculated value`() {
		val testMovie = getMovie()
		val testPopularityRank = 5

		`when`(popularityRankCalculator.calculate(any())).thenReturn(testPopularityRank)

		val mappedObject = movieMapper.transform(testMovie, popularityRankCalculator)

		verify(popularityRankCalculator, times(1)).calculate(same(testMovie))
		assertEquals(testPopularityRank, mappedObject.popularityRank)
	}

	@Test
	fun `When transforming Then the values should remain the same`() {
		val testId = 100
		val testTitle = "title"
		val testOverview = "overview"
		val testPosterImageUrl = "poster"
		val testBackdropImageUrl = "backdrop"
		val testVoteAverage = 7.5f

		val testMovie = Movie(testId, testTitle, testOverview, testPosterImageUrl, testBackdropImageUrl,
			testVoteAverage, 50f)

		`when`(popularityRankCalculator.calculate(any())).thenReturn(5)

		val mappedObject = movieMapper.transform(testMovie, popularityRankCalculator)

		assertEquals(testId, mappedObject.id)
		assertEquals(testTitle, mappedObject.title)
		assertEquals(testOverview, mappedObject.overview)
		assertEquals(testPosterImageUrl, mappedObject.posterImageUrl)
		assertEquals(testBackdropImageUrl, mappedObject.backdropImageUrl)
		assertEquals(testVoteAverage, mappedObject.voteAverage)
	}
}