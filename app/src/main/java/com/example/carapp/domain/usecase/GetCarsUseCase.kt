package com.example.carapp.domain.usecase

import com.example.carapp.data.repository.CarRepository
import com.example.carapp.domain.CarDomainModel
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class GetCarsUseCase@Inject constructor(private val repository: CarRepository) {
    suspend operator fun invoke(make: String): List<CarDomainModel> {
        return repository.getCars(make)
    }
}