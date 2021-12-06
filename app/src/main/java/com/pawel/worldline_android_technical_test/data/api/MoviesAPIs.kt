package com.pawel.worldline_android_technical_test.data.api

import com.pawel.worldline_android_technical_test.BuildConfig
import com.pawel.worldline_android_technical_test.data.model.movie.Movie
import com.pawel.worldline_android_technical_test.data.model.movies.MovieApiResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface MoviesAPIs {

    @GET("/movie/popular?api_key=" + BuildConfig.API_KEY + "&language=fr=&page=1")
    suspend fun getMovies() : MovieApiResponse

    @GET("/movie/{movie_id}?api_key=" + BuildConfig.API_KEY + "&language=fr=&page=1")
    suspend fun getMovieDetail(
        @Path("movie_id") movieID: String,
    ) : Movie


}