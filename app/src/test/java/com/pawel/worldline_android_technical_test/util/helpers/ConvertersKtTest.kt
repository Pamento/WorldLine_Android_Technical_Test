package com.pawel.worldline_android_technical_test.util.helpers

import org.junit.Assert.*

import org.junit.Test

class ConvertersKtTest {

    private val price = "12300"
    private val expected = "12,300"
    private val originDate = "2021-12-10"
    private val convertedDate = "10/12/2021"
    private val dataList = mutableListOf(1,2,3,4,5)
    private val dataListSize = 5
    @Test
    fun frenchFormatOfDateTest() {
        val date:String = frenchFormatOfDate(originDate)
        assertEquals(convertedDate, date)
    }

    @Test
    fun addComaInPriceTest() {
        val result = addComaInPrice(price)
        assertEquals(expected, result)
    }

    @Test
    fun getListSizeTest() {
        val res = getListSize(dataList)
        assertEquals(dataListSize, res)
    }
}