package com.chriscalderonh.nisumcodechallenge.search.presentation.mapper

import com.chriscalderonh.nisumcodechallenge.search.domain.model.DomainResult
import com.chriscalderonh.nisumcodechallenge.search.domain.model.DomainSearch
import com.chriscalderonh.nisumcodechallenge.search.presentation.Constants.DEFAULT_INT_VALUE
import com.chriscalderonh.nisumcodechallenge.search.presentation.model.UiResult
import com.chriscalderonh.nisumcodechallenge.search.presentation.model.UiSearch
import javax.inject.Inject

class UiSearchMapper @Inject constructor() {

    fun DomainSearch.fromDomainToUi() = UiSearch(
        resultCount = resultCount ?: DEFAULT_INT_VALUE,
        results = results?.fromDomainToUi()
    )

    private fun List<DomainResult>.fromDomainToUi(): List<UiResult> {
        return map { it.fromDomainToUi() }
    }

    private fun DomainResult.fromDomainToUi() = UiResult(
        artistName = artistName.orEmpty(),
        trackName = trackName.orEmpty(),
        collectionName = collectionName.orEmpty(),
        artworkUrl100 = artworkUrl100.orEmpty(),
        collectionId = collectionId ?: DEFAULT_INT_VALUE,
        shortDescription = shortDescription.orEmpty(),
        wrapperType = wrapperType.orEmpty()
    )
}