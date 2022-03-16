package com.pawel.presentation.ui.moviesList

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pawel.common.networkErrorHandling.MovieException
import com.pawel.domain.service.MoviesService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.pawel.domain.model.movies.Result
import com.pawel.presentation.base.BaseViewModel

@HiltViewModel
class MoviesViewModel @Inject constructor(private val moviesService: MoviesService) : BaseViewModel() {

    private val moviesList = MutableLiveData<List<Result>>()
    val movies: LiveData<List<Result>>
        get() = moviesList

    private val _error = MutableLiveData<MovieException>()
    val error: LiveData<MovieException>
        get() = _error

    init {
        fetchMovies()
    }

    private fun fetchMovies() {
        viewModelScope.launchBy(
            block = {
                moviesList.value = moviesService.getMovies()
                Log.i("TAG", "MViewM_fetchMovies: movies.size:: ${moviesList.value?.size}")
            },
            error = {
                _error.value = it
            }
        )
    }

    fun movieId(position: Int) = moviesList.value?.get(position)?.id
}