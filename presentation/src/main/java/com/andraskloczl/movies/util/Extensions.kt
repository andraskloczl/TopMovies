package com.andraskloczl.movies.util

import android.support.v7.widget.RecyclerView

object Extensions {

	fun RecyclerView.onScrollListener(callback: () -> Unit) {
		val listener = object : RecyclerView.OnScrollListener() {
			lateinit var callback: () -> Unit

			override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
				super.onScrollStateChanged(recyclerView, newState)
				callback.invoke()
			}
		}
		listener.callback = callback
		addOnScrollListener(listener)
	}
}