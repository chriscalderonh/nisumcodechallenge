package com.chriscalderonh.nisumcodechallenge.search.data

import com.chriscalderonh.nisumcodechallenge.search.data.remote.mapper.CollectionResponseMapper
import com.chriscalderonh.nisumcodechallenge.search.data.remote.mapper.SearchResponseMapper
import com.chriscalderonh.nisumcodechallenge.search.data.source.SearchSourceFactory
import com.chriscalderonh.nisumcodechallenge.search.domain.SearchRepository
import com.chriscalderonh.nisumcodechallenge.search.domain.model.DomainCollection
import com.chriscalderonh.nisumcodechallenge.search.domain.model.DomainSearch
import io.reactivex.Single
import javax.inject.Inject

class SearchDataRepository @Inject constructor(
    private val factory: SearchSourceFactory,
    private val searchResponseMapper: SearchResponseMapper,
    private val collectionResponseMapper: CollectionResponseMapper
) : SearchRepository {

    override fun getSearchResults(searchText: String, page: Int): Single<DomainSearch> = factory
        .getRemote()
        .getSearchResults(searchText, page)
        .map { searchResponse ->
            with(searchResponseMapper) {
                searchResponse.fromRemoteToDomain()
            }
        }

    override fun getCollectionResults(collectionId: String): Single<DomainCollection> = factory
        .getRemote()
        .getCollectionResults(collectionId)
        .map { collectionResponse ->
            with(collectionResponseMapper) {
                collectionResponse.fromRemoteToDomain()
            }
        }
}