package com.example.kinopoisk.util

import android.app.Activity
import android.content.Context
import com.example.kinopoisk.data.MoviesRepositoryImpl
import com.example.kinopoisk.data.network.RetrofitNetworkClient
import com.example.kinopoisk.domain.api.MoviesInteractor
import com.example.kinopoisk.domain.api.MoviesRepository
import com.example.kinopoisk.domain.impl.MoviesInteractorImpl
import com.example.kinopoisk.presentation.MoviesSearchController
import com.example.kinopoisk.presentation.PosterController
import com.example.kinopoisk.ui.movies.MoviesAdapter

object Creator {
    private fun getMoviesRepository(context: Context): MoviesRepository {
        return MoviesRepositoryImpl(RetrofitNetworkClient(context))
    }

    fun provideMoviesInteractor(context: Context): MoviesInteractor {
        return MoviesInteractorImpl(getMoviesRepository(context))
    }

    fun provideMoviesSearchController(activity: Activity, adapter: MoviesAdapter): MoviesSearchController {
        return MoviesSearchController(activity, adapter)
    }

    fun providePosterController(activity: Activity): PosterController {
        return PosterController(activity)
    }
}
