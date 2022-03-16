package com.pawel.presentation.ui.movieDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pawel.common.networkErrorHandling.MovieException
import com.pawel.domain.model.movie.Movie
import com.pawel.domain.service.MoviesService
import com.pawel.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailMovieViewModel @Inject constructor(private val moviesService: MoviesService) :
    BaseViewModel() {

    private val _movie = MutableLiveData<Movie>()
    val movie: LiveData<Movie>
        get() = _movie

    private val _error = MutableLiveData<MovieException>()
    val error: LiveData<MovieException>
    get() = _error

    fun getMovie(movieID: String) {
        viewModelScope.launchBy(
            block = {
                _movie.value = moviesService.getMovie(movieID)
            },
            error = {
                _error.value = it
            }
        )
    }
}