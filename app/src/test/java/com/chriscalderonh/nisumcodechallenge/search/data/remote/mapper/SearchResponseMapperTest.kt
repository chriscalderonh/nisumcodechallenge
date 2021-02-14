package com.chriscalderonh.nisumcodechallenge.search.data.remote.mapper

import com.chriscalderonh.nisumcodechallenge.search.factory.SearchFactory.generateSearchResponse
import org.junit.Assert.*
import org.junit.Test

class SearchResponseMapperTest() {
    private val searchResponseMapper = SearchResponseMapper()

    @Test
    fun `given SearchResponse, when fromRemoteToDomain, then DomainSearch`() {
        val searchResponse = generateSearchResponse()

        val domainSearch = with(searchResponseMapper) {
            searchResponse.fromRemoteToDomain()
        }

        assertEquals("resultCount", searchResponse.resultCount, domainSearch.resultCount)
        searchResponse.results?.forEachIndexed { index, result ->
            assertEquals("artistName", result.artistName, domainSearch.results?.get(index)?.artistName)
            assertEquals("trackName", result.trackName, domainSearch.results?.get(index)?.trackName)
            assertEquals("collectionName", result.collectionName, domainSearch.results?.get(index)?.collectionName)
            assertEquals("artworkUrl100", result.artworkUrl100, domainSearch.results?.get(index)?.artworkUrl100)
            assertEquals("artistName", result.collectionId, domainSearch.results?.get(index)?.collectionId)
            assertEquals("artistName", result.shortDescription, domainSearch.results?.get(index)?.shortDescription)
            assertEquals("artistName", result.wrapperType, domainSearch.results?.get(index)?.wrapperType)
        }
    }
}