package com.pawel.common.networkErrorHandling

import java.io.IOException


const val FUNCTIONNAL_ERROR_CODE = 400
const val TOKEN_EXPIRED_ERROR_CODE = 401
const val UNAUTHORIZED_ERROR_CODE = 403
const val GENERIC_ERROR_CODE = 500
const val CBS_ERROR_CODE = 503

class MovieException(
    val movieErrorCode: MovieErrorCode,
    message: String = "",
    val isHttpError: Boolean
) : IOException(message) {

    companion object {
        fun genericError() = MovieException(
            MovieErrorCode(GENERIC_ERROR_CODE),
            isHttpError = true
        )
    }
}