package com.pawel.worldline_android_technical_test.ui.main

import android.os.Bundle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import org.junit.Test
import org.junit.runner.RunWith
import com.pawel.presentation.ui.moviesList.MoviesListFragment
import com.pawel.presentation.EspressoIdlingResource
import com.pawel.worldline_android_technical_test.R
import com.pawel.worldline_android_technical_test.launchFragmentInHiltContainer
import com.pawel.worldline_android_technical_test.withIndex
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.After
import org.junit.Before
import org.junit.Rule

@RunWith(AndroidJUnit4::class)
@MediumTest
@HiltAndroidTest
class MoviesListFragmentTest {

    private val item1Title = "Spider-Man: No Way Home"
    private val item2Title = "The King's Man"
    private val item3Title = "Scream"

    @get:Rule
    val movieFragmentRule = HiltAndroidRule(this)

    @Before
    fun init() {
        movieFragmentRule.inject()
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
        launchFragmentInHiltContainer<MoviesListFragment>(Bundle(), R.style.ThemeOverlay_AppCompat)
    }

    @After
    fun cleanup() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
    }

    @Test
    fun main_movie_fragment_displayed_successfully() {
        onView(withId(R.id.listMoviesRV)).check(matches(isDisplayed()))
    }

    @Test
    fun recyclerView_has_items_displayed() {
        onView(withId(R.id.listMoviesRV)).check(matches(isDisplayed()))
    }

    @Test
    fun first_recyclerView_item_display_title_correctly() {
        onView(withIndex(withId(R.id.item_title), 0)).check(matches(withText(item1Title)))
    }

    @Test
    fun second_recyclerView_item_display_title_correctly() {
        onView(withIndex(withId(R.id.item_title), 1)).check(matches(withText(item2Title)))
    }

    @Test
    fun third_recyclerView_item_display_title_correctly() {
        onView(withIndex(withId(R.id.item_title), 2)).check(matches(withText(item3Title)))
    }
}
