package com.pawel.presentation.ui.moviesList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.pawel.common.networkErrorHandling.MovieException
import com.pawel.domain.model.movies.Result
import com.pawel.domain.service.MoviesService
import com.pawel.domain.util.helpers.PosterSize
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.pawel.presentation.base.BaseViewModel
import com.pawel.presentation.helpers.Event
import com.pawel.presentation.helpers.MoviesError
import com.pawel.presentation.helpers.MoviesList
import com.pawel.presentation.helpers.NetworkResponse

@HiltViewModel
class MoviesViewModel @Inject constructor(private val moviesService: MoviesService) : BaseViewModel() {

    private val _networkResponse = MutableLiveData<NetworkResponse>()
    val networkResponse: LiveData<NetworkResponse>
        get() = _networkResponse

    private var _movies : List<Result> = mutableListOf()
    lateinit var error: Event<MovieException>

    init {
        fetchMovies()
    }

    fun getImageUrl(endpoint: String) : String {
        return moviesService.getPosterUrl(endpoint, PosterSize.POSTER_FOUR_HUNDRED)
    }

    private fun fetchMovies() {
        if (_networkResponse.value == null) {
            viewModelScope.launchBy(
                block = {
                    val movies = moviesService.getMovies()
                    movies?.let {
                        _movies = movies
                        _networkResponse.postValue(MoviesList(movies))
                    }
                },
                error = { e ->
                    _networkResponse.postValue(MoviesError(e))
                    error = Event(e)
                }
            )
        }
    }

    fun movieId(position: Int) = _movies.isNotEmpty().let {
        _movies[position].id
    }
}