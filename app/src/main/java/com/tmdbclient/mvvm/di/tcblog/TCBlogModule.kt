package com.tmdbclient.mvvm.di.tcblog

import androidx.lifecycle.ViewModel
import com.tmdbclient.mvvm.ViewModelKey
import com.tmdbclient.mvvm.api.TCBlogApi
import com.tmdbclient.mvvm.repository.TCBlogRepository
import com.tmdbclient.mvvm.viewmodel.TCBlogViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import retrofit2.Retrofit

@Module
class TCBlogModule {

    @Provides
    fun provideTCBlogApi(retrofit: Retrofit): TCBlogApi {
        return retrofit.create(TCBlogApi::class.java)
    }

    @Provides
    @IntoMap
    @ViewModelKey(TCBlogViewModel::class)
    fun bindTCBlogViewModelIntoMap(repository: TCBlogRepository): ViewModel
            = TCBlogViewModel(repository)
}