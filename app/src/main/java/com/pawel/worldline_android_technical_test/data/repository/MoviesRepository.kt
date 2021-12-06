package com.pawel.worldline_android_technical_test.data.repository

import com.pawel.worldline_android_technical_test.data.api.ApiHelper

class MoviesRepository(private val apiHelper: ApiHelper) {
    suspend fun getMovies() = apiHelper.getMovies()
    suspend fun getMovie(movieID: String) = apiHelper.getMovie(movieID)
}