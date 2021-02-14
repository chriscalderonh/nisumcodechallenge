package com.chriscalderonh.nisumcodechallenge.search.presentation.model

data class UiSearch(
    val resultCount: Int,
    val results: List<UiResult>?
)

data class UiResult(
    val artistName: String,
    val trackName: String,
    val collectionName: String,
    val artworkUrl100: String,
    val collectionId: Int,
    val shortDescription: String,
    val wrapperType: String
)
