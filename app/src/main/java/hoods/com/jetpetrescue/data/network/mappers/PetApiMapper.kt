package hoods.com.jetpetrescue.data.network.mappers

interface PetApiMapper<Domain,Entity> {
    fun mapToDomain(apiEntity: Entity):Domain
}