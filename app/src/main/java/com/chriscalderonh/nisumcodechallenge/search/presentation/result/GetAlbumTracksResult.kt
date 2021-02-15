package com.chriscalderonh.nisumcodechallenge.search.presentation.result

import com.chriscalderonh.nisumcodechallenge.search.domain.model.DomainCollection

sealed class GetAlbumTracksResult {

    sealed class GetListOfTracksResult: GetAlbumTracksResult() {
        object InProgress: GetListOfTracksResult()
        data class Success(val domainCollection: DomainCollection): GetListOfTracksResult()
        data class Error(val error: Throwable): GetListOfTracksResult()
    }
}