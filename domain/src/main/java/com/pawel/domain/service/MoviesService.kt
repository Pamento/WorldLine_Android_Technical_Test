package com.pawel.domain.service

import com.pawel.domain.model.movie.Movie
import com.pawel.domain.model.movies.Result


interface MoviesService {
    suspend fun getMovies(): List<Result>?
    suspend fun getMovie(movieID: String): Movie
}