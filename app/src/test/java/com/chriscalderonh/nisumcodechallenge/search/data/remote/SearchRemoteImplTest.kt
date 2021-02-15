package com.chriscalderonh.nisumcodechallenge.search.data.remote

import com.chriscalderonh.nisumcodechallenge.base.RandomValuesFactory.generateInt
import com.chriscalderonh.nisumcodechallenge.base.RandomValuesFactory.generateString
import com.chriscalderonh.nisumcodechallenge.search.data.remote.model.CollectionResponse
import com.chriscalderonh.nisumcodechallenge.search.data.remote.model.SearchResponse
import com.chriscalderonh.nisumcodechallenge.search.factory.CollectionFactory.generateCollectionResponse
import com.chriscalderonh.nisumcodechallenge.search.factory.SearchFactory.generateSearchResponse
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.Test

class SearchRemoteImplTest {

    private val restApi = mock<SearchRestApi>()
    private val searchRemoteImpl = SearchRemoteImpl(restApi)

    @Test
    fun `given search parameters, when getSearchResults, then returns data`() {
        val text = generateString()
        val page = generateInt()
        val searchResponse = generateSearchResponse()
        stubRestApiGetSearchResults(text, page, Single.just(searchResponse))

        val testObserver = searchRemoteImpl.getSearchResults(text, page).test()

        testObserver.assertValue(searchResponse)
    }

    @Test
    fun `given collectionId, when getCollectionResults, then returns data`() {
        val collectionId = generateString()
        val collectionResponse = generateCollectionResponse()
        stubRestApiGetCollectionResults(collectionId, Single.just(collectionResponse))

        val testObserver = searchRemoteImpl.getCollectionResults(collectionId).test()

        testObserver.assertValue(collectionResponse)
    }

    private fun stubRestApiGetSearchResults(text: String, page: Int, response: Single<SearchResponse>) {
        whenever(restApi.searchMusic(text, page)).thenReturn(response)
    }

    private fun stubRestApiGetCollectionResults(collectionId: String, response: Single<CollectionResponse>) {
        whenever(restApi.getCollection(collectionId)).thenReturn(response)
    }
}