package com.example.tmdbclient.di

import com.example.tmdbclient.di.movies.MoviesModule
import com.example.tmdbclient.di.movies.MoviesViewModelModule
import com.example.tmdbclient.view.PopularMoviesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector
    abstract fun contributePopularMoviesFragment(): PopularMoviesFragment
}