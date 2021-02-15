package com.chriscalderonh.nisumcodechallenge.search.presentation.di

import android.content.Context
import com.chriscalderonh.nisumcodechallenge.search.data.remote.SearchRemoteImpl
import com.chriscalderonh.nisumcodechallenge.search.data.remote.SearchRestApi
import com.chriscalderonh.nisumcodechallenge.search.data.remote.SearchRestApiFactory
import com.chriscalderonh.nisumcodechallenge.search.data.repository.SearchRemote
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class RemoteModule {
    @Binds
    abstract fun bindSearchRemote(searchRemoteImpl: SearchRemoteImpl): SearchRemote

    @Module
    companion object {

        @Provides
        @JvmStatic
        fun provideSearchRestApi(): SearchRestApi {
            return SearchRestApiFactory().makeRestApi()
        }
    }
}