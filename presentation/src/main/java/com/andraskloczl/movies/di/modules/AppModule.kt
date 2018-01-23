package com.andraskloczl.movies.di.modules

import android.app.Application
import android.content.Context
import com.andraskloczl.movies.App
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Singleton
@Module
abstract class AppModule(val app: App) {

	@Binds
	internal abstract fun bindContext(application: Application): Context
}