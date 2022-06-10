package com.pawel.presentation.helpers

import com.pawel.common.networkErrorHandling.MovieException
import com.pawel.domain.model.movie.Movie
import com.pawel.domain.model.movies.Result

sealed class NetworkResponse

@SuppressWarnings("ForbiddenPublicDataClass")
data class SingleMovie(val movie: Movie) : NetworkResponse()
@SuppressWarnings("ForbiddenPublicDataClass")
data class MoviesList(val movies: List<Result>) : NetworkResponse()
@SuppressWarnings("ForbiddenPublicDataClass")
data class MoviesError(val error: MovieException) : NetworkResponse()
