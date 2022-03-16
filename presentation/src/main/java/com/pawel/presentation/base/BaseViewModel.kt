package com.pawel.presentation.base

import androidx.lifecycle.ViewModel
import com.pawel.common.networkErrorHandling.MovieException
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

open class BaseViewModel : ViewModel() {

    fun CoroutineScope.launchBy(error: (MovieException) -> Unit, block: suspend CoroutineScope.() -> Unit) {
        val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
            error.invoke(if (throwable is MovieException) throwable else MovieException.genericError() )
        }

        this.launch(
            context = Dispatchers.IO + coroutineExceptionHandler,
            block = block
        )
    }
}