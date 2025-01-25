package com.example.carapp.ui


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.carapp.MainDispatcherRule
import com.example.carapp.common.Resource
import com.example.carapp.domain.CarDomainModel
import com.example.carapp.domain.usecase.GetCarsUseCase
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@ExperimentalCoroutinesApi
class CarViewModelTest{


    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val carUseCase = mockk<GetCarsUseCase>()
    private lateinit var viewModel: CarViewModel

    @Before
    fun setUp() {

        viewModel = CarViewModel(carUseCase)
    }


    @Test
    fun `load cars successfully`() = runTest {
        // Arrange
        val mockCars = listOf(
            CarDomainModel("1", "Ford Mustang", "url1"),
            CarDomainModel("2", "Tesla Model S", "url2")
        )
        coEvery { carUseCase("ford") } returns mockCars

        // Act
        viewModel.getCars("ford")


        // Assert
        val result = viewModel.carList.first()
        assertEquals(Resource.Success(mockCars), result)
    }
}