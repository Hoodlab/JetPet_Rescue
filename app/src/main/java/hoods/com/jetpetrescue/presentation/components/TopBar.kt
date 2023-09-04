package hoods.com.jetpetrescue.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import hoods.com.jetpetrescue.R
import hoods.com.jetpetrescue.presentation.ui.theme.JetPetRescueTheme

@Composable
fun TopBar(onSwitchToggle: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Hey Saed,",
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.h5,
                color = MaterialTheme.colors.onSurface
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Find a new friend near you to adopt!",
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.subtitle1,
                color = MaterialTheme.colors.onSurface
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 24.dp,
                    end = 36.dp,
                )
        ) {
            PetRescueSwitch {
                onSwitchToggle.invoke()
            }
        }
    }
}

@Composable
fun PetRescueSwitch(onSwitchToggle: () -> Unit) {
    val icon = if (isSystemInDarkTheme())
        painterResource(id = R.drawable.ic_switch_on)
    else
        painterResource(id = R.drawable.ic_switch_off)
    Icon(
        painter = icon,
        contentDescription = null,
        modifier = Modifier
            .size(24.dp, 24.dp)
            .clickable(onClick = onSwitchToggle),
        tint = MaterialTheme.colors.onSurface
    )

}

@Preview(showBackground = true)
@Composable
fun PrevTopBar() {
    JetPetRescueTheme() {
        TopBar {

        }
    }
}




















