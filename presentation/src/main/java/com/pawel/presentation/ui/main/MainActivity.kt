package com.pawel.presentation.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.pawel.presentation.EspressoIdlingResource
import com.pawel.presentation.ui.movieDetail.DetailMovieFragment
import com.pawel.presentation.ui.moviesList.MoviesListFragment
import com.pawel.movieapp.presentation.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            replaceFragment(MoviesListFragment.newInstance())
        }
    }

    fun replaceFragment(fragment: Fragment) {
        /**
         * EspressoIdlingResource...() utility for AndroidTest
         */
        EspressoIdlingResource.increment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment).commitNow()
        updateBackButton(fragment)
        EspressoIdlingResource.decrement()
    }

    private fun updateBackButton(fragment: Fragment) {
        when (fragment) {
            is MoviesListFragment -> {
                // Don't need Back Button - hide it
                supportActionBar?.setDisplayHomeAsUpEnabled(false)
                supportActionBar?.setDisplayShowCustomEnabled(false)
            }
            is DetailMovieFragment -> {
                // Show Back Button to enable back to list of movies
                supportActionBar?.setDisplayHomeAsUpEnabled(true)
                supportActionBar?.setDisplayShowCustomEnabled(true)
                // Change icon of back button
                supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onBackPressed() {
        replaceFragment(MoviesListFragment.newInstance())
    }
}
