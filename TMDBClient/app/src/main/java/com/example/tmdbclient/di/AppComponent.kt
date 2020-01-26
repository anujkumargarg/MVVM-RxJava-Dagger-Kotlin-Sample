package com.example.tmdbclient.di

import com.example.tmdbclient.App
import com.example.tmdbclient.di.movies.MoviesModule
import com.example.tmdbclient.di.movies.MoviesViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class, ActivityBuilderModule::class, AppModule::class, MoviesViewModelModule::class, MoviesModule::class]
)
interface AppComponent: AndroidInjector<App>{

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun injectApplication(app: App):Builder

        fun build(): AppComponent
    }
}