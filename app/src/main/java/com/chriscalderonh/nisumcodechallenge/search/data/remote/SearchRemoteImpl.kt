package com.chriscalderonh.nisumcodechallenge.search.data.remote

import com.chriscalderonh.nisumcodechallenge.search.data.remote.model.SearchResponse
import com.chriscalderonh.nisumcodechallenge.search.data.repository.SearchRemote
import io.reactivex.Single
import javax.inject.Inject

class SearchRemoteImpl @Inject constructor(private val restApi: SearchRestApi) : SearchRemote {

    override fun getSearchResults(searchText: String, page: Int): Single<SearchResponse> =
            restApi.searchMusic(searchText, page)
}