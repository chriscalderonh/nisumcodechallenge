package com.chriscalderonh.nisumcodechallenge.search.presentation.processor

import com.chriscalderonh.nisumcodechallenge.common.presentation.execution.ExecutionThread
import com.chriscalderonh.nisumcodechallenge.search.domain.GetAlbumTracksRemoteUseCase
import com.chriscalderonh.nisumcodechallenge.search.presentation.action.GetAlbumTracksAction
import com.chriscalderonh.nisumcodechallenge.search.presentation.action.GetAlbumTracksAction.GetListOfTracksAction
import com.chriscalderonh.nisumcodechallenge.search.presentation.result.GetAlbumTracksResult
import com.chriscalderonh.nisumcodechallenge.search.presentation.result.GetAlbumTracksResult.GetListOfTracksResult
import io.reactivex.ObservableTransformer
import javax.inject.Inject

class CollectionProcessor @Inject constructor(
    private val getAlbumTracksRemoteUseCase: GetAlbumTracksRemoteUseCase,
    private val executionThread: ExecutionThread
) {

    var actionProcessor: ObservableTransformer<GetAlbumTracksAction, GetAlbumTracksResult>
        private set

    init {
        actionProcessor = ObservableTransformer { observableAction ->
            observableAction.publish { action ->
                action.ofType(GetListOfTracksAction::class.java)
                    .compose(getAlbumTracksResultsProcessor)
                    .cast(GetAlbumTracksResult::class.java)
            }
        }
    }

    private val getAlbumTracksResultsProcessor =
        ObservableTransformer<GetListOfTracksAction, GetListOfTracksResult> { observableAction ->
            observableAction.switchMap { action ->
                getAlbumTracksRemoteUseCase.execute(action.collectionId)
                    .toObservable()
                    .map { result ->
                        GetListOfTracksResult.Success(result)
                    }
                    .cast(GetListOfTracksResult::class.java)
                    .onErrorReturn(GetListOfTracksResult::Error)
                    .subscribeOn(executionThread.schedulerForSubscribing())
                    .observeOn(executionThread.schedulerForObserving())
                    .startWith(GetListOfTracksResult.InProgress)
            }
        }
}