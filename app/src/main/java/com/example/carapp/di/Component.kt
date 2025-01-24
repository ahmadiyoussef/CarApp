package com.example.carapp.di

import android.app.Activity
import android.content.Context

import com.example.carapp.MainApp
import com.example.carapp.ui.CarViewModel
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance appContext: Context
        ): AppComponent
    }

    val carViewModel: CarViewModel
}


val Activity.injector: AppComponent
    get() = (application as MainApp).appComponent