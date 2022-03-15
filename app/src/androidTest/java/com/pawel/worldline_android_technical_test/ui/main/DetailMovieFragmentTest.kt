package com.pawel.worldline_android_technical_test.ui.main

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.pawel.presentation.EspressoIdlingResource
import com.pawel.presentation.ui.main.MainActivity
import com.pawel.worldline_android_technical_test.R
import com.pawel.worldline_android_technical_test.withIndex
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.hamcrest.core.AllOf.allOf
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@MediumTest
@HiltAndroidTest
class DetailMovieFragmentTest {

    private val itemTitle = "Spider-Man: No Way Home"
    private val itemDescription = "Peter Parker is unmasked and no longer able to separate his normal life from the high-stakes of being a super-hero. When he asks for help from Doctor Strange the stakes become even more dangerous, forcing him to discover what it truly means to be Spider-Man."
    private val itemEditionDate = "15/12/2021"
    private val itemNotes = "8.3"
    private val itemBudget = "200,000,000 $"
    private val itemCompanies = "Marvel Studios\nPascal Pictures\nColumbia Pictures\n"


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
    fun detailMovieFragment_has_fill_all_fields() {
        // on MainActivity
        onView(withId(R.id.listMoviesRV)).check(matches(isDisplayed()))
        // .. click on first item of recyclerView
        onView(allOf(withIndex(withId(R.id.item_movie_container),0))).perform(click())
        onView(withId(R.id.detailMovie_container)).check(matches(isDisplayed()))
        // .. check content
        onView(withId(R.id.detailMovie_title)).check(matches(withText(itemTitle)))
        onView(withId(R.id.detailMovie_overview)).check(matches(withText(itemDescription)))
        onView(withId(R.id.detailMovie_releaseDate_body)).check(matches(withText(itemEditionDate)))
        onView(withId(R.id.detailMovie_rating_body)).check(matches(withText(itemNotes)))
        onView(withId(R.id.detailMovie_budget_body)).check(matches(withText(itemBudget)))
        onView(withId(R.id.detailMovie_original_title_body)).check(matches(withText(itemTitle)))
        onView(withId(R.id.detailMovie_company_body)).check(matches(withText(itemCompanies)))

        // .. check if images placeholder is displayed
        onView(withId(R.id.detailMovie_backdrop)).check(matches(isDisplayed()))
        onView(withId(R.id.detailMovie_poster)).check(matches(isDisplayed()))
    }

}