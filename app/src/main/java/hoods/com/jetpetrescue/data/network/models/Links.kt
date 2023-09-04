package hoods.com.jetpetrescue.data.network.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Links(
    @SerialName("organization")
    val organization: Organization = Organization(),
    @SerialName("self")
    val self: Self = Self(),
    @SerialName("type")
    val type: Type = Type()
)