package com.pawel.domain.model.movies

@SuppressWarnings("ForbiddenPublicDataClass", "ConstructorParameterNaming")
data class MovieApiResponse(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)
