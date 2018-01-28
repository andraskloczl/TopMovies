package com.andraskloczl.movies.domain.utils

import android.support.test.filters.SmallTest
import com.andraskloczl.movies.domain.TestDataGenerator.getMovie
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@SmallTest
class PopularityRankCalculatorTest {


	private lateinit var calculator: PopularityRankCalculator

	@Before
	fun setup() {
		calculator = PopularityRankCalculator()
	}

	@Test
	fun `When initialized with normal values Then it should calculate proper values`() {
		val movies = listOf(
			getMovie(10),
			getMovie(20),
			getMovie(30),
			getMovie(40),
			getMovie(50)
		)
		calculator.init(movies)

		assertEquals(PopularityRankCalculator.MIN_RANK, calculator.calculate(getMovie(10)))
		assertEquals(PopularityRankCalculator.MIN_RANK, calculator.calculate(getMovie(0)))
		assertEquals(PopularityRankCalculator.MAX_RANK, calculator.calculate(getMovie(50)))
		assertEquals(PopularityRankCalculator.MAX_RANK, calculator.calculate(getMovie(100)))

		assertEquals(1, calculator.calculate(getMovie(14)))
		assertEquals(2, calculator.calculate(getMovie(18)))
		assertEquals(3, calculator.calculate(getMovie(22)))
		assertEquals(4, calculator.calculate(getMovie(26)))
		assertEquals(5, calculator.calculate(getMovie(30)))
		assertEquals(6, calculator.calculate(getMovie(34)))
		assertEquals(7, calculator.calculate(getMovie(38)))
		assertEquals(8, calculator.calculate(getMovie(42)))
		assertEquals(9, calculator.calculate(getMovie(46)))
	}

}