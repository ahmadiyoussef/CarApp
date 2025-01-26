package com.example.carapp.data.repository

import com.example.carapp.data.remote.api.CarApiService
import com.example.carapp.domain.CarDomainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CarRepository @Inject constructor(private val apiService: CarApiService) {
    suspend fun getCars(make: String): List<CarDomainModel> {
        val response = apiService.getCarModels(make)
        return response.cars.map {
            CarDomainModel(
                name = it.name ?: "no name",
                description = it.description ?: "No description available",
                imageUrl = it.imageUrl ?: images.random()
            )
        }
    }
}

val images = listOf(
    "c",
    "https://upload.wikimedia.org/wikipedia/commons/c/ca/VW_Golf_V_front_20081127.jpg",
    "https://upload.wikimedia.org/wikipedia/commons/7/75/2018_Ford_Focus_ST-Line_X_1.0.jpg",
    "https://upload.wikimedia.org/wikipedia/commons/c/ca/VW_Golf_V_front_20081127.jpg",
    "https://images.prismic.io/carwow/c340a77d-af56-4562-abfb-bd5518ccb292_2023+Tesla+Model+X+front+quarter+moving.jpg",
    "https://cdn.prod.website-files.com/5ec85520c4dfff034b036be2/659c5c38c307f136dc54d96f_modelx.webp",
    "https://upload.wikimedia.org/wikipedia/commons/c/ca/VW_Golf_V_front_20081127.jpg",
)