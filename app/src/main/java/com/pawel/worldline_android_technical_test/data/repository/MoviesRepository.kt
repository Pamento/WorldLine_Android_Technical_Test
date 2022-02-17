package com.pawel.worldline_android_technical_test.data.repository

import com.pawel.worldline_android_technical_test.data.model.movie.Movie
import com.pawel.worldline_android_technical_test.data.model.movies.Result

interface MoviesRepository {
    suspend fun getMovies(): List<Result>?
    suspend fun getMovie(movieID: String): Movie
}