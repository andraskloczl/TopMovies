package com.andraskloczl.movies.domain.models

class DataPage<T>(
	val pageIndex: Int,
	val pageItems: List<T>
)