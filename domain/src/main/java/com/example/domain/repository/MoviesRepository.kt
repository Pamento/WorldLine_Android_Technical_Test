package com.example.domain.repository

import com.example.data.model.movie.Movie
import com.example.data.model.movies.MovieApiResponse
import com.example.data.model.movies.Result

interface MoviesRepository {
    fun getMoviesFromCache(): List<Result>?
    fun saveMoviesToCache(listResult: List<Result>)
    suspend fun getMovies(): MovieApiResponse
    suspend fun getMovie(movieID: String): Movie
}