package com.chriscalderonh.nisumcodechallenge.search.presentation.mapper

import com.chriscalderonh.nisumcodechallenge.search.domain.model.DomainCollection
import com.chriscalderonh.nisumcodechallenge.search.domain.model.DomainCollectionResult
import com.chriscalderonh.nisumcodechallenge.search.presentation.Constants
import com.chriscalderonh.nisumcodechallenge.search.presentation.Constants.COLLECTION_TYPE
import com.chriscalderonh.nisumcodechallenge.search.presentation.Constants.DEFAULT_INT_VALUE
import com.chriscalderonh.nisumcodechallenge.search.presentation.Constants.TRACK_TYPE
import com.chriscalderonh.nisumcodechallenge.search.presentation.model.UiCollection
import com.chriscalderonh.nisumcodechallenge.search.presentation.model.UiCollectionResult
import javax.inject.Inject

class UiCollectionMapper @Inject constructor() {
    fun DomainCollection.fromDomainToUi() = UiCollection(
        resultCount = resultCount ?: DEFAULT_INT_VALUE,
        collection = results?.fromDomainToUi()?.firstOrNull { it.wrapperType == COLLECTION_TYPE },
        tracks = results?.fromDomainToUi()?.filter { it.wrapperType == TRACK_TYPE }
    )

    private fun List<DomainCollectionResult>.fromDomainToUi(): List<UiCollectionResult> {
        return map { it.fromDomainToUi() }
    }

    private fun DomainCollectionResult.fromDomainToUi() = UiCollectionResult(
        artistName = artistName.orEmpty(),
        artworkUrl100 = artworkUrl100.orEmpty(),
        collectionName = collectionName.orEmpty(),
        trackName = trackName.orEmpty(),
        trackNumber = trackNumber ?: DEFAULT_INT_VALUE,
        trackViewUrl = trackViewUrl.orEmpty(),
        wrapperType = wrapperType.orEmpty()
    )
}