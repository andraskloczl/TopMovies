package com.andraskloczl.movies.di

import android.app.Application
import com.andraskloczl.movies.data.modules.MovieModule
import com.andraskloczl.movies.data.modules.NetworkModule
import com.andraskloczl.movies.di.modules.ActivityBindingsModule
import com.andraskloczl.movies.di.modules.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.android.support.DaggerApplication
import javax.inject.Singleton


@Singleton
@Component(modules = [
	AndroidSupportInjectionModule::class,
	AppModule::class,
	ActivityBindingsModule::class,
	NetworkModule::class,
	MovieModule::class
])
interface AppComponent : AndroidInjector<DaggerApplication> {

	override fun inject(instance: DaggerApplication)

	@Component.Builder
	interface Builder {

		@BindsInstance
		fun application(application: Application): AppComponent.Builder

		fun build(): AppComponent
	}
}