package com.chriscalderonh.nisumcodechallenge.search.data.remote

import com.chriscalderonh.nisumcodechallenge.base.RandomValuesFactory.generateInt
import com.chriscalderonh.nisumcodechallenge.base.RandomValuesFactory.generateString
import com.chriscalderonh.nisumcodechallenge.search.data.remote.model.SearchResponse
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

    private fun stubRestApiGetSearchResults(text: String, page: Int, response: Single<SearchResponse>) {
        whenever(restApi.searchMusic(text, page)).thenReturn(response)
    }
}