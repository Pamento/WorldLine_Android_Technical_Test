package com.pawel.domain.util.helpers

sealed class PosterSize

data class PosterTreeHundred(val url: String) : PosterSize()
data class PosterFourHundred(val url: String) : PosterSize()
data class PosterFifeHundred(val url: String) : PosterSize()
