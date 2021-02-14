package com.chriscalderonh.nisumcodechallenge.di

import android.content.Context
import com.chriscalderonh.nisumcodechallenge.MainApplication
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(mainApplication: MainApplication)
}