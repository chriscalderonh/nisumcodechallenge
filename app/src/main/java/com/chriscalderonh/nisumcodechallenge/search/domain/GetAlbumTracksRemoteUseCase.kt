package com.chriscalderonh.nisumcodechallenge.search.domain

import javax.inject.Inject

class GetAlbumTracksRemoteUseCase @Inject constructor(private val repository: SearchRepository) {

    fun execute(collectionId: String) = repository.getCollectionResults(collectionId)
}