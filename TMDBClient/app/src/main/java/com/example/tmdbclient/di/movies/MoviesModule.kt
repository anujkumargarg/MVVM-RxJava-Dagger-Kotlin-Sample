package com.example.tmdbclient.di.movies

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.tmdbclient.api.MoviesApi
import com.example.tmdbclient.room.AppDatabase
import com.example.tmdbclient.room.PopularMoviesDao
import com.example.tmdbclient.view.PopularMoviesFragment
import com.example.tmdbclient.viewmodel.PopularMoviesViewModel
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class MoviesModule {


    //@MovieScope
    @Provides
    fun provideMoviesApi(retrofit: Retrofit): MoviesApi {
        return retrofit.create(MoviesApi::class.java)
    }

    //@MovieScope
    @Provides
    fun providePopularMoviesDao(appDatabase: AppDatabase): PopularMoviesDao {
        return appDatabase.getPopularMoviesDao()
    }

//    @Provides
//    fun providePopularMoviesViewModel(
//        popularMoviesFragment: PopularMoviesFragment,
//        factory: ViewModelProvider.Factory
//    ): PopularMoviesViewModel {
//        return ViewModelProviders.of(popularMoviesFragment, factory)
//            .get(PopularMoviesViewModel::class.java)
//    }
}