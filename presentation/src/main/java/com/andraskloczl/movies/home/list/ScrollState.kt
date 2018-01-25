package com.andraskloczl.movies.home.list

data class ScrollState(
	val lastVisibleIndex: Int,
	val totalItemsCount: Int
)