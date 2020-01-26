package com.tmdbclient.mvvm.api

import com.tmdbclient.mvvm.model.MovieResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query


interface MoviesApi {

    @GET("movie/popular")
    fun getPopularMovies(@Query("api_key") apiKey: String, @Query("page") page: Int): Observable<MovieResponse>
}