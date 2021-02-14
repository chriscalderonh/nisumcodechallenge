package com.chriscalderonh.nisumcodechallenge.search.data.source

import com.chriscalderonh.nisumcodechallenge.search.data.repository.SearchRemote
import javax.inject.Inject

class SearchSourceFactory @Inject constructor(
        private val searchRemote: SearchRemote) {

    fun getRemote(): SearchRemote = searchRemote
}