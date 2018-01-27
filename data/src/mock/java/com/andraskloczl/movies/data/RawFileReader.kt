package com.andraskloczl.movies.data

import android.content.Context
import android.support.annotation.RawRes
import com.google.gson.Gson
import io.reactivex.Single
import javax.inject.Inject

class RawFileReader @Inject constructor(
	val context: Context,
	val gson: Gson
) {

	fun <T> readFile(@RawRes fileRes: Int, classOfT: Class<T>): Single<T> = Single.fromCallable {
		val inputStream = context.resources.openRawResource(fileRes)
		val jsonString = inputStream.bufferedReader().use { it.readText() }
		gson.fromJson(jsonString, classOfT)
	}
}
