package com.pawel.domain.service

import com.pawel.domain.repository.MoviesRepository
import com.pawel.domain.model.movies.Result
import javax.inject.Inject


class MoviesServiceImpl @Inject constructor(private val moviesRepository: MoviesRepository) :
    MoviesService {

    override suspend fun getMovies(): List<Result>? = moviesRepository.getMoviesFromCache() ?: getMoviesFromNetwork()

    private suspend fun getMoviesFromNetwork(): List<Result>? {
        val movies = moviesRepository.getMovies().results
        movies?.let {
            moviesRepository.saveMoviesToCache(movies)
        }
        return movies
    }

    override suspend fun getMovie(movieID: String) = moviesRepository.getMovie(movieID)
}