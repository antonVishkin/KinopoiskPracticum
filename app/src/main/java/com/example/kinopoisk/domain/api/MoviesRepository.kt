package com.example.kinopoisk.domain.api

import com.example.kinopoisk.domain.models.Movie
import com.example.kinopoisk.util.Resource

interface MoviesRepository {
    fun searchMovies(expression: String): Resource<List<Movie>>
}