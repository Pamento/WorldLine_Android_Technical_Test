package com.pawel.worldline_android_technical_test.ui.main

import android.os.Bundle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import org.junit.Test
import org.junit.runner.RunWith
import com.pawel.presentation.ui.moviesList.MoviesListFragment
import com.pawel.worldline_android_technical_test.R
import com.pawel.worldline_android_technical_test.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.hamcrest.core.AllOf.allOf
import org.junit.Before
import org.junit.Rule

@RunWith(AndroidJUnit4::class)
@MediumTest
@HiltAndroidTest
class MoviesListFragmentTest {

    @get:Rule
    val movieFragmentRule = HiltAndroidRule(this)

    @Before
    fun init() {
        movieFragmentRule.inject()
    }

    @Test
    fun check_if_fragment_display() {
        launchFragmentInHiltContainer<MoviesListFragment>(Bundle(), R.style.ThemeOverlay_AppCompat)
        val textView = onView(
            allOf(
                withText("The movie"),
                withParent(
                    allOf(
                        withId(androidx.appcompat.R.id.action_bar),
                        withParent(withId(androidx.appcompat.R.id.action_bar_container))
                    )
                ),
                isDisplayed()
            )
        )
        textView.check(matches(withText("The movie")))
    }
}