package com.chriscalderonh.nisumcodechallenge.search.presentation.mapper

import com.chriscalderonh.nisumcodechallenge.search.data.remote.mapper.SearchResponseMapper
import com.chriscalderonh.nisumcodechallenge.search.factory.SearchFactory
import com.chriscalderonh.nisumcodechallenge.search.factory.SearchFactory.generateDomainSearch
import org.junit.Assert.*
import org.junit.Test

class UiSearchMapperTest {

    private val uiSearchMapper = UiSearchMapper()

    @Test
    fun `given DomainSearch, when fromDomainToUi, then UiSearch`() {
        val searchDomain = generateDomainSearch()

        val uiSearch = with(uiSearchMapper) {
            searchDomain.fromDomainToUi()
        }

        assertEquals("resultCount", searchDomain.resultCount, uiSearch.resultCount)
        searchDomain.results?.forEachIndexed { index, result ->
            assertEquals("artistName", result.artistName, uiSearch.results?.get(index)?.artistName)
            assertEquals("trackName", result.trackName, uiSearch.results?.get(index)?.trackName)
            assertEquals("collectionName", result.collectionName, uiSearch.results?.get(index)?.collectionName)
            assertEquals("artworkUrl100", result.artworkUrl100, uiSearch.results?.get(index)?.artworkUrl100)
            assertEquals("artistName", result.collectionId, uiSearch.results?.get(index)?.collectionId)
            assertEquals("artistName", result.shortDescription, uiSearch.results?.get(index)?.shortDescription)
            assertEquals("artistName", result.wrapperType, uiSearch.results?.get(index)?.wrapperType)
        }
    }
}