package com.chriscalderonh.nisumcodechallenge.search.presentation.di

import com.chriscalderonh.nisumcodechallenge.search.data.SearchDataRepository
import com.chriscalderonh.nisumcodechallenge.search.domain.SearchRepository
import dagger.Binds
import dagger.Module

@Module
abstract class DataModule {

    @Binds
    abstract fun bindCardsDataRepository(
        dataRepository: SearchDataRepository
    ): SearchRepository

}