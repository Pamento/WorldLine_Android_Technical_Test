package com.pawel.worldline_android_technical_test.service

import com.pawel.data.repository.MoviesRepository
import com.pawel.data.model.movies.Result
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