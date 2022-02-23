package com.pawel.domain.service

import com.pawel.domain.repository.MoviesRepository
import com.pawel.domain.model.movies.Result
import javax.inject.Inject


class MoviesServiceImpl @Inject constructor(private val moviesRepository: MoviesRepository) :
    MoviesService {

    override suspend fun getMovies(): List<Result>? = moviesRepository.getMovies()

    override suspend fun getMovie(movieID: String) = moviesRepository.getMovie(movieID)
}