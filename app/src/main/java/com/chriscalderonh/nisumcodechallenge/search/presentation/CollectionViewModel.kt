package com.chriscalderonh.nisumcodechallenge.search.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chriscalderonh.nisumcodechallenge.common.presentation.MviPresentation
import com.chriscalderonh.nisumcodechallenge.search.presentation.action.GetAlbumTracksAction
import com.chriscalderonh.nisumcodechallenge.search.presentation.mapper.UiCollectionMapper
import com.chriscalderonh.nisumcodechallenge.search.presentation.processor.CollectionProcessor
import com.chriscalderonh.nisumcodechallenge.search.presentation.result.GetAlbumTracksResult
import com.chriscalderonh.nisumcodechallenge.search.presentation.result.GetAlbumTracksResult.GetListOfTracksResult
import com.chriscalderonh.nisumcodechallenge.search.presentation.uistate.CollectionUiState
import com.chriscalderonh.nisumcodechallenge.search.presentation.userintent.CollectionUserIntent
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.BiFunction
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class CollectionViewModel @Inject constructor(
    private val collectionProcessor: CollectionProcessor,
    private val uiCollectionMapper: UiCollectionMapper
) : ViewModel(), MviPresentation<CollectionUserIntent, CollectionUiState> {

    private val userIntentSubject: PublishSubject<CollectionUserIntent> = PublishSubject.create()
    private val disposable = CompositeDisposable()
    private val liveDataUiState = MutableLiveData<CollectionUiState>()
    private val uiStatesObservable: Observable<CollectionUiState> = compose()

    init {
        disposable += uiStatesObservable.subscribe(liveDataUiState::setValue) { }
    }

    private fun compose(): Observable<CollectionUiState> = userIntentSubject
        .map { userIntent -> transformUserIntentsIntoActions(userIntent) }
        .compose(collectionProcessor.actionProcessor)
        .scan(CollectionUiState.Default, reducer)

    private val reducer: BiFunction<CollectionUiState, GetAlbumTracksResult, CollectionUiState>
        get() = BiFunction { _: CollectionUiState, result: GetAlbumTracksResult ->
            when (result) {
                is GetListOfTracksResult -> when (result) {
                    GetListOfTracksResult.InProgress -> CollectionUiState.Loading
                    is GetListOfTracksResult.Success ->
                        CollectionUiState.SuccessGettingAlbumResults(
                            with(uiCollectionMapper) { result.domainCollection.fromDomainToUi() })
                    is GetListOfTracksResult.Error ->
                        CollectionUiState.ErrorGettingAlbumResults(result.error.message)
                }
            }
        }

    private fun transformUserIntentsIntoActions(userIntent: CollectionUserIntent): GetAlbumTracksAction =
        when (userIntent) {
            is CollectionUserIntent.InitialUserIntent ->
                GetAlbumTracksAction.GetListOfTracksAction(userIntent.collectionId)
        }

    override fun processUserIntents(userIntents: Observable<CollectionUserIntent>) {
        userIntents.subscribe(userIntentSubject)
    }

    override fun uiStates(): Observable<CollectionUiState> = uiStatesObservable

    fun liveData(): LiveData<CollectionUiState> = liveDataUiState

    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }
}