package com.pawel.domain.util.helpers

class PosterUrlBuilder(
    private val posterUrl: String,
    private val endpoint: String,
    private val size: PosterSize
) {

    fun posterUrl() = when (size) {
        PosterSize.POSTER_TREE_HUNDRED -> "${posterUrl}w200/$endpoint"
        PosterSize.POSTER_FOUR_HUNDRED -> "${posterUrl}w400/$endpoint"
        PosterSize.POSTER_FIFE_HUNDRED -> "${posterUrl}w500/$endpoint"
    }
}