package com.example.tmdbclient.repository

import com.example.tmdbclient.api.MoviesApi
import com.example.tmdbclient.model.Movie
import com.example.tmdbclient.room.PopularMoviesDao
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PopularMoviesRepository @Inject constructor(
    private val moviesApi: MoviesApi,
    private val popularMoviesDao: PopularMoviesDao
) {

    private val disposables = CompositeDisposable()
    //private val popularMoviesApi = RetrofitBuilder.getPopularMoviesService()
    //private val popularMoviesDao: PopularMoviesDao = App.instance.database.getPopularMoviesDao()

    fun getPopularMovies(): Observable<List<Movie>> {
        return popularMoviesDao.getPopularMovies()
    }

    fun clearPopularMovies() {
        Observable.fromCallable {
             popularMoviesDao.deleteAll()
        }.subscribeOn(Schedulers.io()).subscribe{

        }.disposeOnClear()
    }

    fun requestPopularMovies(page: Int) {
        moviesApi.getPopularMovies(API_KEY, page).map {
            it.results
        }.subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe {
            it?.let { movies ->
                popularMoviesDao.insertPopularMovies(movies)
            }
        }.disposeOnClear()
    }

    companion object {
        const val API_KEY = "4c3c151f9d82b5ef78535759ec6cee88"
    }

    fun clear() {
        disposables.clear()
    }

    private fun Disposable.disposeOnClear() {
        disposables.add(this)
    }
}

