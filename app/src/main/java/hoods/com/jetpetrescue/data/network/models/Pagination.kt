package hoods.com.jetpetrescue.data.network.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Pagination(
    @SerialName("count_per_page")
    val countPerPage: Int = 0,
    @SerialName("current_page")
    val currentPage: Int = 0,
    @SerialName("_links")
    val links: LinksX = LinksX(),
    @SerialName("total_count")
    val totalCount: Int = 0,
    @SerialName("total_pages")
    val totalPages: Int = 0
)