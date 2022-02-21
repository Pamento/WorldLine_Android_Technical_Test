package com.pawel.worldline_android_technical_test.util.helpers

import com.example.data.model.movie.ProductionCompany

fun buildStringForCompanies(dataList: List<ProductionCompany>): CharSequence {
    val sBuilder = StringBuilder()
    for (company in dataList) {
        sBuilder.append(company.name).append("\n")
    }
    return sBuilder
}