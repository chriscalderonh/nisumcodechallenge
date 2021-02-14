package com.chriscalderonh.nisumcodechallenge.search.presentation.di

import androidx.lifecycle.ViewModelProvider
import com.chriscalderonh.nisumcodechallenge.common.presentation.execution.AppExecutionThread
import com.chriscalderonh.nisumcodechallenge.common.presentation.execution.ExecutionThread
import com.chriscalderonh.nisumcodechallenge.di.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class PresentationModule {

    @Binds
    abstract fun bindExecutionThread(executionThread: AppExecutionThread): ExecutionThread

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}