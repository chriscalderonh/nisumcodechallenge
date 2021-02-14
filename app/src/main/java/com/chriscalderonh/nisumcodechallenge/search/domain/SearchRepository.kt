package com.chriscalderonh.nisumcodechallenge.search.domain

import com.chriscalderonh.nisumcodechallenge.search.domain.model.DomainSearch
import io.reactivex.Single

interface SearchRepository {

    fun getSearchResults(searchText: String, page: Int): Single<DomainSearch>
}