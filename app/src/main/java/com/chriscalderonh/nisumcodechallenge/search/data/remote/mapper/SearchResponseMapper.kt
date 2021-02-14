package com.chriscalderonh.nisumcodechallenge.search.data.remote.mapper

import com.chriscalderonh.nisumcodechallenge.search.data.remote.model.SearchResponse
import com.chriscalderonh.nisumcodechallenge.search.data.remote.model.SearchResult
import com.chriscalderonh.nisumcodechallenge.search.domain.model.DomainResult
import com.chriscalderonh.nisumcodechallenge.search.domain.model.DomainSearch
import javax.inject.Inject

class SearchResponseMapper @Inject constructor() {

    fun SearchResponse.fromRemoteToDomain() = DomainSearch(
            resultCount = resultCount,
            results = results?.fromRemoteToDomain()
    )

    private fun List<SearchResult>.fromRemoteToDomain(): List<DomainResult> {
        return map { it.fromRemoteToDomain() }
    }

    private fun SearchResult.fromRemoteToDomain() = DomainResult(
            artistName = artistName,
            trackName = trackName,
            collectionName = collectionName,
            artworkUrl100 = artworkUrl100,
            collectionId = collectionId,
            shortDescription = shortDescription,
            wrapperType = wrapperType
    )
}