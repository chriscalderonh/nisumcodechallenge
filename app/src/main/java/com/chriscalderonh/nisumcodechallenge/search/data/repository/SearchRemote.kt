package com.chriscalderonh.nisumcodechallenge.search.data.repository

import com.chriscalderonh.nisumcodechallenge.search.data.remote.model.CollectionResponse
import com.chriscalderonh.nisumcodechallenge.search.data.remote.model.SearchResponse
import io.reactivex.Single

interface SearchRemote {

    fun getSearchResults(searchText: String, page: Int): Single<SearchResponse>

    fun getCollectionResults(collectionId: String): Single<CollectionResponse>
}