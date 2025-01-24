package com.example.carapp.data.repository

import com.example.carapp.data.remote.api.CarApiService
import com.example.carapp.domain.CarDomainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CarRepository(private val apiService: CarApiService) {
    suspend fun getCars(make: String): List<CarDomainModel> {
        return withContext(Dispatchers.IO) {
            val response = apiService.getCarModels(make)
            response.cars.map {
                CarDomainModel(
                    name = it.name,
                    description = it.description ?: "No description available",
                    imageUrl = it.imageUrl ?: ""
                )
            }
        }
    }
}