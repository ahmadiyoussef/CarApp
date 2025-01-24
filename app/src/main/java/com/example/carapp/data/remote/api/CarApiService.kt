package com.example.carapp.data.remote.api

import retrofit2.http.GET
import retrofit2.http.Query

interface CarApiService {
    @GET("?cmd=getModels")
    suspend fun getCarModels(@Query("make") make: String): CarApiResponse
}