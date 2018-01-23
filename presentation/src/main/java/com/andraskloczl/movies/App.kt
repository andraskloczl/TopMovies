package com.andraskloczl.movies

import com.andraskloczl.movies.di.AppComponent
import com.andraskloczl.movies.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class App : DaggerApplication() {

	override fun onCreate() {
		super.onCreate()
		instance = this
	}

	companion object {
		lateinit var instance: App
			private set
		lateinit var appComponent: AppComponent
			private set
	}

	override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
		appComponent = DaggerAppComponent.builder().application(this).build()
		return appComponent
	}

}