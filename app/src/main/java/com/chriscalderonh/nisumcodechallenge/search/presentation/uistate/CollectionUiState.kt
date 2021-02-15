package com.chriscalderonh.nisumcodechallenge.search.presentation.uistate

import com.chriscalderonh.nisumcodechallenge.common.presentation.MviUiState
import com.chriscalderonh.nisumcodechallenge.search.presentation.model.UiCollection

sealed class CollectionUiState(val isLoading: Boolean = false,
                               val collection: UiCollection? = null,
                               val error: String? = null): MviUiState {

    object Default: CollectionUiState()

    object Loading: CollectionUiState(
        isLoading = true
    )

    data class SuccessGettingAlbumResults(val uiCollection: UiCollection): CollectionUiState(
        collection = uiCollection
    )

    data class ErrorGettingAlbumResults(val message: String?): CollectionUiState(
        error = message ?: DEFAULT_ERROR_MSG
    )

    companion object {
        const val DEFAULT_ERROR_MSG = ""
    }
}