package com.tmdbclient.mvvm.di.movies

import com.tmdbclient.mvvm.api.MoviesApi
import com.tmdbclient.mvvm.room.AppDatabase
import com.tmdbclient.mvvm.room.PopularMoviesDao
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