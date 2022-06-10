package com.pawel.domain.service

import com.pawel.domain.repository.MoviesRepository
import javax.inject.Inject
import com.pawel.domain.model.movies.Result
import com.pawel.domain.repository.ImageRepository
import com.pawel.domain.util.helpers.PosterSize

class MoviesServiceImpl @Inject constructor(
    private val moviesRepository: MoviesRepository,
    private val imageRepository: ImageRepository
) :
    MoviesService {

    override suspend fun getMovies(): List<Result>? = moviesRepository.getMovies()

    override suspend fun getMovie(movieID: String) = moviesRepository.getMovie(movieID)

    override fun getPosterUrl(endpoint: String, size: PosterSize): String =
        imageRepository.getImageUrl(endpoint, size)
}
