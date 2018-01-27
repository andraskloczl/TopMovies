package com.andraskloczl.movies.data.network

interface RequestParamsProvider {
	fun provideParams(): MutableMap<String, String>
}