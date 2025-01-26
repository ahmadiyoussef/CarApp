package com.example.carapp.ui

import com.example.carapp.domain.CarDomainModel

data class CarUiModel(
    val name: String,
    val description: String,
    val imageUrl: String
)

fun CarDomainModel.toUiModel(): CarUiModel {
    return CarUiModel(
        name = this.name,
        description = this.description,
        imageUrl = this.imageUrl
    )
}