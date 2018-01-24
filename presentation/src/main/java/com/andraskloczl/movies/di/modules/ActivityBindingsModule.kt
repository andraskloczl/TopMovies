package com.andraskloczl.movies.di.modules

import com.andraskloczl.movies.detail.DetailActivity
import com.andraskloczl.movies.detail.DetailActivityModule
import com.andraskloczl.movies.di.scopes.PerActivity
import com.andraskloczl.movies.home.HomeActivity
import com.andraskloczl.movies.home.HomeActivityModule
import dagger.Module
import dagger.android.AndroidInjectionModule
import dagger.android.ContributesAndroidInjector

@Module(includes = [AndroidInjectionModule::class])
abstract class ActivityBindingsModule {

	@PerActivity
	@ContributesAndroidInjector(modules = [HomeActivityModule::class])
	internal abstract fun homeActivityInjector(): HomeActivity


	@PerActivity
	@ContributesAndroidInjector(modules = [DetailActivityModule::class])
	internal abstract fun detailActivityInjector(): DetailActivity

}