package com.andraskloczl.movies.domain

import org.mockito.Mockito

open class BaseUnitTest {

	protected fun <T> any(): T {
		Mockito.any<T>()
		return uninitialized()
	}

	protected fun <T> same(value: T): T {
		Mockito.same<T>(value)
		return uninitialized()
	}

	private fun <T> uninitialized(): T = null as T
}