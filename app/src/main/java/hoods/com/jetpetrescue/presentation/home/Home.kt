package hoods.com.jetpetrescue.presentation.home

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import hoods.com.jetpetrescue.presentation.components.PetItemCard
import hoods.com.jetpetrescue.presentation.components.TopBar
import hoods.com.jetpetrescue.presentation.viemodels.Uistate
import hoods.com.jetpetrescue.utils.ResourceHolder

@Composable
fun HomeScreen(
    onAppBarClick: () -> Unit,
    onPetClicked: (Int) -> Unit,
    onLoadNextPage: () -> Unit,
    onInfiniteScrollingChange: (Boolean) -> Unit,
    uistate: Uistate,
) {
    val scrollState = rememberLazyListState()
    Scaffold(
        topBar = {
            TopBar {
                onAppBarClick.invoke()
            }
        }
    ) { paddingValue ->
        LazyColumn(contentPadding = paddingValue) {
            when (uistate.animals) {
                is ResourceHolder.Loading -> {
                    item {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .fillMaxSize()
                                .wrapContentSize(align = Alignment.Center)
                        )
                    }
                }

                is ResourceHolder.Success -> {
                    val petList = uistate.animals.data ?: emptyList()
                    itemsIndexed(petList) { index, item ->
                        PetItemCard(
                            pet = item,
                            onPetItemClicked = {
                                onPetClicked.invoke(index)
                            }
                        )
                        LaunchedEffect(key1 = scrollState) {
                            Log.i("myApp", " index${index >= petList.lastIndex}")
                            Log.i("myApp", "isfetchpate${!uistate.isFetchingPet}")
                            Log.i("myApp", "startInfini${uistate.startInfiniteScrolling}")
                            if (
                                index >= petList.lastIndex &&
                                !uistate.isFetchingPet &&
                                uistate.startInfiniteScrolling
                            ) {
                                Log.i("myApp", "HomeScreen:nextpage Called")
                                onLoadNextPage()
                            }
                        }
                    }
                    if (uistate.isFetchingPet) {
                        item {
                            CircularProgressIndicator()
                        }
                    }
                    item {
                        AnimatedVisibility(
                            visible = uistate.loadMoreButtonVisible
                        ) {
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp)
                            ) {
                                TextButton(
                                    onClick = {
                                        onLoadNextPage.invoke()
                                        onInfiniteScrollingChange(true)
                                    }
                                ) {
                                    Text(text = "Load More Pets")
                                }
                            }
                        }
                    }
                }

                else -> {
                    uistate.animals.throwable?.printStackTrace()
                    item {
                        Text(
                            text = "Error Occured",
                            color = MaterialTheme.colors.error
                        )
                    }
                }
            }
        }
    }
}
