package com.pawel.presentation.ui.movieDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.pawel.common.networkErrorHandling.MovieException
import com.pawel.domain.service.MoviesService
import com.pawel.domain.util.helpers.PosterSize
import com.pawel.domain.util.helpers.PosterUrlBuilder
import com.pawel.presentation.base.BaseViewModel
import com.pawel.presentation.helpers.Event
import com.pawel.presentation.helpers.MoviesError
import com.pawel.presentation.helpers.NetworkResponse
import com.pawel.presentation.helpers.SingleMovie
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailMovieViewModel @Inject constructor(private val moviesService: MoviesService) :
    BaseViewModel() {

    private val _networkResponse = MutableLiveData<NetworkResponse>()
    val networkResponse: LiveData<NetworkResponse>
        get() = _networkResponse
    private var _movieId: String = ""
    lateinit var error: Event<MovieException>

    fun getPosterUrl(endpoint: String, size: PosterSize) =
        moviesService.getPosterUrl(endpoint, size)


    fun getMovie(movieID: String) {
        if (_networkResponse.value == null || _movieId != movieID) {
            _movieId = movieID
            viewModelScope.launchBy(
                block = {
                    val movie = moviesService.getMovie(movieID)
                    _networkResponse.postValue(SingleMovie(movie))
                },
                error = { e ->
                    _networkResponse.postValue(MoviesError(e))
                    error = Event(e)
                }
            )
        }
    }
}