package com.pawel.domain.repository

import com.pawel.domain.model.movie.Movie
import com.pawel.domain.model.movies.Result


interface MoviesRepository {
    fun getMoviesFromCache(): List<Result>?
    fun saveMoviesToCache(listResult: List<Result>)
    suspend fun getMovies(): List<Result>?
    suspend fun getMovie(movieID: String): Movie
}