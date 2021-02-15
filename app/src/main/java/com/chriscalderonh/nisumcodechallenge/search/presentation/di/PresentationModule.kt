package com.chriscalderonh.nisumcodechallenge.search.presentation.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.chriscalderonh.nisumcodechallenge.common.presentation.execution.AppExecutionThread
import com.chriscalderonh.nisumcodechallenge.common.presentation.execution.ExecutionThread
import com.chriscalderonh.nisumcodechallenge.di.ViewModelFactory
import com.chriscalderonh.nisumcodechallenge.di.ViewModelKey
import com.chriscalderonh.nisumcodechallenge.search.presentation.CollectionViewModel
import com.chriscalderonh.nisumcodechallenge.search.presentation.SearchViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class PresentationModule {

    @Binds
    abstract fun bindExecutionThread(executionThread: AppExecutionThread): ExecutionThread

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    abstract fun bindSearchViewModel(
        viewModel: SearchViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CollectionViewModel::class)
    abstract fun bindCollectionViewModel(
        viewModel: CollectionViewModel): ViewModel
}