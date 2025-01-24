package com.example.carapp.domain.usecase

import com.example.carapp.data.repository.CarRepository
import com.example.carapp.domain.CarDomainModel

class GetCarsUseCase(private val repository: CarRepository) {
    suspend operator fun invoke(make: String): List<CarDomainModel> {
        return repository.getCars(make)
    }
}