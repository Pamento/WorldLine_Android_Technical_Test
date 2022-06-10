package com.pawel.domain.util.helpers

import com.pawel.domain.model.movie.ProductionCompany

fun buildStringForCompanies(dataList: List<ProductionCompany>): CharSequence {
    val sBuilder = StringBuilder()
    for (company in dataList) {
        sBuilder.append(company.name).append("\n")
    }
    return sBuilder
}
