package com.pawel.domain.util.helpers

import junit.framework.TestCase

class ConvertersKtTest : TestCase() {

    fun testFrenchFormatOfDate() {
        val date = "2022-02-28"
        val toDate = "28/02/2022"
        val dateConverted = frenchFormatOfDate(date)
        assertEquals(toDate, dateConverted)
    }

    fun testAddComaInPrice() {
        val price = "20220228"
        val toPrice = "20,220,228"
        val priceConverted = addComaInPrice(price)
        assertEquals(toPrice, priceConverted)
    }

    fun testGetListSize() {
        val list = listOf('a', 'b', 'c')
        val count = getListSize(list)
        assertEquals(3, count)
    }
}