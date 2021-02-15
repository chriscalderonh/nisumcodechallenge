package com.chriscalderonh.nisumcodechallenge.search.presentation.uistate

import com.chriscalderonh.nisumcodechallenge.common.presentation.MviUiState
import com.chriscalderonh.nisumcodechallenge.search.presentation.model.UiSearch

sealed class SearchUiState(val isLoading: Boolean = false,
                           val search: UiSearch? = null,
                           val error: String? = null): MviUiState {

    object Default: SearchUiState()

    object Loading: SearchUiState(
        isLoading = true
    )

    data class SuccessGettingSearchResults(val uiSearch: UiSearch): SearchUiState(
        search = uiSearch
    )

    data class ErrorGettingSearchResults(val message: String?): SearchUiState(
        error = message ?: DEFAULT_ERROR_MSG
    )

    companion object {
        const val DEFAULT_ERROR_MSG = ""
    }
}