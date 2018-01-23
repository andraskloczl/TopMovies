package com.andraskloczl.movies.di.modules

import dagger.Module
import javax.inject.Singleton

@Singleton
@Module
class NetworkModule {

//	@Provides
//	@Singleton
//	fun provideRetrofit() =
//		Retrofit.Builder()
//			.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//			.addConverterFactory(GsonConverterFactory.create())
//			.baseUrl(BuildConfig.SERVER_URL)
//			.build()
//
//
//	@Provides
//	@Singleton
//	fun provideMovieAPI(retrofit: Retrofit): CommentAPI = retrofit.create(CommentAPI::class.java)
}