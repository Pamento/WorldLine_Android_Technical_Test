package com.pawel.data.repository


import com.pawel.data.model.movie.Movie
import com.pawel.data.model.movies.MovieApiResponse
import com.pawel.data.model.movies.Result


interface MoviesRepository {
    fun getMoviesFromCache(): List<Result>?
    fun saveMoviesToCache(listResult: List<Result>)
    suspend fun getMovies(): MovieApiResponse
    suspend fun getMovie(movieID: String): Movie
}