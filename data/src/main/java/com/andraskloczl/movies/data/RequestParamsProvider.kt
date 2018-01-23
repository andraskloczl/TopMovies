package com.andraskloczl.movies.data

import io.reactivex.Single

interface RequestParamsProvider {
	fun provideParams() : Single<MutableMap<String, String>>
}