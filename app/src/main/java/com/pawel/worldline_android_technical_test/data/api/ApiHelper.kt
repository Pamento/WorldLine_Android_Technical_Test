package com.pawel.worldline_android_technical_test.data.api

import com.pawel.worldline_android_technical_test.data.model.movie.Movie
import com.pawel.worldline_android_technical_test.data.model.movies.MovieApiResponse

interface ApiHelper {
    suspend fun getMovies(): MovieApiResponse
    suspend fun getMovie(movieID: String): Movie
}