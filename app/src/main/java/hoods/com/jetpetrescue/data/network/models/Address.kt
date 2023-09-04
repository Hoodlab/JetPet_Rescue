package hoods.com.jetpetrescue.data.network.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Address(
    @SerialName("address1")
    val address1: String? = null,
    @SerialName("address2")
    val address2: String? = null,
    @SerialName("city")
    val city: String = "",
    @SerialName("country")
    val country: String = "",
    @SerialName("postcode")
    val postcode: String = "",
    @SerialName("state")
    val state: String = ""
)