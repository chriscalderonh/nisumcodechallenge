package com.chriscalderonh.nisumcodechallenge.search.data.remote.mapper

import com.chriscalderonh.nisumcodechallenge.search.data.remote.model.CollectionResponse
import com.chriscalderonh.nisumcodechallenge.search.data.remote.model.CollectionResult
import com.chriscalderonh.nisumcodechallenge.search.domain.model.DomainCollection
import com.chriscalderonh.nisumcodechallenge.search.domain.model.DomainCollectionResult
import javax.inject.Inject

class CollectionResponseMapper @Inject constructor() {
    fun CollectionResponse.fromRemoteToDomain() = DomainCollection(
        resultCount = resultCount,
        results = results?.fromRemoteToDomain()
    )

    private fun List<CollectionResult>.fromRemoteToDomain(): List<DomainCollectionResult> {
        return map { it.fromRemoteToDomain() }
    }

    private fun CollectionResult.fromRemoteToDomain() = DomainCollectionResult(
        artistName = artistName,
        artworkUrl100 = artworkUrl100,
        collectionName = collectionName,
        trackName = trackName,
        trackNumber = trackNumber,
        trackViewUrl = trackViewUrl,
        wrapperType = wrapperType
    )
}