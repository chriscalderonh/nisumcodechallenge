package com.chriscalderonh.nisumcodechallenge.search.data.remote

import org.junit.Assert.*
import org.junit.Test

class SearchRestApiFactoryTest {

    private val restApiFactory = SearchRestApiFactory()

    @Test
    fun `when MakeRestApi, then returns NotNull`() {
        assertNotNull(restApiFactory.makeRestApi())
    }
}