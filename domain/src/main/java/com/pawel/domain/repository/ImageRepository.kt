package com.pawel.domain.repository

import com.pawel.domain.util.helpers.PosterSize

interface ImageRepository {
    fun getImageUrl(endpoint: String, size: PosterSize): String
}
