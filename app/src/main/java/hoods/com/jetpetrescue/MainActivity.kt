package hoods.com.jetpetrescue

import DetailScreen
import Home
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import hoods.com.jetpetrescue.ui.theme.JetPetTheme

enum class Screen {
    Home,
    Detail
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var isDarkTheme by remember {
                mutableStateOf(false)
            }
            var currentScreen by remember {
                mutableStateOf(Screen.Home)
            }
            var selectedIndex by remember {
                mutableStateOf(-1)
            }


            JetPetTheme(
                darkTheme = isDarkTheme
            ) {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background) {
                    when (currentScreen) {
                        Screen.Home -> {
                            Home(
                                onSwitchClick = { isDarkTheme =!isDarkTheme },
                                onPetClick = { index ->
                                    currentScreen = Screen.Detail
                                    selectedIndex = index
                                },
                            )
                        }
                        Screen.Detail -> {
                            DetailScreen(index = selectedIndex) {
                                currentScreen = Screen.Home
                            }
                        }

                    }

                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetPetTheme {
        Greeting("Android")
    }
}