package com.chriscalderonh.nisumcodechallenge.common.presentation

import io.reactivex.Observable

interface MviPresentation<TUserIntent: MviUserIntent, TUiState: MviUiState> {

    fun processUserIntents(userIntents: Observable<TUserIntent>)

    fun uiStates(): Observable<TUiState>

}