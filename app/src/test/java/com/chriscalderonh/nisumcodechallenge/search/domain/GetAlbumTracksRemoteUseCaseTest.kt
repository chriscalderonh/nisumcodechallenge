package com.chriscalderonh.nisumcodechallenge.search.domain

import com.chriscalderonh.nisumcodechallenge.base.RandomValuesFactory
import com.chriscalderonh.nisumcodechallenge.search.domain.model.DomainCollection
import com.chriscalderonh.nisumcodechallenge.search.factory.CollectionFactory.generateDomainCollection
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.Assert.*
import org.junit.Test

class GetAlbumTracksRemoteUseCaseTest {
    private val repository = mock<SearchRepository>()
    private val getAlbumTracksRemoteUseCase = GetAlbumTracksRemoteUseCase(repository)

    @Test
    fun `given SearchResponse, when execute, then returns data`() {
        val id = RandomValuesFactory.generateString()
        val domainCollection = generateDomainCollection()
        stubGetCollectionResults(id, Single.just(domainCollection))

        val testObserver = getAlbumTracksRemoteUseCase.execute(id).test()

        testObserver.assertValue(domainCollection)
    }

    private fun stubGetCollectionResults(id: String, response: Single<DomainCollection>) {
        whenever(repository.getCollectionResults(id)).thenReturn(response)
    }
}