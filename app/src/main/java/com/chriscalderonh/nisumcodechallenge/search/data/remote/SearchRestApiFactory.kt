package com.chriscalderonh.nisumcodechallenge.search.data.remote

import com.chriscalderonh.nisumcodechallenge.common.net.ApiClient

class SearchRestApiFactory : ApiClient<SearchRestApi>() {

    init {
        restAPI = SearchRestApi::class.java
        baseURL = "https://itunes.apple.com"
    }

    fun makeRestApi(): SearchRestApi = apiService
}