package hoods.com.jetpetrescue.data.network.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Environment(
    @SerialName("cats")
    val cats: Boolean? = null,
    @SerialName("children")
    val children: Boolean? = null,
    @SerialName("dogs")
    val dogs: Boolean? = null
)