package com.andraskloczl.movies.domain.models

class DataPage<out T>(
	val pageIndex: Int,
	val pageItems: List<T>
)