package com.andraskloczl.movies.data.language

import android.content.Context
import android.os.Build
import java.util.*
import javax.inject.Inject

class LanguageCodeProviderImpl @Inject constructor(
	private val context: Context
) : LanguageCodeProvider {

	override fun provideLanguage(): String = getLocale().language

	private fun getLocale(): Locale {
		return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
			context.resources.configuration.locales.get(0)
		} else {
			Locale.getDefault()
		}
	}

}