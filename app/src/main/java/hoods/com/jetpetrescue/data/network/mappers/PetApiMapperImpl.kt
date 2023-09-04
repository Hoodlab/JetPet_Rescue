package hoods.com.jetpetrescue.data.network.mappers

import hoods.com.jetpetrescue.data.network.models.Address
import hoods.com.jetpetrescue.data.network.models.ApiAnimals
import hoods.com.jetpetrescue.data.network.models.Contact
import hoods.com.jetpetrescue.data.network.models.Photo
import hoods.com.jetpetrescue.domain.models.Pet
import hoods.com.jetpetrescue.domain.models.PetOwnerContacts
import hoods.com.jetpetrescue.domain.models.PetPhoto


class PetApiMapperImpl : PetApiMapper<List<Pet>, ApiAnimals> {
    companion object {
        private const val EMPTY_DATA = "unknown"
    }

    override fun mapToDomain(apiEntity: ApiAnimals): List<Pet> {
        return apiEntity.animals.map { animal ->
            animal.run {
                Pet(
                    id = formatData(id.toString()),
                    age = formatData(age),
                    breeds = formatData(breeds.primary),
                    colors = formatData(colors.primary),
                    contact = formatContacts(contact),
                    description = formatData(description),
                    distance = formatData(distance?.toString()),
                    gender = formatData(gender),
                    name = formatData(name),
                    photos = formatPhotos(photos),
                    size = formatData(size),
                    species = formatData(species),
                    status = formatData(status),
                    tags = tags ,
                    type = formatData(type),
                    currentPage = apiEntity.pagination.currentPage,
                    url = url
                )
            }
        }
    }

    private fun formatContacts(contact: Contact?): PetOwnerContacts {
        return PetOwnerContacts(
            address = formatData(formatAddress(contact?.address)),
            email = formatData(contact?.email),
            phone = formatData(contact?.phone)
        )
    }

    private fun formatAddress(address: Address?): String {
        val dot = "u25CF"
        if (address != null) {
            return "${address.city}$dot${address.country}"
        }
        return ""
    }

    private fun formatData(data: String?): String {
        return data ?: EMPTY_DATA
    }

    private fun formatPhotos(photoList: List<Photo>?): List<PetPhoto> {
        return photoList?.map { photo ->
            PetPhoto(
                full = formatData(photo.full),
                large = formatData(photo.large),
                medium = formatData(photo.medium),
                small = formatData(photo.small)
            )
        } ?: listOf()
    }
}


















