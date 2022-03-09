package com.pawel.worldline_android_technical_test.ui.main

import android.os.Bundle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
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
import org.hamcrest.core.AllOf.allOf
import org.hamcrest.core.IsInstanceOf
import org.junit.After
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
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
        launchFragmentInHiltContainer<MoviesListFragment>(Bundle(), R.style.ThemeOverlay_AppCompat)
    }

    @After
    fun cleanup() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
    }

    @Test
    fun check_if_fragment_display() {
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

    @Test
    fun check_if_recyclerView_display() {
        onView(
            allOf(
                withId(R.id.listMoviesRV),
                hasFocus()
            )
        ).check(
            matches(
                isDisplayed()
            )
        )
    }

    @Test
    fun recyclerView_display_success() {
        onView(withIndex(withId(R.id.listMoviesRV), 1)).check(matches(isDisplayed()))
    }

    @Test
    fun click_recyclerViewItem_display_detailMovieFragment() {

        onView(withIndex(withId(R.id.listMoviesRV), 1)).perform(click())
        val textView = onView(
            allOf(
                withId(R.id.detailMovie_overview),
                withText("Peter Parker is unmasked and no longer able to separate his normal life from the high-stakes of being a super-hero. When he asks for help from Doctor Strange the stakes become even more dangerous, forcing him to discover what it truly means to be Spider-Man."),
                withParent(withParent(IsInstanceOf.instanceOf(android.widget.ScrollView::class.java))),
                isDisplayed()
            )
        )
        textView.check(matches(withText("Peter Parker is unmasked and no longer able to separate his normal life from the high-stakes of being a super-hero. When he asks for help from Doctor Strange the stakes become even more dangerous, forcing him to discover what it truly means to be Spider-Man.")))

        val textView2 = onView(
            allOf(
                withId(R.id.detailMovie_overview),
                withText("Peter Parker is unmasked and no longer able to separate his normal life from the high-stakes of being a super-hero. When he asks for help from Doctor Strange the stakes become even more dangerous, forcing him to discover what it truly means to be Spider-Man."),
                withParent(withParent(IsInstanceOf.instanceOf(android.widget.ScrollView::class.java))),
                isDisplayed()
            )
        )
        textView2.check(matches(withText("Peter Parker is unmasked and no longer able to separate his normal life from the high-stakes of being a super-hero. When he asks for help from Doctor Strange the stakes become even more dangerous, forcing him to discover what it truly means to be Spider-Man.")))

    }
}