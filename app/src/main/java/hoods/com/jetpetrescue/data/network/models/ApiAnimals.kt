package hoods.com.jetpetrescue.data.network.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiAnimals(
    @SerialName("animals")
    val animals: List<Animal> = listOf(),
    @SerialName("pagination")
    val pagination: Pagination = Pagination()
)