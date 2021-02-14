package com.chriscalderonh.nisumcodechallenge.search.factory

import com.chriscalderonh.nisumcodechallenge.base.RandomValuesFactory
import com.chriscalderonh.nisumcodechallenge.search.data.remote.model.SearchResponse
import com.chriscalderonh.nisumcodechallenge.search.data.remote.model.SearchResult
import com.chriscalderonh.nisumcodechallenge.search.domain.model.DomainResult
import com.chriscalderonh.nisumcodechallenge.search.domain.model.DomainSearch

object SearchFactory {

    fun generateSearchResponse() = SearchResponse(
            RandomValuesFactory.generateInt(),
            generateSearchResultList())

    private fun generateSearchResultList() = (0..10).map { generateSearchResult() }

    private fun generateSearchResult() = SearchResult(
            RandomValuesFactory.generateInt(),
            RandomValuesFactory.generateString(),
            RandomValuesFactory.generateString(),
            RandomValuesFactory.generateString(),
            RandomValuesFactory.generateString(),
            RandomValuesFactory.generateString(),
            RandomValuesFactory.generateString(),
            RandomValuesFactory.generateString(),
            RandomValuesFactory.generateDouble(),
            RandomValuesFactory.generateInt(),
            RandomValuesFactory.generateString(),
            RandomValuesFactory.generateDouble(),
            RandomValuesFactory.generateString(),
            RandomValuesFactory.generateString(),
            RandomValuesFactory.generateString(),
            RandomValuesFactory.generateString(),
            RandomValuesFactory.generateInt(),
            RandomValuesFactory.generateInt(),
            RandomValuesFactory.generateBoolean(),
            RandomValuesFactory.generateString(),
            RandomValuesFactory.generateString(),
            RandomValuesFactory.generateString(),
            RandomValuesFactory.generateString(),
            RandomValuesFactory.generateString(),
            RandomValuesFactory.generateString(),
            RandomValuesFactory.generateString(),
            RandomValuesFactory.generateInt(),
            RandomValuesFactory.generateString(),
            RandomValuesFactory.generateDouble(),
            RandomValuesFactory.generateInt(),
            RandomValuesFactory.generateString(),
            RandomValuesFactory.generateInt(),
            RandomValuesFactory.generateDouble(),
            RandomValuesFactory.generateInt(),
            RandomValuesFactory.generateString(),
            RandomValuesFactory.generateString())

    fun generateDomainSearch() = DomainSearch(
            RandomValuesFactory.generateInt(),
            generateDomainResultList())

    private fun generateDomainResultList() = (0..10).map { generateDomainResult() }

    private fun generateDomainResult() = DomainResult(
            RandomValuesFactory.generateString(),
            RandomValuesFactory.generateString(),
            RandomValuesFactory.generateString(),
            RandomValuesFactory.generateString(),
            RandomValuesFactory.generateInt(),
            RandomValuesFactory.generateString(),
            RandomValuesFactory.generateString())
}