package com.chriscalderonh.nisumcodechallenge.search.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chriscalderonh.nisumcodechallenge.common.presentation.MviPresentation
import com.chriscalderonh.nisumcodechallenge.search.presentation.action.SearchMusicAction
import com.chriscalderonh.nisumcodechallenge.search.presentation.action.SearchMusicAction.GetSearchResultsMusicAction
import com.chriscalderonh.nisumcodechallenge.search.presentation.mapper.UiSearchMapper
import com.chriscalderonh.nisumcodechallenge.search.presentation.processor.SearchProcessor
import com.chriscalderonh.nisumcodechallenge.search.presentation.result.SearchMusicResult
import com.chriscalderonh.nisumcodechallenge.search.presentation.result.SearchMusicResult.GetSearchResultsMusicResult
import com.chriscalderonh.nisumcodechallenge.search.presentation.uistate.SearchUiState
import com.chriscalderonh.nisumcodechallenge.search.presentation.userintent.SearchUserIntent
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.BiFunction
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val searchProcessor: SearchProcessor,
    private val uiSearchMapper: UiSearchMapper)
    : ViewModel(), MviPresentation<SearchUserIntent, SearchUiState> {

    private val userIntentSubject: PublishSubject<SearchUserIntent> = PublishSubject.create()
    private val disposable = CompositeDisposable()
    private val liveDataUiState = MutableLiveData<SearchUiState>()
    private val uiStatesObservable: Observable<SearchUiState> = compose()

    init {
        disposable += uiStatesObservable.subscribe(liveDataUiState::setValue) { }
    }

    private fun compose(): Observable<SearchUiState> = userIntentSubject
        .map { userIntent -> transformUserIntentsIntoActions(userIntent) }
        .compose(searchProcessor.actionProcessor)
        .scan(SearchUiState.Default, reducer)

    private val reducer: BiFunction<SearchUiState, SearchMusicResult, SearchUiState>
        get() = BiFunction { _: SearchUiState, result: SearchMusicResult ->
            when (result) {
                is GetSearchResultsMusicResult -> when (result) {
                    GetSearchResultsMusicResult.InProgress -> SearchUiState.Loading
                    is GetSearchResultsMusicResult.Success ->
                        SearchUiState.SuccessGettingSearchResults(
                            with(uiSearchMapper) { result.domainSearch.fromDomainToUi() })
                    is GetSearchResultsMusicResult.Error ->
                        SearchUiState.ErrorGettingSearchResults(result.error.message)
                }
            }
        }

    private fun transformUserIntentsIntoActions(userIntent: SearchUserIntent): SearchMusicAction =
        when (userIntent) {
            is SearchUserIntent.InvokingSearchUserIntent ->
                GetSearchResultsMusicAction(userIntent.text, userIntent.page)
            is SearchUserIntent.ClickOnSearchItemUserIntent ->
                TODO("Next Story")
        }

    override fun processUserIntents(userIntents: Observable<SearchUserIntent>) {
        userIntents.subscribe(userIntentSubject)
    }

    override fun uiStates(): Observable<SearchUiState> = uiStatesObservable

    fun liveData(): LiveData<SearchUiState> = liveDataUiState

    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }
}