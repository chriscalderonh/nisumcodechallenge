package com.chriscalderonh.nisumcodechallenge.search.presentation.processor

import com.chriscalderonh.nisumcodechallenge.common.presentation.execution.ExecutionThread
import com.chriscalderonh.nisumcodechallenge.search.domain.SearchMusicRemoteUseCase
import com.chriscalderonh.nisumcodechallenge.search.presentation.action.SearchMusicAction
import com.chriscalderonh.nisumcodechallenge.search.presentation.action.SearchMusicAction.GetSearchResultsMusicAction
import com.chriscalderonh.nisumcodechallenge.search.presentation.result.SearchMusicResult
import com.chriscalderonh.nisumcodechallenge.search.presentation.result.SearchMusicResult.GetSearchResultsMusicResult
import io.reactivex.ObservableTransformer
import javax.inject.Inject

class SearchProcessor @Inject constructor(
    private val searchMusicRemoteUseCase: SearchMusicRemoteUseCase,
    private val executionThread: ExecutionThread
) {

    var actionProcessor: ObservableTransformer<SearchMusicAction, SearchMusicResult>
        private set

    init {
        actionProcessor = ObservableTransformer { observableAction ->
            observableAction.publish { action ->
                action.ofType(GetSearchResultsMusicAction::class.java)
                    .compose(getSearchResultsProcessor)
                    .cast(SearchMusicResult::class.java)
            }
        }
    }

    private val getSearchResultsProcessor =
        ObservableTransformer<GetSearchResultsMusicAction, GetSearchResultsMusicResult> { observableAction ->
            observableAction.switchMap { action ->
                searchMusicRemoteUseCase.execute(action.text, action.page)
                    .toObservable()
                    .map { result ->
                        GetSearchResultsMusicResult.Success(result)
                    }
                    .cast(GetSearchResultsMusicResult::class.java)
                    .onErrorReturn(GetSearchResultsMusicResult::Error)
                    .subscribeOn(executionThread.schedulerForSubscribing())
                    .observeOn(executionThread.schedulerForObserving())
                    .startWith(GetSearchResultsMusicResult.InProgress)
            }
        }
}