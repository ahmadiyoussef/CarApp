package com.example.carapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.carapp.common.Resource
import com.example.carapp.data.repository.CarRepository
import com.example.carapp.domain.CarDomainModel
import com.example.carapp.domain.usecase.GetCarsUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class CarViewModel @Inject constructor(
    private val getCarsUseCase: GetCarsUseCase
) : ViewModel() {

    private val _carList = MutableStateFlow<Resource<List<CarDomainModel>>>(Resource.Loading)
    val carList: StateFlow<Resource<List<CarDomainModel>>> = _carList

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing

    init {
        getCars(make = "ford")
    }

     fun getCars(make: String) {
        viewModelScope.launch {
            _carList.value = Resource.Loading
            try {
                val cars = getCarsUseCase(make)
                _carList.value = Resource.Success(cars)
            } catch (e: Exception) {
                _carList.value = Resource.Error("Failed to load cars")
            }
        }
    }

    fun refreshCars(make: String) {
        viewModelScope.launch {
            _isRefreshing.value = true
            try {
                val cars = getCarsUseCase(make)
                _carList.value = Resource.Success(cars)
            } catch (e: Exception) {
                _carList.value = Resource.Error("Failed to refresh cars")
            } finally {
                _isRefreshing.value = false
            }
        }
    }
}

