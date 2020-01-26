package com.tmdbclient.mvvm.di

import com.tmdbclient.mvvm.view.PopularMoviesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector
    abstract fun contributePopularMoviesFragment(): PopularMoviesFragment
}