package com.andraskloczl.movies.data.image

import com.andraskloczl.movies.data.BuildConfig
import javax.inject.Inject

class ImageUrlProviderImpl @Inject constructor() : ImageUrlProvider {
	override fun getFullUrl(imagePath: String): String {
		return BuildConfig.IMAGE_BASE_URL + "w500" + imagePath
	}
}
