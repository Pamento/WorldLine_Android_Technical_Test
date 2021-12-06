package com.pawel.worldline_android_technical_test.data.api

import com.pawel.worldline_android_technical_test.data.model.movie.Movie
import com.pawel.worldline_android_technical_test.data.model.movies.MovieApiResponse

class ApiHelperImpl(private val moviesAPIs: MoviesAPIs) : ApiHelper {
    override suspend fun getMovies(): MovieApiResponse {
        return moviesAPIs.getMovies()
    }

    override suspend fun getMovie(movieID : String): Movie {
        return moviesAPIs.getMovieDetail(movieID)
    }
}