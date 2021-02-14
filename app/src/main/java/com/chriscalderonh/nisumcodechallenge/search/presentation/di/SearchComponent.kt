package com.chriscalderonh.nisumcodechallenge.search.presentation.di

import com.chriscalderonh.nisumcodechallenge.di.ActivityScope
import com.chriscalderonh.nisumcodechallenge.di.ApplicationComponent
import dagger.Component

@ActivityScope
@Component(
    modules = [PresentationModule::class],
    dependencies = [ApplicationComponent::class])
interface  SearchComponent