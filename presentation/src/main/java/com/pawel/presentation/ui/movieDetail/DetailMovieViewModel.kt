package com.pawel.presentation.ui.movieDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.pawel.domain.service.MoviesService
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

    private val _networkResponse = MutableLiveData<Event<NetworkResponse>>()
    val networkResponse: LiveData<Event<NetworkResponse>>
        get() = _networkResponse

    fun getMovie(movieID: String) {
        viewModelScope.launchBy(
            block = {
                _networkResponse.postValue(Event(SingleMovie(moviesService.getMovie(movieID))))
            },
            error = {
                _networkResponse.postValue(Event(MoviesError(it)))
            }
        )
    }
}