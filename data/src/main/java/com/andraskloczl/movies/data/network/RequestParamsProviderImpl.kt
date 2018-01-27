package com.andraskloczl.movies.data.network

import com.andraskloczl.movies.data.BuildConfig
import com.andraskloczl.movies.data.Keys
import com.andraskloczl.movies.data.language.LanguageCodeProvider
import javax.inject.Inject

class RequestParamsProviderImpl @Inject constructor(
	val languageCodeProvider: LanguageCodeProvider
) : RequestParamsProvider {

	override fun provideParams(): MutableMap<String, String> {
		val paramsMap = mutableMapOf<String, String>()
		paramsMap.put(Keys.Remote.API_KEY, BuildConfig.API_KEY)
		paramsMap.put(Keys.Remote.LANGUAGE, languageCodeProvider.provideLanguage())
		return paramsMap
	}
}