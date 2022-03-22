package com.pawel.data.repository

import com.pawel.data.constants.Consts.POSTER_URL
import com.pawel.domain.repository.ImageRepository
import com.pawel.domain.util.helpers.PosterSize
import com.pawel.domain.util.helpers.PosterUrlBuilder
import javax.inject.Inject

class ImageRepositoryImpl @Inject constructor() : ImageRepository {

    override fun getImageUrl(endpoint: String, size: PosterSize): String {
        val url = PosterUrlBuilder(POSTER_URL,endpoint,size)
        return url.posterUrl()
    }
}