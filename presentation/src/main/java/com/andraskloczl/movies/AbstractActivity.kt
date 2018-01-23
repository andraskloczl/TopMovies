package com.andraskloczl.movies

import android.app.Activity
import android.os.Bundle
import android.util.Log
import butterknife.ButterKnife
import dagger.android.support.DaggerAppCompatActivity

abstract class AbstractActivity : DaggerAppCompatActivity() {

	protected abstract val layoutResourceId: Int

	val Activity.app: App get() = application as App

	protected val TAG = this.javaClass.simpleName

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		Log.d(TAG, "onCreate")
		setContentView(layoutResourceId)
		ButterKnife.bind(this)
	}

//	override fun onStart() {
//		super.onStart()
//		EventBus.getDefault().register(this)
//	}
//
//	override fun onStop() {
//		super.onStop()
//		EventBus.getDefault().unregister(this)
//	}
//
//	@Subscribe
//	fun onNoConnection(noConnectionEvent: NoConnectionEvent) {
//		Toast.makeText(this, getString(R.string.no_internet_connection), Toast.LENGTH_LONG).show()
//	}
}
