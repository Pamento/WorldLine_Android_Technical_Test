package com.pawel.worldline_android_technical_test.ui.main

import android.widget.ScrollView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.pawel.common.util.Constants.MOVIES_RECYCLER_VIEW_TAG
import com.pawel.presentation.EspressoIdlingResource
import com.pawel.presentation.ui.main.MainActivity
import com.pawel.worldline_android_technical_test.R
import com.pawel.worldline_android_technical_test.withIndex
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.hamcrest.core.AllOf
import org.hamcrest.core.AllOf.allOf
import org.hamcrest.core.IsInstanceOf
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@MediumTest
@HiltAndroidTest
class MainActivityTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)


    @get:Rule(order = 1)
    val scenario = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun init() {
        hiltRule.inject()
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
    }

    @After
    fun cleanup() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
    }

    @Test
    fun mainActivity_and_ToolBar_with_title_isDisplayed_successfully() {
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
    fun launchingMainActivity() {

        onView(allOf(withId(R.id.listMoviesRV), isDisplayed())).check(matches(isDisplayed()))
        onView(allOf(withIndex(withId(R.id.item_movie_container), 0))).perform(click())

        val textView = onView(
            allOf(
                withId(R.id.detailMovie_overview),
                withText("Peter Parker is unmasked and no longer able to separate his normal life from the high-stakes of being a super-hero. When he asks for help from Doctor Strange the stakes become even more dangerous, forcing him to discover what it truly means to be Spider-Man."),
                withParent(withParent(IsInstanceOf.instanceOf(ScrollView::class.java))),
                isDisplayed()
            )
        )
        textView.check(matches(withText("Peter Parker is unmasked and no longer able to separate his normal life from the high-stakes of being a super-hero. When he asks for help from Doctor Strange the stakes become even more dangerous, forcing him to discover what it truly means to be Spider-Man.")))

        val textView2 = onView(
            allOf(
                withId(R.id.detailMovie_overview),
                withText("Peter Parker is unmasked and no longer able to separate his normal life from the high-stakes of being a super-hero. When he asks for help from Doctor Strange the stakes become even more dangerous, forcing him to discover what it truly means to be Spider-Man."),
                withParent(withParent(IsInstanceOf.instanceOf(ScrollView::class.java))),
                isDisplayed()
            )
        )
        textView2.check(matches(withText("Peter Parker is unmasked and no longer able to separate his normal life from the high-stakes of being a super-hero. When he asks for help from Doctor Strange the stakes become even more dangerous, forcing him to discover what it truly means to be Spider-Man.")))

    }

    @Test
    fun navigation_to_detailFragment_and_back_isSuccessful() {
        onView(allOf(withId(R.id.listMoviesRV), isDisplayed())).check(matches(isDisplayed()))
        onView(allOf(withIndex(withId(R.id.item_movie_container), 0))).perform(click())
        onView(withId(R.id.detailMovie_container)).check(matches(isDisplayed()))
        pressBack()
        onView(allOf(withId(R.id.listMoviesRV), withParent(withId(R.id.frg_listMovies_container)))).check(matches(isDisplayed()))
    }

}