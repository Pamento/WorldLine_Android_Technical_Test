package com.example.data.repository

import com.example.data.api.MoviesRepositoryNetwork
import com.example.data.cache.MoviesInMemoryCache
import com.example.data.model.movie.Movie
import com.example.data.model.movies.MovieApiResponse
import com.example.data.model.movies.Result
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(private val moviesRepositoryNetwork: MoviesRepositoryNetwork) :
    MoviesRepository {

    @Inject
    lateinit var cache: MoviesInMemoryCache

    override fun getMoviesFromCache(): List<Result>? =
        cache.fetchFromCacheMemory(MOVIES_IN_MEMORY_KEY)

    override fun saveMoviesToCache(listResult: List<Result>) =
        cache.saveInCacheMemory(MOVIES_IN_MEMORY_KEY, listResult)

    override suspend fun getMovies(): MovieApiResponse =
        moviesRepositoryNetwork.getMovies()

    override suspend fun getMovie(movieID: String): Movie =
        moviesRepositoryNetwork.getMovieDetail(movieID)
}