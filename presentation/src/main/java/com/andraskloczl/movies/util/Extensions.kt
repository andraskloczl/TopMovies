package com.andraskloczl.movies.util

import android.support.v4.view.ViewPager
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

	fun ViewPager.onPageChangeListener(callback: (position: Int) -> Unit) {
		val listener = object : ViewPager.OnPageChangeListener {
			lateinit var callback: (position: Int) -> Unit

			override fun onPageSelected(position: Int) {
				callback.invoke(position)
			}

			override fun onPageScrollStateChanged(state: Int) {
			}

			override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
			}
		}
		listener.callback = callback
		addOnPageChangeListener(listener)
	}
}