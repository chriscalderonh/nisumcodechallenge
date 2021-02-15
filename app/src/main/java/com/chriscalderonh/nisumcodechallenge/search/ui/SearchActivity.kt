package com.chriscalderonh.nisumcodechallenge.search.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.chriscalderonh.nisumcodechallenge.R
import com.chriscalderonh.nisumcodechallenge.search.presentation.di.DaggerSearchComponent

class SearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        DaggerSearchComponent.builder()
            .build()
            .inject(this)
    }
}