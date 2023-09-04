package hoods.com.jetpetrescue.data.network.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Colors(
    @SerialName("primary")
    val primary: String? = null,
    @SerialName("secondary")
    val secondary: String? = null,
    @SerialName("tertiary")
    val tertiary: String? = null
)