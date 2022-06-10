package com.pawel.presentation.ui.main

import androidx.test.filters.LargeTest
import androidx.test.ext.junit.runners.AndroidJUnit4
import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import androidx.test.espresso.matcher.ViewMatchers.withText

import com.pawel.movieapp.presentation.R
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest

import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.IsInstanceOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

import org.hamcrest.Matchers.allOf
import org.junit.Before

@LargeTest
@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class MainActivityTestDetailDisplay {

    @get:Rule
    var mActivityTestRule = HiltAndroidRule(this)

    @Before
    fun init() {
        mActivityTestRule.inject()
    }

    @Test
    fun detailDisplayFromMainActivity() {
        val recyclerView = onView(
            allOf(
                withId(R.id.listMoviesRV),
                childAtPosition(
                    withId(R.id.frg_listMovies_container),
                    0
                )
            )
        )
        recyclerView.perform(actionOnItemAtPosition<ViewHolder>(0, click()))

        val textView = onView(
            allOf(
                withId(R.id.detailMovie_overview),
                withText("Peter Parker is unmasked and no longer able to separate his normal " +
                        "life from the high-stakes of being a super-hero. When he asks for help " +
                        "from Doctor Strange the stakes become even more dangerous, forcing him " +
                        "to discover what it truly means to be Spider-Man."),
                withParent(withParent(IsInstanceOf.instanceOf(android.widget.ScrollView::class.java))),
                isDisplayed()
            )
        )
        textView.check(matches(withText("Peter Parker is unmasked and no longer able to " +
                "separate his normal life from the high-stakes of being a super-hero. " +
                "When he asks for help from Doctor Strange the stakes become even more dangerous, " +
                "forcing him to discover what it truly means to be Spider-Man.")))

        val textView2 = onView(
            allOf(
                withId(R.id.detailMovie_overview),
                withText("Peter Parker is unmasked and no longer able to separate his normal " +
                        "life from the high-stakes of being a super-hero. When he asks for help" +
                        " from Doctor Strange the stakes become even more dangerous, forcing him " +
                        "to discover what it truly means to be Spider-Man."),
                withParent(withParent(IsInstanceOf.instanceOf(android.widget.ScrollView::class.java))),
                isDisplayed()
            )
        )
        textView2.check(matches(withText("Peter Parker is unmasked and no longer able" +
                " to separate his normal life from the high-stakes of being a super-hero. " +
                "When he asks for help from Doctor Strange the stakes become even more dangerous, " +
                "forcing him to discover what it truly means to be Spider-Man.")))
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
                return parent is ViewGroup && parentMatcher.matches(parent) &&
                        view == parent.getChildAt(position)
            }
        }
    }
}
