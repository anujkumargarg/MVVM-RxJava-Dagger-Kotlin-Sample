package com.tmdbclient.mvvm.di.movies

import androidx.lifecycle.ViewModel
import com.tmdbclient.mvvm.ViewModelKey
import com.tmdbclient.mvvm.repository.PopularMoviesRepository
import com.tmdbclient.mvvm.viewmodel.PopularMoviesViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
class MoviesViewModelModule {

    @Provides
    @IntoMap
    @ViewModelKey(PopularMoviesViewModel::class)
    fun bindPopularMoviesViewModelIntoMap(moviesRepository: PopularMoviesRepository):ViewModel
            = PopularMoviesViewModel(moviesRepository)
}