package com.chriscalderonh.nisumcodechallenge.search.presentation.di

import com.chriscalderonh.nisumcodechallenge.di.ActivityScope
import com.chriscalderonh.nisumcodechallenge.search.ui.SearchActivity
import com.chriscalderonh.nisumcodechallenge.search.ui.SearchFragment
import dagger.Component

@ActivityScope
@Component(
    modules = [PresentationModule::class, RemoteModule::class, DataModule::class]
)
interface SearchComponent {

    fun inject(searchActivity: SearchActivity)

    fun inject(searchFragment: SearchFragment)
}