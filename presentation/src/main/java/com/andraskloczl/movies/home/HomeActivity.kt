package com.andraskloczl.movies.home

import android.os.Bundle
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.View
import android.widget.Toast
import com.andraskloczl.movies.AbstractActivity
import com.andraskloczl.movies.R
import com.andraskloczl.movies.detail.DetailActivity
import com.andraskloczl.movies.domain.models.DisplayedMovie
import com.andraskloczl.movies.home.list.MovieListAdapter
import com.andraskloczl.movies.home.list.ScrollState
import com.andraskloczl.movies.util.Extensions.onScrollListener
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.toolbar.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class HomeActivity : AbstractActivity(), HomeContract.View {

	override val layoutResourceId: Int
		get() = R.layout.activity_home

	companion object {
		const val SPAN_COUNT = 2
		const val ORIENTATION_VERTICAL = 1
		const val SCROLL_STATE_SAMPLE_INTERVAL_MILLIS = 500L
		const val PLACEHOLDER_VALUE = 0
	}

	@Inject
	lateinit var presenter: HomeContract.Presenter

	private lateinit var listAdapter: MovieListAdapter

	private lateinit var layoutManager: StaggeredGridLayoutManager
	private val scrollStatePublishSubject = PublishSubject.create<ScrollState>()
	private val rawScrollPublishSubject = PublishSubject.create<Int>()

	val movies = ArrayList<DisplayedMovie>()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		initUI()
	}

	private fun initUI() {
		setSupportActionBar(toolbar)
		toolbarTitle.text = toolbar.title
		supportActionBar?.setDisplayShowTitleEnabled(false)

		layoutManager = StaggeredGridLayoutManager(SPAN_COUNT, ORIENTATION_VERTICAL)
		recyclerView.layoutManager = layoutManager

		listAdapter = MovieListAdapter(this, movies, { clickedPost ->
			presenter.onMovieClicked(clickedPost)
		})
		recyclerView.adapter = listAdapter
		recyclerView.onScrollListener { rawScrollPublishSubject.onNext(PLACEHOLDER_VALUE) }
	}

	override fun listenForScroll(): Observable<ScrollState> {
		rawScrollPublishSubject
			.sample(SCROLL_STATE_SAMPLE_INTERVAL_MILLIS, TimeUnit.MILLISECONDS)
			.onErrorReturn { PLACEHOLDER_VALUE }
			.subscribe({
				val lastVisiblePositions = layoutManager.findLastVisibleItemPositions(IntArray(SPAN_COUNT))
				val scrollState = ScrollState(lastVisiblePositions.lastOrNull() ?: 0, movies.size)
				scrollStatePublishSubject.onNext(scrollState)
			})

		return scrollStatePublishSubject
	}

	override fun onResume() {
		super.onResume()
		presenter.subscribe()
	}

	override fun onPause() {
		super.onPause()
		presenter.unsubscribe()
	}

	override fun displayMovies(newMovies: List<DisplayedMovie>) {
		loadingView.visibility = View.GONE

		val beforeSize = movies.size
		this.movies.addAll(newMovies)
		listAdapter.notifyItemRangeInserted(beforeSize, newMovies.size)
	}

	override fun displayError(errorText: String) {
		loadingView.visibility = View.GONE
		val errorMessage = if (errorText.isNotEmpty()) errorText else getString(R.string.default_error_message)
		Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
	}

	override fun goToDetailsScreen(movieToShow: DisplayedMovie) {
		startActivity(DetailActivity.newIntent(this, movieToShow))
		overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left)
	}
}
