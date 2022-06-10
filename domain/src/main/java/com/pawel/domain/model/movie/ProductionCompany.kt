package com.pawel.domain.model.movie

@SuppressWarnings("ForbiddenPublicDataClass")
data class ProductionCompany(
    val id: Int,
    val logo_path: String,
    val name: String,
    val origin_country: String
)
