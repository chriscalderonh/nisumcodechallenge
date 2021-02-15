package com.chriscalderonh.nisumcodechallenge.search.factory

import com.chriscalderonh.nisumcodechallenge.base.RandomValuesFactory
import com.chriscalderonh.nisumcodechallenge.search.data.remote.model.CollectionResponse
import com.chriscalderonh.nisumcodechallenge.search.data.remote.model.CollectionResult
import com.chriscalderonh.nisumcodechallenge.search.domain.model.DomainCollection
import com.chriscalderonh.nisumcodechallenge.search.domain.model.DomainCollectionResult

object CollectionFactory {

    fun generateCollectionResponse() = CollectionResponse(
        RandomValuesFactory.generateInt(),
        generateCollectionResultList())

    private fun generateCollectionResultList() = (0..10).map { generateCollectionResult() }

    private fun generateCollectionResult() = CollectionResult(
        RandomValuesFactory.generateInt(),
        RandomValuesFactory.generateInt(),
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
        RandomValuesFactory.generateString(),
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
        RandomValuesFactory.generateInt(),
        RandomValuesFactory.generateString(),
        RandomValuesFactory.generateInt(),
        RandomValuesFactory.generateString(),
        RandomValuesFactory.generateInt(),
        RandomValuesFactory.generateDouble(),
        RandomValuesFactory.generateInt(),
        RandomValuesFactory.generateString(),
        RandomValuesFactory.generateString())

    fun generateDomainCollection() = DomainCollection(
        RandomValuesFactory.generateInt(),
        generateDomainCollectionResultList())

    private fun generateDomainCollectionResultList() = (0..10).map { generateDomainCollectionResult() }

    private fun generateDomainCollectionResult() = DomainCollectionResult(
        RandomValuesFactory.generateString(),
        RandomValuesFactory.generateString(),
        RandomValuesFactory.generateString(),
        RandomValuesFactory.generateString(),
        RandomValuesFactory.generateInt(),
        RandomValuesFactory.generateString(),
        RandomValuesFactory.generateString())

}