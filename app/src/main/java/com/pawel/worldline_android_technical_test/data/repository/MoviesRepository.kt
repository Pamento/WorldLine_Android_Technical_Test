package com.pawel.worldline_android_technical_test.data.repository

import com.pawel.worldline_android_technical_test.data.model.movie.Movie
import com.pawel.worldline_android_technical_test.data.model.movies.MovieApiResponse
import com.pawel.worldline_android_technical_test.data.model.movies.Result

interface MoviesRepository {
    fun getMoviesFromCache(): List<Result>?
    fun saveMoviesToCache(listResult: List<Result>)
    suspend fun getMovies(): MovieApiResponse
    suspend fun getMovie(movieID: String): Movie
}