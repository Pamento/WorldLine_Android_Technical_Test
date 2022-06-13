package com.pawel.data.repository

import com.pawel.data.api.MoviesRepositoryNetwork
import com.pawel.data.cache.MoviesInMemoryCache
import com.pawel.data.util.Const.MOVIES_IN_MEMORY_KEY
import com.pawel.domain.model.movie.Movie
import com.pawel.domain.repository.MoviesRepository
import javax.inject.Inject
import com.pawel.domain.model.movies.Result

class MoviesRepositoryImpl @Inject constructor(private val moviesRepositoryNetwork: MoviesRepositoryNetwork) :
    MoviesRepository {

    @Inject
    lateinit var cache: MoviesInMemoryCache

    override suspend fun getMovies(): List<Result> = getMoviesFromCache() ?: cacheDataManager()

    override suspend fun getMovie(movieID: String): Movie =
        moviesRepositoryNetwork.getMovieDetail(movieID)

    private fun getMoviesFromCache(): List<Result>? =
        cache.fetchFromCacheMemory(MOVIES_IN_MEMORY_KEY)

    private fun saveMoviesToCache(listResult: List<Result>) =
        cache.saveInCacheMemory(MOVIES_IN_MEMORY_KEY, listResult)

    private suspend fun cacheDataManager(): List<Result> {
        val movies = moviesRepositoryNetwork.getMovies().results
        saveMoviesToCache(movies)
        return movies
    }
}
