package com.pawel.worldline_android_technical_test.data.repository

import com.pawel.worldline_android_technical_test.data.api.MoviesRepositoryNetwork
import com.pawel.worldline_android_technical_test.data.model.movie.Movie
import com.pawel.worldline_android_technical_test.data.model.movies.MovieApiResponse
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(private val moviesRepositoryNetwork: MoviesRepositoryNetwork) :
    MoviesRepository {
    override suspend fun getMovies(): MovieApiResponse {
        return moviesRepositoryNetwork.getMovies()
    }

    override suspend fun getMovie(movieID : String): Movie {
        return moviesRepositoryNetwork.getMovieDetail(movieID)
    }
}