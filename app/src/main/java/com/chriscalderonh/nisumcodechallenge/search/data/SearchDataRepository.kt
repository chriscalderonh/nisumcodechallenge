package com.chriscalderonh.nisumcodechallenge.search.data

import com.chriscalderonh.nisumcodechallenge.search.data.remote.mapper.SearchResponseMapper
import com.chriscalderonh.nisumcodechallenge.search.data.source.SearchSourceFactory
import com.chriscalderonh.nisumcodechallenge.search.domain.SearchRepository
import com.chriscalderonh.nisumcodechallenge.search.domain.model.DomainSearch
import io.reactivex.Single
import javax.inject.Inject

class SearchDataRepository @Inject constructor(
        private val factory: SearchSourceFactory,
        private val searchResponseMapper: SearchResponseMapper) : SearchRepository {

    override fun getSearchResults(searchText: String, page: Int): Single<DomainSearch> = factory
            .getRemote()
            .getSearchResults(searchText, page)
            .map { searchResponse ->
                with(searchResponseMapper) {
                    searchResponse.fromRemoteToDomain()
                }
            }
}