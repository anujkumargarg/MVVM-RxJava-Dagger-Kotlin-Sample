package com.tmdbclient.mvvm.api

import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Path


interface TCBlogApi {

    @GET("{year}/{month}/{date}/{article}")
    fun getTrueCallerBlogContent(
        @Path("year") year: String,
        @Path("month") month: String,
        @Path("date") date: String,
        @Path("article") article: String
    ): Observable<ResponseBody>
}