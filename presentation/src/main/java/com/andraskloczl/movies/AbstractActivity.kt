package com.andraskloczl.movies

import android.app.Activity
import android.os.Bundle
import butterknife.ButterKnife
import dagger.android.support.DaggerAppCompatActivity

abstract class AbstractActivity : DaggerAppCompatActivity() {

	protected abstract val layoutResourceId: Int

	val Activity.app: App get() = application as App

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(layoutResourceId)
		ButterKnife.bind(this)
	}
}
