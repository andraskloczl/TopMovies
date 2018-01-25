package com.andraskloczl.movies.detail

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.andraskloczl.movies.domain.models.DisplayedMovie

class SimilarMoviesPagerAdapter(
	fragmentManager: FragmentManager,
	val movies: List<DisplayedMovie>
) : FragmentStatePagerAdapter(fragmentManager) {

	override fun getItem(position: Int): Fragment {
		val movie = movies.get(position)
		return MovieDetailsFragment.newInstance(movie)
	}

	override fun getCount() = movies.size

}
