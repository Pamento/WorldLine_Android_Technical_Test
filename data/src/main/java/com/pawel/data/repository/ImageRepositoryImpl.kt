package com.pawel.data.repository

import com.pawel.data.constants.Consts.POSTER_URL
import com.pawel.domain.repository.ImageRepository
import com.pawel.domain.util.helpers.PosterFifeHundred
import com.pawel.domain.util.helpers.PosterFourHundred
import com.pawel.domain.util.helpers.PosterSize
import com.pawel.domain.util.helpers.PosterTreeHundred
import javax.inject.Inject

class ImageRepositoryImpl @Inject constructor() : ImageRepository {

    override fun getImageUrl(endpoint: String, size: PosterSize): String {
        return when (size) {
            is PosterTreeHundred -> "${POSTER_URL}w300/$endpoint"
            is PosterFourHundred -> "${POSTER_URL}w400/$endpoint"
            is PosterFifeHundred -> "${POSTER_URL}w500/$endpoint"
        }
    }
}