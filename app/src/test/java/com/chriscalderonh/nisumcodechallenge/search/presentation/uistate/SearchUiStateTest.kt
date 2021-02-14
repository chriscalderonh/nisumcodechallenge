package com.chriscalderonh.nisumcodechallenge.search.presentation.uistate

import com.chriscalderonh.nisumcodechallenge.base.RandomValuesFactory.generateString
import com.chriscalderonh.nisumcodechallenge.search.factory.SearchFactory.generateUiSearch
import org.junit.Assert.*
import org.junit.Test

class SearchUiStateTest {

    @Test
    fun `given Default state, then default values`() {
        val uiState = SearchUiState.Default

        assertEquals("isLoading", false, uiState.isLoading)
        assertEquals("transferBetweenProducts", null, uiState.search)
        assertEquals("error", null, uiState.error)
    }

    @Test
    fun `given Loading state, then isLoading true`() {
        val uiState = SearchUiState.Loading

        assertEquals("isLoading", true, uiState.isLoading)
        assertEquals("transferBetweenProducts", null, uiState.search)
        assertEquals("error", null, uiState.error)
    }

    @Test
    fun `given Success state, then transferBetweenProducts with data`() {
        val uiState = SearchUiState.SuccessGettingSearchResults(generateUiSearch())

        assertEquals("isLoading", false, uiState.isLoading)
        assertNotNull("transferBetweenProducts", uiState.search)
        assertEquals("error", null, uiState.error)
    }

    @Test
    fun `given Error state with message, then error with message`() {
        val errorMessage = generateString()
        val uiState = SearchUiState.ErrorGettingSearchResults(errorMessage)

        assertEquals("isLoading", false, uiState.isLoading)
        assertEquals("search", null, uiState.search)
        assertEquals("error", errorMessage, uiState.error)
    }
}