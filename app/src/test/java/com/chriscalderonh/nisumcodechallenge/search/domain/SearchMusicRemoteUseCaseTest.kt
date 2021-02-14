package com.chriscalderonh.nisumcodechallenge.search.domain

import com.chriscalderonh.nisumcodechallenge.base.RandomValuesFactory
import com.chriscalderonh.nisumcodechallenge.search.domain.model.DomainSearch
import com.chriscalderonh.nisumcodechallenge.search.factory.SearchFactory
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.Test

class SearchMusicRemoteUseCaseTest {

    private val repository = mock<SearchRepository>()
    private val getCancelReissueCardsUseCase = SearchMusicRemoteUseCase(repository)

    @Test
    fun `given SearchResponse, when execute, then returns data`() {
        val text = RandomValuesFactory.generateString()
        val page = RandomValuesFactory.generateInt()
        val domainSearch = SearchFactory.generateDomainSearch()
        stubGetSearchResults(text, page, Single.just(domainSearch))

        val testObserver = getCancelReissueCardsUseCase.execute(text, page).test()

        testObserver.assertValue(domainSearch)
    }

    private fun stubGetSearchResults(text: String, page: Int, response: Single<DomainSearch>) {
        whenever(repository.getSearchResults(text, page)).thenReturn(response)
    }
}
