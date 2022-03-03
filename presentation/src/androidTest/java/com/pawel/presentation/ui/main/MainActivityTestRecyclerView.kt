package com.pawel.presentation.ui.main

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.pawel.worldline_android_technical_test.presentation.R
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.hamcrest.Matchers.allOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

const val RECYCLERVIEW_ITEMS_COUNT = 20

@LargeTest
@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class MainActivityTestRecyclerView {

    @get:Rule
    var mActivityTestRule = HiltAndroidRule(this)

    @Before
    fun init() {
        mActivityTestRule.inject()
    }


    @Test
    fun mainActivityTest_RecyclerView() {
        val recyclerView = onView(
            allOf(
                withId(R.id.listMoviesRV),
                withParent(
                    allOf(
                        withId(R.id.frg_listMovies_container),
                        withParent(withId(R.id.container))
                    )
                ),
                isDisplayed()
            )
        )
        recyclerView.check(matches(isDisplayed()))

        val viewGroup = onView(
            allOf(
                withParent(
                    allOf(
                        withId(R.id.listMoviesRV),
                        withParent(withId(R.id.frg_listMovies_container))
                    )
                ),
                isDisplayed()
            )
        )
        viewGroup.check(matches(hasChildCount(RECYCLERVIEW_ITEMS_COUNT)))
    }
}
