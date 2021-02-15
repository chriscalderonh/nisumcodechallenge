package com.chriscalderonh.nisumcodechallenge.search.presentation.model

data class UiCollection(
    val resultCount: Int?,
    val collection: UiCollectionResult?,
    val tracks: List<UiCollectionResult>?
)

data class UiCollectionResult(
    val artistName: String,
    val artworkUrl100: String,
    val collectionName: String,
    val trackName: String,
    val trackNumber: Int,
    val trackViewUrl: String,
    val wrapperType: String
)
