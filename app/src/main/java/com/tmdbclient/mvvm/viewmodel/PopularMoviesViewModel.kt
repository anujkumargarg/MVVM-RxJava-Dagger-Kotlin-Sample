package com.tmdbclient.mvvm.viewmodel

import androidx.lifecycle.ViewModel
import com.tmdbclient.mvvm.model.Movie
import com.tmdbclient.mvvm.repository.PopularMoviesRepository
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class PopularMoviesViewModel constructor(
    val moviesRepository:PopularMoviesRepository
): ViewModel() {
    private val disposable: CompositeDisposable = CompositeDisposable()

    //private val moviesRepository = PopularMoviesRepository()

    lateinit var searchQuery: Observable<String>

    fun getPopularMovies(): Observable<List<Movie>> {
        moviesRepository.clearPopularMovies()
        return Observable.combineLatest<List<Movie>, String, List<Movie>>(
            moviesRepository.getPopularMovies(),
            searchQuery.startWith(""),
            BiFunction { a: List<Movie>, b: String ->
                a.filter {
                    it.title?.startsWith(b, true) ?: false
                }
            })
    }

    fun requestPopularMovies(){
        Observable.intervalRange(1, 10, 0, 10, TimeUnit.SECONDS)
            .subscribeOn(Schedulers.io())
            .subscribe {
                moviesRepository.requestPopularMovies(it.toInt())
            }.disposeOnDestroy()
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
        moviesRepository.clear()
    }
    private fun Disposable.disposeOnDestroy() {
        disposable.add(this)
    }
}