package com.example.kinopoisk.data

import com.example.kinopoisk.data.dto.Response

interface NetworkClient {
    fun doRequest(dto: Any): Response

}