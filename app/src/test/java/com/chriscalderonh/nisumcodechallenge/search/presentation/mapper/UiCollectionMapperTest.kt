package com.chriscalderonh.nisumcodechallenge.search.presentation.mapper

import com.chriscalderonh.nisumcodechallenge.search.factory.CollectionFactory.generateDomainCollection
import com.chriscalderonh.nisumcodechallenge.search.presentation.Constants
import org.junit.Assert.*
import org.junit.Test

class UiCollectionMapperTest {

    private val uiCollectionMapper = UiCollectionMapper()

    @Test
    fun `given DomainSearch, when fromDomainToUi, then UiSearch`() {
        val collectionDomain = generateDomainCollection()

        val uiCollection = with(uiCollectionMapper) {
            collectionDomain.fromDomainToUi()
        }

        assertEquals("resultCount", collectionDomain.resultCount, uiCollection.resultCount)
        collectionDomain.results?.filter { it.wrapperType == Constants.TRACK_TYPE }?.forEachIndexed { index, result ->
            assertEquals("artistName", result.artistName, uiCollection.tracks?.get(index)?.artistName)
            assertEquals("artworkUrl100", result.artworkUrl100, uiCollection.tracks?.get(index)?.artworkUrl100)
            assertEquals("collectionName", result.collectionName, uiCollection.tracks?.get(index)?.collectionName)
            assertEquals("trackName", result.trackName, uiCollection.tracks?.get(index)?.trackName)
            assertEquals("trackNumber", result.trackNumber, uiCollection.tracks?.get(index)?.trackNumber)
            assertEquals("artistName", result.trackViewUrl, uiCollection.tracks?.get(index)?.trackViewUrl)
        }
    }
}