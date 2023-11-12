package com.example.kinopoisk.ui.poster

import android.app.Activity
import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.kinopoisk.util.Creator
import com.example.kinopoisk.R
import com.example.kinopoisk.presentation.PosterPresenter
import com.example.kinopoisk.presentation.poster.PosterView

class PosterActivity : Activity(),PosterView {

    private lateinit var posterPresenter:PosterPresenter
    private lateinit var poster: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val imageUrl = intent.extras?.getString("poster", "") ?: ""
        posterPresenter = Creator.providePosterPresenter(this, imageUrl)
        setContentView(R.layout.activity_poster)
        poster = findViewById(R.id.poster)
        posterPresenter.onCreate()
    }

    override fun setupPosterImage(url: String) {
        Glide.with(applicationContext)
            .load(url)
            .into(poster)
    }
}