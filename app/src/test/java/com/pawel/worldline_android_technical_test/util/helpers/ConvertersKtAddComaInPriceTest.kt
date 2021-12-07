package com.pawel.worldline_android_technical_test.util.helpers

import org.junit.Assert.*

import org.junit.Test

class ConvertersKtAddComaInPriceTest {

    val price = "12300"
    val expected = "12,300"
    @Test
    fun addComaInPrice_test() {
        val result = addComaInPrice(price)
        assertEquals(expected, result)
    }
}