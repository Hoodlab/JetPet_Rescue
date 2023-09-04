package hoods.com.jetpetrescue.data.repository

import hoods.com.jetpetrescue.data.network.mappers.PetApiMapper
import hoods.com.jetpetrescue.data.network.models.ApiAnimals
import hoods.com.jetpetrescue.data.network.retrofit.PetFinderApiService
import hoods.com.jetpetrescue.domain.models.Pet
import hoods.com.jetpetrescue.domain.repository.PetRepository
import hoods.com.jetpetrescue.utils.ResourceHolder


class PetRepositoryImpl(
    private val apiService: PetFinderApiService,
    private val petApiMapper: PetApiMapper<List<Pet>, ApiAnimals>,
) : PetRepository {
    override suspend fun getAnimal(page: Int):
            ResourceHolder<List<Pet>> {
        return try {
            val data = apiService.getAnimals(page)
            ResourceHolder.Success(petApiMapper.mapToDomain(data))
        } catch (e: Exception) {
            e.printStackTrace()
            ResourceHolder.Error(e.cause)
        }
    }
}









