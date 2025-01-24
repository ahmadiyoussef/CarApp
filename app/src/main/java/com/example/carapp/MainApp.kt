package com.example.carapp

import android.app.Application
import com.example.carapp.di.DaggerAppComponent


class MainApp : Application() {

    val appComponent by lazy { DaggerAppComponent.factory().create(this) }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
    }

    companion object {
        private lateinit var INSTANCE: MainApp

        fun get() = INSTANCE
    }
}