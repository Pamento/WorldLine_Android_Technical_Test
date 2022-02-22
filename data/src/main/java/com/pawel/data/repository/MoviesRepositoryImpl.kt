package com.pawel.data.repository

import com.pawel.data.api.MoviesRepositoryNetwork
import com.pawel.data.cache.MoviesInMemoryCache
import com.pawel.data.model.movie.Movie
import com.pawel.data.model.movies.MovieApiResponse
import com.pawel.data.model.movies.Result
import com.pawel.data.util.Const.MOVIES_IN_MEMORY_KEY
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