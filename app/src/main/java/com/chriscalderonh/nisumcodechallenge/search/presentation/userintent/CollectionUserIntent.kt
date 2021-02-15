package com.chriscalderonh.nisumcodechallenge.search.presentation.userintent

import com.chriscalderonh.nisumcodechallenge.common.presentation.MviUserIntent

sealed class CollectionUserIntent : MviUserIntent {

    data class InitialUserIntent(val collectionId: String): CollectionUserIntent()
}