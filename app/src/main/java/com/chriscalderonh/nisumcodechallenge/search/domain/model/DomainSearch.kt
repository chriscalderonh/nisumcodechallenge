package com.chriscalderonh.nisumcodechallenge.search.domain.model

data class DomainSearch(
        val resultCount: Int?,
        val results: List<DomainResult>?
)

data class DomainResult(
        val artistName: String?,
        val trackName: String?,
        val collectionName: String?,
        val artworkUrl100: String?,
        val collectionId: Int?,
        val shortDescription: String?,
        val wrapperType: String?
)