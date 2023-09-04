package hoods.com.jetpetrescue.domain.paginator

interface PetPaginator<Page, Content> {
    suspend fun fetchNextPage()
    fun resetPage()
}