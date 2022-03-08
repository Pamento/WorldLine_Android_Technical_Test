package com.pawel.worldline_android_technical_test.ui.main

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
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
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.AllOf.allOf
import org.hamcrest.core.IsInstanceOf
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
        launchFragmentInHiltContainer<MoviesListFragment>(Bundle(), R.style.ThemeOverlay_AppCompat)
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
        val recyclerView = onView(
            allOf(
                withId(R.id.listMoviesRV),
                hasFocus()
            )
        )
        recyclerView.perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

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

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}