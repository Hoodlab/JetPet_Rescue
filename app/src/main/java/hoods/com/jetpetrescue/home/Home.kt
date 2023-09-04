
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun Home(
    onSwitchClick:() ->Unit,
    onPetClick: (Int) -> Unit
) {
    val petList = DummyPetDataSource.dogList
    Scaffold(
        topBar = {
            TopBar {
                onSwitchClick()
            }
        }
    ) { paddingValues ->
        LazyColumn(contentPadding = paddingValues) {
            itemsIndexed(petList) { index,pet ->
                PetInfoItem(pet = pet) {
                    onPetClick(index)
                }
            }
        }
    }


}

@Preview(showSystemUi = true)
@Composable
fun PrevItem() {
    Home(onSwitchClick = {}){

    }
}