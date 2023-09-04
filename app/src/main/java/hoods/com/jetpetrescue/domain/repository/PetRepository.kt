package hoods.com.jetpetrescue.domain.repository


import hoods.com.jetpetrescue.domain.models.Pet
import hoods.com.jetpetrescue.utils.ResourceHolder


interface PetRepository {
    suspend fun getAnimal(page: Int): ResourceHolder<List<Pet>>
}