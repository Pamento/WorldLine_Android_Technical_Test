package com.pawel.worldline_android_technical_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pawel.worldline_android_technical_test.ui.main.MoviesListFrg

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MoviesListFrg.newInstance())
                .commitNow()
        }
    }
}