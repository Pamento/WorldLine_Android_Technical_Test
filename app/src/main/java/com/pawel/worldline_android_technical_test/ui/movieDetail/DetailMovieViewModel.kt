package com.pawel.worldline_android_technical_test.ui.movieDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pawel.worldline_android_technical_test.data.model.movie.Movie
import com.pawel.worldline_android_technical_test.data.repository.MoviesRepository
import kotlinx.coroutines.launch

class DetailMovieViewModel(private val moviesRepository: MoviesRepository) : ViewModel() {

    private val _movie = MutableLiveData<Movie>()
    val movie: LiveData<Movie>
        get() = _movie

    fun getMovie(movieID: String) {
        viewModelScope.launch {
            _movie.value = moviesRepository.getMovie(movieID)
        }
    }
}