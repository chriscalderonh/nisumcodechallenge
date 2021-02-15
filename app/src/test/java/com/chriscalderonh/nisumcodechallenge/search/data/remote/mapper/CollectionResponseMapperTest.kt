package com.chriscalderonh.nisumcodechallenge.search.data.remote.mapper

import com.chriscalderonh.nisumcodechallenge.search.factory.CollectionFactory.generateCollectionResponse
import org.junit.Assert.*
import org.junit.Test

class CollectionResponseMapperTest {
    private val collectionResponseMapper = CollectionResponseMapper()

    @Test
    fun `given SearchResponse, when fromRemoteToDomain, then DomainSearch`() {
        val collectionResponse = generateCollectionResponse()

        val domainSearch = with(collectionResponseMapper) {
            collectionResponse.fromRemoteToDomain()
        }

        assertEquals("resultCount", collectionResponse.resultCount, domainSearch.resultCount)
        collectionResponse.results?.forEachIndexed { index, result ->
            assertEquals("artistName", result.artistName, domainSearch.results?.get(index)?.artistName)
            assertEquals("artworkUrl100", result.artworkUrl100, domainSearch.results?.get(index)?.artworkUrl100)
            assertEquals("collectionName", result.collectionName, domainSearch.results?.get(index)?.collectionName)
            assertEquals("trackName", result.trackName, domainSearch.results?.get(index)?.trackName)
            assertEquals("trackNumber", result.trackNumber, domainSearch.results?.get(index)?.trackNumber)
            assertEquals("trackViewUrl", result.trackViewUrl, domainSearch.results?.get(index)?.trackViewUrl)
        }
    }
}