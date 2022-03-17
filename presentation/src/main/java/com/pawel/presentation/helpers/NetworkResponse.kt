package com.pawel.presentation.helpers

import com.pawel.common.networkErrorHandling.MovieException
import com.pawel.domain.model.movies.Result

sealed class NetworkResponse

data class MoviesList(val movies: List<Result>) : NetworkResponse()
data class MoviesError(val error: MovieException) : NetworkResponse()
