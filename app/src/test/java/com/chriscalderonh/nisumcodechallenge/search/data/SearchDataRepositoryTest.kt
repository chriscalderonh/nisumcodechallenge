package com.chriscalderonh.nisumcodechallenge.search.data

import com.chriscalderonh.nisumcodechallenge.base.RandomValuesFactory
import com.chriscalderonh.nisumcodechallenge.search.data.remote.mapper.CollectionResponseMapper
import com.chriscalderonh.nisumcodechallenge.search.data.remote.mapper.SearchResponseMapper
import com.chriscalderonh.nisumcodechallenge.search.data.remote.model.CollectionResponse
import com.chriscalderonh.nisumcodechallenge.search.data.remote.model.SearchResponse
import com.chriscalderonh.nisumcodechallenge.search.data.repository.SearchRemote
import com.chriscalderonh.nisumcodechallenge.search.data.source.SearchSourceFactory
import com.chriscalderonh.nisumcodechallenge.search.domain.model.DomainCollection
import com.chriscalderonh.nisumcodechallenge.search.domain.model.DomainSearch
import com.chriscalderonh.nisumcodechallenge.search.factory.CollectionFactory.generateCollectionResponse
import com.chriscalderonh.nisumcodechallenge.search.factory.CollectionFactory.generateDomainCollection
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
    private val searchMapper = mock<SearchResponseMapper>()
    private val collectionMapper = mock<CollectionResponseMapper>()
    private val searchDataRepository = SearchDataRepository(factory, searchMapper, collectionMapper)

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
        stubSearchMapper(searchResponse, domainSearch)
        stubGetSearchResults(text, page, Single.just(searchResponse))

        val testObserver = searchDataRepository.getSearchResults(text, page).test()

        testObserver.assertValue(domainSearch)
    }

    @Test
    fun `given collectionId, when getCollectionResults, then returns data`() {
        val collectionId = RandomValuesFactory.generateString()
        val collectionResponse = generateCollectionResponse()
        val domainCollection = generateDomainCollection()
        stubCollectionMapper(collectionResponse, domainCollection)
        stubGetCollectionResults(collectionId, Single.just(collectionResponse))

        val testObserver = searchDataRepository.getCollectionResults(collectionId).test()

        testObserver.assertValue(domainCollection)
    }

    private fun stubGetSearchResults(text: String, page: Int, response: Single<SearchResponse>) {
        whenever(remote.getSearchResults(text, page)).thenReturn(response)
    }

    private fun stubGetCollectionResults(collectionId: String, response: Single<CollectionResponse>) {
        whenever(remote.getCollectionResults(collectionId)).thenReturn(response)
    }

    private fun stubSearchMapper(remote: SearchResponse, domain: DomainSearch) {
        whenever(with(searchMapper) { remote.fromRemoteToDomain() }).thenReturn(domain)
    }

    private fun stubCollectionMapper(remote: CollectionResponse, domain: DomainCollection) {
        whenever(with(collectionMapper) { remote.fromRemoteToDomain() }).thenReturn(domain)
    }
}