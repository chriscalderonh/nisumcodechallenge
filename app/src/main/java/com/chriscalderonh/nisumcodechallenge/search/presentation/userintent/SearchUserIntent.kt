package com.chriscalderonh.nisumcodechallenge.search.presentation.userintent

import com.chriscalderonh.nisumcodechallenge.common.presentation.MviUserIntent

sealed class SearchUserIntent : MviUserIntent {

    data class InvokingSearchUserIntent(val text: String, val page: Int): SearchUserIntent()

    data class ClickOnSearchItemUserIntent(val collectionId: String): SearchUserIntent()
}