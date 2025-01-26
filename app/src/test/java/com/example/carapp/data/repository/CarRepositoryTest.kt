package com.example.carapp.data.repository

import com.example.carapp.data.CarDataModel
import com.example.carapp.data.remote.api.CarApiResponse
import com.example.carapp.data.remote.api.CarApiService
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Test

@ExperimentalCoroutinesApi
class CarRepositoryTest{

    private val apiService: CarApiService = mockk<CarApiService>()

    private val carRepository = CarRepository(apiService)

    @Test
    fun `getCars returns list of cars when API call is successful`() = runTest {
        // Arrange
        val mockCarResponse = CarApiResponse(
            cars = listOf(
                CarDataModel(name = "Ford Mustang", description = "A classic car", imageUrl = "url1"),
                CarDataModel(name = "Tesla Model S", description = "An electric car", imageUrl = "url2")
            )
        )
        coEvery { apiService.getCarModels("ford") } returns mockCarResponse

        // When
        val result = carRepository.getCars("ford")

        // Assert
        assertEquals(2, result.size)
        assertEquals("Ford Mustang", result[0].name)
        assertEquals("A classic car", result[0].description)
        assertEquals("url1", result[0].imageUrl)
        assertEquals("Tesla Model S", result[1].name)
        assertEquals("An electric car", result[1].description)
        assertEquals("url2", result[1].imageUrl)
    }



    @Test
    fun `getCars handles missing data by providing default values`() = runTest {
        // Arrange
        val mockCarResponse = CarApiResponse(
            cars = listOf(
                CarDataModel(name = null, description = null, imageUrl = null)
            )
        )
        coEvery { apiService.getCarModels("ford") } returns mockCarResponse

        // When
        val result = carRepository.getCars("ford")

        // Assert
        assertEquals(1, result.size)
        assertEquals("no name", result[0].name)
        assertEquals("No description available", result[0].description)

    }



    @Test
    fun `getCars handles null name and description gracefully`() = runTest {
        // Arrange
        val mockResponse = CarApiResponse(
            cars = listOf(
                CarDataModel(name = null, description = null, imageUrl = "urlA")
            )
        )
        coEvery { apiService.getCarModels("ford") } returns mockResponse

        // When
        val result = carRepository.getCars("ford")

        // Assert
        assertEquals(1, result.size)
        assertEquals("no name", result[0].name)
        assertEquals("No description available", result[0].description)
        assertEquals("urlA", result[0].imageUrl)
    }
}