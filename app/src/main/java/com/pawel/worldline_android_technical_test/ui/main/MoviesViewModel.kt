package com.pawel.worldline_android_technical_test.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pawel.worldline_android_technical_test.data.model.movies.Result
import com.pawel.worldline_android_technical_test.data.repository.MoviesRepository
import kotlinx.coroutines.launch

class MoviesViewModel(private val moviesRepository: MoviesRepository) : ViewModel() {

    private val moviesList = MutableLiveData<List<Result>>()
    val movies: LiveData<List<Result>>
        get() = moviesList

    init {
        fetchMovies()
    }

    private fun fetchMovies() {
        viewModelScope.launch {
            moviesList.value = moviesRepository.getMovies().results
        }
    }
}