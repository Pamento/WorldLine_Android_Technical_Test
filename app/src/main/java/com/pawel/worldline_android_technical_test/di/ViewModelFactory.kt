package com.pawel.worldline_android_technical_test.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pawel.worldline_android_technical_test.data.api.ApiHelper
import com.pawel.worldline_android_technical_test.data.repository.MoviesRepository
import com.pawel.worldline_android_technical_test.ui.movieDetail.DetailMovieViewModel
import com.pawel.worldline_android_technical_test.ui.moviesList.MoviesViewModel
import java.lang.IllegalArgumentException

class ViewModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MoviesViewModel::class.java)) {
            return MoviesViewModel(MoviesRepository(apiHelper)) as T
        }
        if (modelClass.isAssignableFrom(DetailMovieViewModel::class.java)) {
            return DetailMovieViewModel(MoviesRepository(apiHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}