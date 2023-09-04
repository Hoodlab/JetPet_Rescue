
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import hoods.com.jetpetrescue.data.model.Pet

@Composable
fun DetailScreen(index: Int, onNavigate: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Detail") },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = null,
                        modifier = Modifier
                            .size(24.dp, 24.dp)
                            .clickable {
                                onNavigate.invoke()
                            },
                        tint = MaterialTheme.colors.onSurface
                    )
                },
                backgroundColor = MaterialTheme.colors.background,
                contentColor = MaterialTheme.colors.onSurface
            )
        }
    ) { padding ->
        val pet = DummyPetDataSource.dogList[index]
        LazyColumn(contentPadding = padding) {
            item {
                Image(
                    painter = painterResource(id = pet.image),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(346.dp),
                    alignment = Alignment.CenterStart,
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(16.dp))
                PetBasicInfoItem(
                    name = pet.name,
                    gender = pet.gender,
                    location = pet.location,
                )
            }

            item {
                MyStoryItem(pet = pet)
            }

            item {
                PetInfo(pet = pet)
            }

            item {
                OwnerCardInfo(owner = pet.owner)
            }

            item {
                PetButton {

                }
            }

        }


    }
}

@Composable
fun PetButton(onClick: () -> Unit) {
    Spacer(modifier = Modifier.height(36.dp))
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
    ) {
        Text("Adopt Me")
    }
    Spacer(modifier = Modifier.height(24.dp))
}


@Composable
fun PetInfo(pet: Pet) {
    Column {
        Spacer(modifier = Modifier.height(24.dp))
        Title(title = "Pet Info")
        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            InfoCard(
                primaryText = pet.age,
                secondaryText = "Age",
                modifier = Modifier
                    .weight(1f)
                    .padding(4.dp)
            )

            InfoCard(
                primaryText = pet.color,
                secondaryText = "Color",
                modifier = Modifier
                    .weight(1f)
                    .padding(4.dp)
            )
            InfoCard(
                primaryText = pet.breed,
                secondaryText = "Breed",
                modifier = Modifier
                    .weight(1f)
                    .padding(4.dp)
            )

        }


    }

}

@Composable
fun InfoCard(
    modifier: Modifier = Modifier,
    primaryText: String,
    secondaryText: String,
) {
    Surface(
        shape = MaterialTheme.shapes.medium,
        modifier = modifier,
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CompositionLocalProvider(
                LocalContentAlpha provides ContentAlpha.disabled
            ) {
                Text(text = secondaryText)
            }
            Text(
                text = primaryText,
                style = MaterialTheme.typography.body1,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.size(4.dp))
        }

    }

}


@Composable
fun MyStoryItem(pet: Pet) {
    Column {
        Spacer(modifier = Modifier.height(24.dp))
        Title(title = "My Story")
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = pet.description,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            color = MaterialTheme.colors.onSurface,
            style = MaterialTheme.typography.body2,
            textAlign = TextAlign.Start
        )


    }

}

@Composable
fun Title(title: String) {
    Text(
        text = title,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp),
        color = MaterialTheme.colors.onSurface,
        style = MaterialTheme.typography.subtitle1,
        fontWeight = FontWeight.W600,
        textAlign = TextAlign.Start
    )
}


@Preview(showSystemUi = true)
@Composable
fun PrevDetailScreen() {
    DetailScreen(index = 0) {

    }
}

@Preview(showBackground = true)
@Composable
fun PrevInfoCard() {
    val pet = DummyPetDataSource.dogList[0]
    PetInfo(pet = pet)
}
