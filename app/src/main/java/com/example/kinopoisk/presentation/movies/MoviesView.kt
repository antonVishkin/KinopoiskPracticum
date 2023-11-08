package com.example.kinopoisk.presentation.movies

import com.example.kinopoisk.domain.models.Movie

interface MoviesView {
    fun showPlaceholderMessage(isVisible: Boolean)

    fun showMoviesList(isVisible: Boolean)

    fun showProgressBar(isVisible: Boolean)

    fun changePlaceholderText(newPlaceholderText: String)

    fun updateMoviesList(newMoviesList: List<Movie>)

    fun showToast(text:String)

}