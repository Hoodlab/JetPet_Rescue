package hoods.com.jetpetrescue.domain.paginator

class PetPaginatorImpl<Page, Result>(
    private val initialKey: Page,
    private val loadingState: LoadingStateListener<Result>,
    private val onRequest: suspend (nextPage: Page) -> Result,
    private val getNextPage: (Result) -> Page,
) : PetPaginator<Page, Result> {
    private var currentPage = initialKey
    override suspend fun fetchNextPage() {
        try {
            val result = onRequest.invoke(currentPage)
            loadingState.onLoadingStateChanged(true)
            currentPage = getNextPage.invoke(result)
            loadingState.onDataFetched(result)
            loadingState.onLoadingStateChanged(false)

        } catch (e: Exception) {
            loadingState.onLoadingStateChanged(false)
            loadingState.onError(e)
        }
    }

    override fun resetPage() {
        currentPage = initialKey
    }

}

interface LoadingStateListener<T> {
    fun onLoadingStateChanged(isLoading: Boolean)
    fun onDataFetched(data: T)
    fun onError(error: Throwable)
}
