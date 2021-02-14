package com.chriscalderonh.nisumcodechallenge.search.data.source

import com.chriscalderonh.nisumcodechallenge.search.data.repository.SearchRemote
import com.nhaarman.mockitokotlin2.mock
import org.junit.Assert.*
import org.junit.Test

class SearchSourceFactoryTest {
    private val remote = mock<SearchRemote>()
    private val factory = SearchSourceFactory(remote)

    @Test
    fun `when getRemote, then not null`() {
        assertNotNull(factory.getRemote())
    }
}