package com.chriscalderonh.nisumcodechallenge.search.domain.model

data class DomainCollection(
    val resultCount: Int?,
    val results: List<DomainCollectionResult>?
)

data class DomainCollectionResult(
    val artistName: String?,
    val artworkUrl100: String?,
    val collectionName: String?,
    val trackName: String?,
    val trackNumber: Int?,
    val trackViewUrl: String?,
    val wrapperType: String?
)