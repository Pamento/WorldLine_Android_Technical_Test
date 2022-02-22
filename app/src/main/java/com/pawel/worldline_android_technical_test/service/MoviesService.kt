package com.pawel.worldline_android_technical_test.service

import com.pawel.data.model.movie.Movie
import com.pawel.data.model.movies.Result

interface MoviesService {
    suspend fun getMovies(): List<Result>?
    suspend fun getMovie(movieID: String): Movie
}