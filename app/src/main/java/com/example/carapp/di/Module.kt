package com.example.carapp.di


import com.example.carapp.common.Constants.BASE_URL
import com.example.carapp.data.remote.api.CarApiService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class AppModule {


    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL).build()
    }

    @Provides
    @Singleton
    fun provideCarApiService(retrofit: Retrofit): CarApiService {
        return retrofit.create(CarApiService::class.java)
    }

}