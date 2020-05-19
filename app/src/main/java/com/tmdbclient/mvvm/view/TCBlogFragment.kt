package com.tmdbclient.mvvm.view

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.tmdbclient.mvvm.R
import com.tmdbclient.mvvm.viewmodel.TCBlogViewModel
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_tc_blog.*
import java.lang.StringBuilder
import javax.inject.Inject


class TCBlogFragment: DaggerFragment() {

    lateinit var viewModel: TCBlogViewModel

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tc_blog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this, factory)
            .get(TCBlogViewModel::class.java)
        viewModel.tenthCharacter.observe(this, Observer {tenthChar ->
            if(tenthChar == null) {
                tenthCharTextView.visibility = GONE
            } else {
                tenthCharTextView.visibility = VISIBLE
                tenthCharTextView.text = tenthChar.toString()
            }
        })
        viewModel.eachTenthCharacter.observe(this, Observer {eachTenthCharList ->
            val eachTenthCharAsString =  eachTenthCharList.fold(StringBuilder()) { accumulator, character ->
                accumulator.append(character).append("  ")
            }.toString()
            eachTenthCharTextView.apply {
                if(eachTenthCharAsString.isEmpty()){
                    visibility = GONE
                } else {
                    visibility = VISIBLE
                    text = eachTenthCharAsString
                }
            }
        })
        viewModel.wordCount.observe(this, Observer { wordCountMap ->
            val stringBuilder = StringBuilder()
            wordCountMap.forEach {entry ->
                stringBuilder.append(entry.key).append("  ---->  ").append(entry.value).append("\\n")
            }
            val wordMapAsString = stringBuilder.toString()
            wordMapTextView.apply {
                if(wordMapAsString.isEmpty()){
                    visibility = GONE
                } else {
                    visibility = VISIBLE
                    text = wordMapAsString
                }
            }
        })
        eachTenthCharTextView.movementMethod = ScrollingMovementMethod.getInstance()
        wordMapTextView.movementMethod = ScrollingMovementMethod.getInstance()

        requestButton.setOnClickListener {
            viewModel.fetchTCBlog()
        }
    }
}