package com.chriscalderonh.nisumcodechallenge.search.data.remote

import com.chriscalderonh.nisumcodechallenge.search.data.remote.model.CollectionResponse
import com.chriscalderonh.nisumcodechallenge.search.data.remote.model.SearchResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchRestApi {

    @GET("search?media=music&limit=20")
    fun searchMusic(@Query("term") searchText: String,
                    @Query("offset") page: Int): Single<SearchResponse>

    @GET("lookup?entity=song")
    fun getCollection(@Query("id") collectionId: String): Single<CollectionResponse>
}