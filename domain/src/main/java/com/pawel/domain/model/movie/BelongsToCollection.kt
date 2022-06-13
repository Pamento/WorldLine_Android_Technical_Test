package com.pawel.domain.model.movie

@SuppressWarnings("ForbiddenPublicDataClass", "ConstructorParameterNaming")
data class BelongsToCollection(
    val backdrop_path: String,
    val id: Int,
    val name: String,
    val poster_path: String
)
