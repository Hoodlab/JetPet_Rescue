package hoods.com.jetpetrescue.presentation.viemodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hoods.com.jetpetrescue.Graph
import hoods.com.jetpetrescue.domain.models.Pet
import hoods.com.jetpetrescue.domain.paginator.LoadingStateListener
import hoods.com.jetpetrescue.domain.paginator.PetPaginatorImpl
import hoods.com.jetpetrescue.domain.repository.PetRepository
import hoods.com.jetpetrescue.utils.ResourceHolder
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: PetRepository = Graph.petRepository,
) : ViewModel(), LoadingStateListener<ResourceHolder<List<Pet>>> {
    var uiState by mutableStateOf(Uistate())

    companion object {
        const val TAG = "myModel"
    }

    private val petPaginator = PetPaginatorImpl(
        initialKey = getPage(uiState.animals.data),
        loadingState = this,
        onRequest = { page ->
            if (uiState.isFetchingPet)
                return@PetPaginatorImpl ResourceHolder.Loading()
            val pet = fetchAnimals(page)
            pet
        },
        getNextPage = { result ->
            getPage(result.data)
        }
    )

    init {
        loadNextPetsPage()
    }

    fun loadNextPetsPage() {
        viewModelScope.launch {
            petPaginator.fetchNextPage()
        }
    }

    fun onInfiniteScrollChange(start: Boolean) {
        uiState = uiState.copy(
            startInfiniteScrolling = start,
            loadMoreButtonVisible = !start
        )
    }

    private suspend fun fetchAnimals(page: Int): ResourceHolder<List<Pet>> {
        return repository.getAnimal(page)
    }

    private fun getPage(pageSource: List<Pet>?): Int {
        return if (pageSource?.isNotEmpty() == true) {
            pageSource[pageSource.lastIndex].currentPage + 1
        } else 1
    }

    override fun onLoadingStateChanged(isLoading: Boolean) {
        uiState = uiState.copy(isFetchingPet = isLoading)
    }

    override fun onError(error: Throwable) {
        Log.e(TAG, "onError: Fetching Pet error", error)
    }

    override fun onDataFetched(data: ResourceHolder<List<Pet>>) {
        uiState = uiState.updateAnimal(data)
    }

    private fun Uistate.updateAnimal(newData: ResourceHolder<List<Pet>>): Uistate {
        return when (newData) {
            is ResourceHolder.Success -> {
                val updateData = this.animals.data?.combineData(newData.data!!) ?: newData
                copy(animals = updateData)
            }

            is ResourceHolder.Error -> {
                copy(animals = newData)
            }

            else -> this
        }
    }

    private fun <Data> List<Data>.combineData(newList: List<Data>)
            : ResourceHolder<List<Data>> {
        return ResourceHolder.Success(data = this.union(newList).toList())
    }
}

data class Uistate(
    val animals: ResourceHolder<List<Pet>> = ResourceHolder.Loading(),
    val isFetchingPet: Boolean = false,
    val loadMoreButtonVisible: Boolean = true,
    val startInfiniteScrolling: Boolean = false,
)








