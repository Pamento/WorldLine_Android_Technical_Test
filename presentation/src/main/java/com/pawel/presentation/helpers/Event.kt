package com.pawel.presentation.helpers

open class Event<out T>(private val content: T) {

    private var hasBeenHandled = false
        // private set // Allow external read but not write

    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }

    fun peekContent(): T = content
}
