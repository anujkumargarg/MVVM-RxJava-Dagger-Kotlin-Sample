package com.tmdbclient.mvvm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tmdbclient.mvvm.view.PopularMoviesFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragmentContainer, PopularMoviesFragment(), "PopularMovies")
                .commit()
        }
    }
}
