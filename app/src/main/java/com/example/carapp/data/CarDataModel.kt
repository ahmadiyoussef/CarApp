package com.example.carapp.data

import com.google.gson.annotations.SerializedName

data class CarDataModel(
    @SerializedName("model_name")
    val name: String?,
    @SerializedName("model_make_id")
    val description: String?,
    @SerializedName("model_image")
    val imageUrl: String?
)
