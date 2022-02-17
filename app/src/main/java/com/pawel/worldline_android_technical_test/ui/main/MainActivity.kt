package com.pawel.worldline_android_technical_test.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.pawel.worldline_android_technical_test.R
import com.pawel.worldline_android_technical_test.ui.movieDetail.DetailMovieFragment
import com.pawel.worldline_android_technical_test.ui.moviesList.MoviesListFragment
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
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment).commitNow()
        updateBackButton(fragment)
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