package com.example.kinopoisk.presentation.movies

import android.app.Activity
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kinopoisk.util.Creator
import com.example.kinopoisk.R
import com.example.kinopoisk.domain.api.MoviesInteractor
import com.example.kinopoisk.domain.models.Movie
import com.example.kinopoisk.ui.movies.MoviesAdapter

class MoviesSearchPresenter(private val view: MoviesView,
                            private val context: Context
) {

    private var lastSearchText: String? = null
    private val searchRunnable = Runnable {
        val newSearchText = lastSearchText ?: ""
        searchRequest(newSearchText)
    }
    private val moviesInteractor = Creator.provideMoviesInteractor(context)

    companion object {
        private const val SEARCH_DEBOUNCE_DELAY = 2000L
    }


    fun onDestroy() {
        handler.removeCallbacks(searchRunnable)
    }



    private val movies = ArrayList<Movie>()

    private val handler = Handler(Looper.getMainLooper())


    fun searchDebounce(changedText:String) {
        this.lastSearchText = changedText
        handler.removeCallbacks(searchRunnable)
        handler.postDelayed(searchRunnable, SEARCH_DEBOUNCE_DELAY)
    }

    private fun searchRequest(newSearchText: String) {
        if (newSearchText.isNotEmpty()) {

            view.showPlaceholderMessage(false)
            view.showMoviesList(false)
            view.showProgressBar(true)

            moviesInteractor.searchMovies(newSearchText, object : MoviesInteractor.MoviesConsumer {
                override fun consume(foundMovies: List<Movie>?, errorMessage: String?) {
                    handler.post {
                        view.showProgressBar(false)
                        if (foundMovies != null) {
                            movies.clear()
                            movies.addAll(foundMovies)
                            view.updateMoviesList(movies)
                            view.showMoviesList(true)
                        }
                        if (errorMessage != null) {
                            showMessage(context.getString(R.string.something_went_wrong), errorMessage)
                        } else if (movies.isEmpty()) {
                            showMessage(context.getString(R.string.nothing_found), "")
                        } else {
                            hideMessage()
                        }
                    }
                }
            })
        }
    }

    private fun showMessage(text: String, additionalMessage: String) {
        if (text.isNotEmpty()) {
            view.showPlaceholderMessage(true)
            movies.clear()
            view.updateMoviesList(movies)
            view.changePlaceholderText(text)
            if (additionalMessage.isNotEmpty()) {
                view.showToast(additionalMessage)
            }
        } else {
            view.showPlaceholderMessage(false)
        }
    }

    private fun hideMessage() {
        view.showPlaceholderMessage(false)
    }
}