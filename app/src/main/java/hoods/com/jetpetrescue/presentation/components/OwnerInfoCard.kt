package hoods.com.jetpetrescue.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import hoods.com.jetpetrescue.R
import hoods.com.jetpetrescue.presentation.model.Owner
import hoods.com.jetpetrescue.presentation.ui.theme.JetPetRescueTheme
import hoods.com.jetpetrescue.presentation.ui.theme.blueText

@Composable
fun OwnerInfoCard(owner: Owner) {
    Spacer(modifier = Modifier.height(16.dp))
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier
                    .size(60.dp, 60.dp)
                    .clip(RoundedCornerShape(16.dp)),
                painter = painterResource(id = owner.image),
                alignment = Alignment.CenterStart,
                contentDescription = "",
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier) {
                Text(
                    text = owner.name,
                    color = MaterialTheme.colors.onSurface,
                    style = MaterialTheme.typography.subtitle1,
                    fontWeight = FontWeight.W600,
                    textAlign = TextAlign.Start
                )

                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = owner.basicInfo,
                    color = MaterialTheme.colors.onSurface,
                    style = MaterialTheme.typography.caption
                )
            }
        }
        Surface(
            modifier = Modifier
                .size(40.dp)
                .clickable { }
                .clip(CircleShape),
            shape = CircleShape,
            color = MaterialTheme.colors.primary,
            contentColor = MaterialTheme.colors.onPrimary
        ) {
           Box(
               contentAlignment = Alignment.Center,
               modifier =Modifier.size(20.dp),
           ){
               Icon(
                   painter = painterResource(id = R.drawable.ic_messenger),
                   contentDescription = "",
                   tint = Color.White
               )
           }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PrevOwnerCardItem() {
    JetPetRescueTheme {
        OwnerInfoCard(owner = Owner(
            "John",
            "Basic Info",
            R.drawable.blue_dog
        ))
    }
}