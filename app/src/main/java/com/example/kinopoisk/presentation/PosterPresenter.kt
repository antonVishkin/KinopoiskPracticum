package com.example.kinopoisk.presentation

import com.example.kinopoisk.presentation.poster.PosterView

class PosterPresenter(private val view: PosterView, private val imageUrl: String) {


    fun onCreate() {
        view.setupPosterImage(imageUrl)
    }
}