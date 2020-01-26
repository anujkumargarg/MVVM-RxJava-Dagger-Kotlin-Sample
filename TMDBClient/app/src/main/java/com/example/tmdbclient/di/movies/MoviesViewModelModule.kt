package com.example.tmdbclient.di.movies

import androidx.lifecycle.ViewModel
import com.example.tmdbclient.ViewModelKey
import com.example.tmdbclient.repository.PopularMoviesRepository
import com.example.tmdbclient.viewmodel.PopularMoviesViewModel
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