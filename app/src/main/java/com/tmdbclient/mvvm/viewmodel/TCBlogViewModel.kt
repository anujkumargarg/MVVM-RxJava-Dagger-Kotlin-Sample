package com.tmdbclient.mvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tmdbclient.mvvm.repository.TCBlogRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Inject

class TCBlogViewModel @Inject constructor(
    private val tcBlogRepository: TCBlogRepository
):ViewModel() {

    private val disposables: CompositeDisposable = CompositeDisposable()
    val tenthCharacter: MutableLiveData<Char> = MutableLiveData()
    val eachTenthCharacter: MutableLiveData<List<Char>> = MutableLiveData()
    val wordCount: MutableLiveData<TreeMap<String, Int>> = MutableLiveData()

    fun fetchTCBlog(){
        tcBlogRepository.getTCBlog(YEAR, MONTH, DATE, ARTICLE)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({
                // onNext
                tenthCharacter.value = it[9]
                val allTenthChars:MutableList<Char> = mutableListOf()
                it.forEachIndexed { index, character ->
                    if((index+1)%10==0){
                        allTenthChars.add(character)
                    }
                }
                eachTenthCharacter.value = allTenthChars
                val wordMap:TreeMap<String, Int> = TreeMap()
                it.split(Regex("[\\s+]"), 0).forEach {word->
                    val currentValue = wordMap[word]
                    if (currentValue != null) {
                        wordMap[word] = currentValue + 1
                    } else {
                        wordMap[word] = 1
                    }
                }
                wordCount.value = wordMap
            }, {
                // onError
                tenthCharacter.value = null
                eachTenthCharacter.value = null
                wordCount.value = null
            }).addToDisposables()
    }

    private fun Disposable.addToDisposables() {
        disposables.add(this)
    }

    companion object {
        const val YEAR = "2018"
        const val MONTH = "01"
        const val DATE = "22"
        const val ARTICLE = "life-as-an-android-engineer"
    }
}