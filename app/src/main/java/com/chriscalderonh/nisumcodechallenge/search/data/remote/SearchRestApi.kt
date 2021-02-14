package com.chriscalderonh.nisumcodechallenge.search.data.remote

import com.chriscalderonh.nisumcodechallenge.search.data.remote.model.SearchResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface SearchRestApi {

    @GET("search?term={searchText}&media=music&limit=20&offset={page}")
    fun searchMusic(@Path("searchText") searchText: String,
                    @Path("page") page: Int): Single<SearchResponse>
}