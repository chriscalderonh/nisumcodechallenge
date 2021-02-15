package com.chriscalderonh.nisumcodechallenge.search.presentation.result

import com.chriscalderonh.nisumcodechallenge.search.domain.model.DomainSearch

sealed class SearchMusicResult {

    sealed class GetSearchResultsMusicResult: SearchMusicResult() {
        object InProgress: GetSearchResultsMusicResult()
        data class Success(val domainSearch: DomainSearch): GetSearchResultsMusicResult()
        data class Error(val error: Throwable): GetSearchResultsMusicResult()
    }
}