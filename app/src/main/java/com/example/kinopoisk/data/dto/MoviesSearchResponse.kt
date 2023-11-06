package com.example.kinopoisk.data.dto

import com.example.kinopoisk.domain.models.Movie

class MoviesSearchResponse(val searchType: String,
                           val expression: String,
                           val results: List<MovieDto>) : Response()