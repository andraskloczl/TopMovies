package com.andraskloczl.movies.data.modules

import com.andraskloczl.movies.data.BuildConfig
import com.andraskloczl.movies.data.MovieApi
import com.andraskloczl.movies.data.RequestParamsProvider
import com.andraskloczl.movies.data.image.ImageUrlProvider
import com.andraskloczl.movies.data.image.ImageUrlProviderImpl
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Singleton
@Module
class NetworkModule {

	@Provides
	@Singleton
	fun provideRetrofit() =
		Retrofit.Builder()
			.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
			.addConverterFactory(GsonConverterFactory.create())
			.baseUrl(BuildConfig.SERVER_URL)
			.build()


	@Provides
	@Singleton
	fun provideImageUrlProvider(imageUrlProvider: ImageUrlProviderImpl): ImageUrlProvider = imageUrlProvider

	@Provides
	@Singleton
	fun provideGson(): Gson = Gson()

	@Provides
	@Singleton
	fun provideMovieAPI(retrofit: Retrofit): MovieApi = retrofit.create(MovieApi::class.java)

	@Provides
	@Singleton
	fun provideParamProvider(): RequestParamsProvider = object : RequestParamsProvider {
		override fun provideParams(): Single<MutableMap<String, String>> = Single.fromCallable {
			val paramsMap = mutableMapOf<String, String>()
			paramsMap.put("api_key", "b58ec89735cb99115f50c4035aa41c46")
			paramsMap.put("language", "en-us")
			paramsMap
		}
	}

}