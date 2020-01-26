package com.example.tmdbclient.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.tmdbclient.R
import com.example.tmdbclient.viewmodel.PopularMoviesViewModel
import com.jakewharton.rxbinding3.widget.queryTextChangeEvents
import dagger.android.support.DaggerFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_popular_movies.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class PopularMoviesFragment : DaggerFragment() {

    private val disposable: CompositeDisposable = CompositeDisposable()
    //@Inject
    lateinit var viewModel: PopularMoviesViewModel
    @Inject
    lateinit var adapter: PopularMoviesAdapter
    @Inject
    lateinit var factory: ViewModelProvider.Factory

    //val adapter: PopularMoviesAdapter = PopularMoviesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //AndroidSupportInjection.inject(this)
        return inflater.inflate(R.layout.fragment_popular_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        moviesRecycler.adapter = adapter
        viewModel = ViewModelProviders.of(this, factory)
            .get(PopularMoviesViewModel::class.java)
        viewModel.searchQuery = search_view.queryTextChangeEvents()
            .skipInitialValue()
            .debounce(500, TimeUnit.MILLISECONDS)
            .map {
                it.queryText.toString()
            }.subscribeOn(AndroidSchedulers.mainThread())
            .observeOn(AndroidSchedulers.mainThread())
        viewModel.getPopularMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({ movieList ->
                adapter.updateMovieList(movieList.sortedBy { it.title })
            }, {
                Log.e("PopularMoviesFragment"," error occurred:", it)
            }
            )
            .disposeOnDestroy()
        viewModel.requestPopularMovies()

    }

    private fun Disposable.disposeOnDestroy() {
        disposable.add(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.clear()
    }
}

