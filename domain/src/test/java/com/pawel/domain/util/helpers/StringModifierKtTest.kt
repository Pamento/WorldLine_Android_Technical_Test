package com.pawel.domain.util.helpers

import com.pawel.domain.model.movie.ProductionCompany
import junit.framework.TestCase

class StringModifierKtTest : TestCase() {

    private var productionCompany =
        listOf(
            ProductionCompany(id = 1, "", name = "OneCompany", ""),
            ProductionCompany(id = 2, "", name = "SecondCompany", ""),
            ProductionCompany(id = 3, "", name = "ThirdCompany", ""),
            ProductionCompany(id = 4, "", name = "FourthCompany", "")
        )

    fun testBuildStringForCompanies() {
        val rebuildStr = buildStringForCompanies(productionCompany)
        val expectedStr = "OneCompany\nSecondCompany\nThirdCompany\nFourthCompany\n"
        assertEquals(expectedStr, rebuildStr.toString())
    }
}