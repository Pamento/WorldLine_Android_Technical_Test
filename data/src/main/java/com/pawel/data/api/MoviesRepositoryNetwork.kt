package com.pawel.data.api

import com.pawel.data.model.movie.Movie
import com.pawel.data.model.movies.MovieApiResponse
import com.pawel.worldline_android_technical_test.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Path


interface MoviesRepositoryNetwork {

    @GET("movie/popular?api_key=" + BuildConfig.API_KEY + "&language=fr=&page=1")
    suspend fun getMovies() : MovieApiResponse

    @GET("movie/{movie_id}?api_key=" + BuildConfig.API_KEY + "&language=fr=&page=1")
    suspend fun getMovieDetail(
        @Path("movie_id") movieID: String,
    ) : Movie
}