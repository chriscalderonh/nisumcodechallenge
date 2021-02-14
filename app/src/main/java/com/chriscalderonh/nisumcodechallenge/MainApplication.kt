package com.chriscalderonh.nisumcodechallenge

import android.app.Application
import com.chriscalderonh.nisumcodechallenge.di.ApplicationComponent
import com.chriscalderonh.nisumcodechallenge.di.ApplicationModule
import com.chriscalderonh.nisumcodechallenge.di.DaggerApplicationComponent

open class MainApplication : Application() {
    lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        createComponent()
    }

    protected open fun createComponent() {
        component = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .build()
        component.inject(this)
    }
}