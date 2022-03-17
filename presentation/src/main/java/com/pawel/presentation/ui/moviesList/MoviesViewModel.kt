package com.pawel.presentation.ui.moviesList

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.pawel.domain.model.movies.Result
import com.pawel.domain.service.MoviesService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.pawel.presentation.base.BaseViewModel
import com.pawel.presentation.helpers.Event
import com.pawel.presentation.helpers.MoviesError
import com.pawel.presentation.helpers.MoviesList
import com.pawel.presentation.helpers.NetworkResponse
import kotlinx.coroutines.supervisorScope

@HiltViewModel
class MoviesViewModel @Inject constructor(private val moviesService: MoviesService) : BaseViewModel() {

    private val _networkResponse = MutableLiveData<Event<NetworkResponse>>()
    val networkResponse: LiveData<Event<NetworkResponse>>
        get() = _networkResponse

    private var _movies : List<Result> = mutableListOf()

    init {
        fetchMovies()
    }

    private fun fetchMovies() {
        viewModelScope.launchBy(
            block = {
                supervisorScope {
                    val movies = moviesService.getMovies()
                    movies?.let {
                        _movies = movies
                        _networkResponse.postValue(Event(MoviesList(movies)))
                    }
                    Log.i("TAG", "MViewM_fetchMovies: movies.size:: ${movies?.size}")
                }
            },
            error = { e ->
                _networkResponse.postValue(Event(MoviesError(e)))
            }
        )
    }

    fun movieId(position: Int) = _movies.isNotEmpty().let {
        _movies[position].id
    }
}