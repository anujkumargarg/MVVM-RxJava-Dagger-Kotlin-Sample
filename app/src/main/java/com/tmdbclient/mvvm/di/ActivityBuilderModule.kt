package com.tmdbclient.mvvm.di

import com.tmdbclient.mvvm.view.PopularMoviesFragment
import com.tmdbclient.mvvm.view.TCBlogFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector
    abstract fun contributePopularMoviesFragment(): PopularMoviesFragment

    @ContributesAndroidInjector
    abstract fun contributeTCBlogFragment(): TCBlogFragment
}