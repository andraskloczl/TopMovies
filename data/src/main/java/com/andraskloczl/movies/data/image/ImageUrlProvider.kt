package com.andraskloczl.movies.data.image

interface ImageUrlProvider {
	fun getFullUrl(imagePath: String): String
}