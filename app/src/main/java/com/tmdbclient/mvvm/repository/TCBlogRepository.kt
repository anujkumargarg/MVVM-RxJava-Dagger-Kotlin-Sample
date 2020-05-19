package com.tmdbclient.mvvm.repository

import com.tmdbclient.mvvm.api.TCBlogApi
import io.reactivex.Observable
import javax.inject.Inject

class TCBlogRepository @Inject constructor(
    private val tcBlogApi: TCBlogApi
) {

    fun getTCBlog(year: String, month: String, date: String, article: String): Observable<String> {
        return tcBlogApi.getTrueCallerBlogContent(year, month, date, article).map {
            it.string()
        }
    }
}