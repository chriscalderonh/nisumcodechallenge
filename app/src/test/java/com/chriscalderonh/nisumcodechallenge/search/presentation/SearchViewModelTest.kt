package com.chriscalderonh.nisumcodechallenge.search.presentation

import com.chriscalderonh.nisumcodechallenge.base.RandomValuesFactory.generateInt
import com.chriscalderonh.nisumcodechallenge.base.RandomValuesFactory.generateString
import com.chriscalderonh.nisumcodechallenge.common.presentation.execution.FakeExecutionThread
import com.chriscalderonh.nisumcodechallenge.search.domain.SearchMusicRemoteUseCase
import com.chriscalderonh.nisumcodechallenge.search.domain.model.DomainSearch
import com.chriscalderonh.nisumcodechallenge.search.factory.SearchFactory.generateDomainSearch
import com.chriscalderonh.nisumcodechallenge.search.factory.SearchFactory.generateUiSearch
import com.chriscalderonh.nisumcodechallenge.search.presentation.mapper.UiSearchMapper
import com.chriscalderonh.nisumcodechallenge.search.presentation.model.UiSearch
import com.chriscalderonh.nisumcodechallenge.search.presentation.processor.SearchProcessor
import com.chriscalderonh.nisumcodechallenge.search.presentation.uistate.SearchUiState
import com.chriscalderonh.nisumcodechallenge.search.presentation.userintent.SearchUserIntent
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.junit.After
import org.junit.Before
import org.junit.Test

class SearchViewModelTest {

    private val fakeExecutionThread = FakeExecutionThread()
    private val searchMusicRemoteUseCase = mock<SearchMusicRemoteUseCase>()
    private val processor = SearchProcessor(searchMusicRemoteUseCase, fakeExecutionThread)
    private val uiSearchMapper = mock<UiSearchMapper>()
    private val viewModel = SearchViewModel(processor, uiSearchMapper)
    private lateinit var testObserver: TestObserver<SearchUiState>

    @Before
    fun setUp() {
        testObserver = viewModel.uiStates().test()
    }

    @After
    fun tearDown() {
        testObserver.dispose()
    }

    @Test
    fun `given search result from SearchMusicRemoteUseCase, when InvokingSearchUserIntent then SuccessGettingSearchResults`() {
        val domainSearch = generateDomainSearch()
        val uiSearch = generateUiSearch()
        val text = generateString()
        val page = generateInt()
        stubUiSearchMapper(domainSearch, uiSearch)
        stubSearchMusicRemoteUseCase(text, page, Single.just(domainSearch))

        viewModel.processUserIntents(
            Observable.just(SearchUserIntent.InvokingSearchUserIntent(text, page)))

        testObserver.assertValueAt(0) { uiState -> uiState is SearchUiState.Default }
        testObserver.assertValueAt(1) { uiState -> uiState is SearchUiState.Loading }
        testObserver.assertValueAt(2) { uiState -> uiState is SearchUiState.SuccessGettingSearchResults }
        testObserver.assertValueAt(2) { uiState ->
            uiState.search == with(uiSearchMapper) {
                domainSearch.fromDomainToUi()
            }
        }
    }
    @Test
    fun `given error from SearchMusicRemoteUseCase, when InvokingSearchUserIntent then ErrorGettingSearchResults`() {
        val text = generateString()
        val page = generateInt()
        val errorMessage = generateString()
        stubSearchMusicRemoteUseCase(text, page, Single.error(Throwable()))

        viewModel.processUserIntents(
            Observable.just(SearchUserIntent.InvokingSearchUserIntent(text, page)))

        testObserver.assertValueAt(0) { uiState -> uiState is SearchUiState.Default }
        testObserver.assertValueAt(1) { uiState -> uiState is SearchUiState.Loading }
        testObserver.assertValueAt(2) { uiState -> uiState is SearchUiState.ErrorGettingSearchResults }
    }

    private fun stubSearchMusicRemoteUseCase(text: String, page: Int, response: Single<DomainSearch>) {
        whenever(searchMusicRemoteUseCase.execute(text, page)).thenReturn(response)
    }

    private fun stubUiSearchMapper(domain: DomainSearch, ui: UiSearch) {
        whenever(with(uiSearchMapper) { domain.fromDomainToUi() }).thenReturn(ui)
    }
}