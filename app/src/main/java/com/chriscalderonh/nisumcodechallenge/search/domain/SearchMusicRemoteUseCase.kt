package com.chriscalderonh.nisumcodechallenge.search.domain

import javax.inject.Inject

class SearchMusicRemoteUseCase @Inject constructor(private val repository: SearchRepository) {

    fun execute(searchText: String, page: Int) = repository.getSearchResults(searchText, page)
}