package com.chriscalderonh.nisumcodechallenge.search.data

import com.chriscalderonh.nisumcodechallenge.base.RandomValuesFactory
import com.chriscalderonh.nisumcodechallenge.search.data.remote.mapper.SearchResponseMapper
import com.chriscalderonh.nisumcodechallenge.search.data.remote.model.SearchResponse
import com.chriscalderonh.nisumcodechallenge.search.data.repository.SearchRemote
import com.chriscalderonh.nisumcodechallenge.search.data.source.SearchSourceFactory
import com.chriscalderonh.nisumcodechallenge.search.domain.model.DomainSearch
import com.chriscalderonh.nisumcodechallenge.search.factory.SearchFactory.generateDomainSearch
import com.chriscalderonh.nisumcodechallenge.search.factory.SearchFactory.generateSearchResponse
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

class SearchDataRepositoryTest {
    private val remote = mock<SearchRemote>()
    private val factory = mock<SearchSourceFactory>()
    private val mapper = mock<SearchResponseMapper>()
    private val searchDataRepository = SearchDataRepository(factory, mapper)

    @Before
    fun setUp() {
        stubFactoryGetRemote()
    }

    private fun stubFactoryGetRemote() {
        whenever(factory.getRemote()).thenReturn(remote)
    }

    @Test
    fun `given search parameters, when getSearchResults, then returns data`() {
        val text = RandomValuesFactory.generateString()
        val page = RandomValuesFactory.generateInt()
        val searchResponse = generateSearchResponse()
        val domainSearch = generateDomainSearch()
        stubMapper(searchResponse, domainSearch)
        stubGetSearchResults(text, page, Single.just(searchResponse))

        val testObserver = searchDataRepository.getSearchResults(text, page).test()

        testObserver.assertValue(domainSearch)
    }

    private fun stubGetSearchResults(text: String, page: Int, response: Single<SearchResponse>) {
        whenever(remote.getSearchResults(text, page)).thenReturn(response)
    }

    private fun stubMapper(remote: SearchResponse, domain: DomainSearch) {
        whenever(with(mapper) { remote.fromRemoteToDomain() }).thenReturn(domain)
    }
}