package com.example.carapp.data.remote.api

import com.example.carapp.data.CarDataModel
import com.google.gson.annotations.SerializedName

data class CarApiResponse(

    @SerializedName("Models")
    val cars: List<CarDataModel>
)
