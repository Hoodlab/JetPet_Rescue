
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import hoods.com.jetpetrescue.R
import hoods.com.jetpetrescue.data.model.Pet

@Composable
fun PetInfoItem(
    pet: Pet,
    onPetItemClick:(Pet) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable(onClick = {onPetItemClick(pet)}),
        elevation = 2.dp,
        backgroundColor = MaterialTheme.colors.surface
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row {
                Image(
                    modifier = Modifier
                        .size(80.dp, 80.dp)
                        .clip(RoundedCornerShape(16.dp)),
                    painter = painterResource(id = pet.image),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.CenterStart
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column(
                    modifier = Modifier.align(Alignment.CenterVertically)
                ) {
                    Text(
                        text = pet.name,
                        color = MaterialTheme.colors.onSurface,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.subtitle1
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = buildString {
                            append(pet.age)
                            append(" | ")
                            append(pet.breed)
                        },
                        color = MaterialTheme.colors.onSurface,
                        style = MaterialTheme.typography.caption
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        verticalAlignment = Alignment.Bottom
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_location),
                            contentDescription = null,
                            modifier = Modifier
                                .size(16.dp, 16.dp),
                            tint = Color.Red
                        )
                        Text(
                            text = pet.location,
                            modifier = Modifier.padding(
                                start = 8.dp,
                                top = 0.dp,
                                end = 12.dp,
                                bottom = 0.dp
                            ),
                            color = MaterialTheme.colors.onSurface,
                            style = MaterialTheme.typography.caption
                        )
                    }


                }
            }
            Column(
                modifier = Modifier.height(80.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                GenderTag(gender = pet.gender, modifier = Modifier)
                Text(
                    text = "Adoptable",
                    style = MaterialTheme.typography.caption,
                    color = MaterialTheme.colors.onSurface
                )
            }
        }
    }
}

@Composable
fun GenderTag(gender: String, modifier: Modifier) {
    val color = if (gender == "Male") {
        Color.Blue
    } else {
        Color.Red
    }
    Box(
        modifier = modifier
            .wrapContentSize()
            .clip(RoundedCornerShape(12.dp))
            .background(color.copy(.2f))
    ) {
        Text(
            text = gender,
            modifier = Modifier.padding(12.dp, 4.dp, 12.dp, 6.dp),
            style = MaterialTheme.typography.caption,
            color = color
        )
    }
}


@Preview(showBackground = true)
@Composable
fun PrevPetInfoItem() {
    val petItem = DummyPetDataSource.dogList.random()
    PetInfoItem(petItem){

    }
}

