package com.pawel.domain.util.helpers

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

fun frenchFormatOfDate(date: String): String {
    val fromDateFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)
    val toDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE)
    val temp = fromDateFormat.parse(date)
    return temp?.let { toDateFormat.format(temp) } ?: date
}

fun addComaInPrice(price: String): String {
    val s = price.split("").toMutableList()
    val sT = ArrayList(s)
    for (el in sT) {
        if (el.isNullOrEmpty()) s.remove(el)
    }
    val l = s.size
    var r = l % 3
    val comaPrice = StringBuilder()
    for (irs in s) {
        if (r == 0 && (comaPrice.isEmpty())) {
            comaPrice.append(irs)
            r = 2
        } else if (r == 0 && comaPrice.length != l) {
            comaPrice.append(",").append(irs)
            r = 2
        } else {
            comaPrice.append(irs)
            r--
        }
    }
    return comaPrice.toString()
}

fun getListSize(dataList: List<Any>): Int = dataList.size