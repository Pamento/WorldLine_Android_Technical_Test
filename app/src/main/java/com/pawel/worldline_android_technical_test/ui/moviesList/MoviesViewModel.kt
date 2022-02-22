package com.pawel.worldline_android_technical_test.ui.moviesList

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pawel.data.model.movies.Result
import com.pawel.worldline_android_technical_test.service.MoviesService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(private val moviesService: MoviesService) : ViewModel() {

    private val moviesList = MutableLiveData<List<Result>>()
    val movies: LiveData<List<Result>>
        get() = moviesList

    init {
        fetchMovies()
    }

    private fun fetchMovies() {
        viewModelScope.launch {
            moviesList.value = moviesService.getMovies()
            Log.i("TAG", "MViewM_fetchMovies: movies.size:: ${moviesList.value?.size}")
        }
    }

    fun movieId(position: Int) = moviesList.value?.get(position)?.id
}