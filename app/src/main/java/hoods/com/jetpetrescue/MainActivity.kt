package hoods.com.jetpetrescue

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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import hoods.com.jetpetrescue.presentation.detail.DetailScreen
import hoods.com.jetpetrescue.presentation.home.HomeScreen
import hoods.com.jetpetrescue.presentation.navigation.JetPetNavigation
import hoods.com.jetpetrescue.presentation.ui.theme.JetPetRescueTheme
import hoods.com.jetpetrescue.presentation.viemodels.MainViewModel



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val vieModel = viewModel(modelClass = MainViewModel::class.java)
            var isDark by remember {
                mutableStateOf(false)
            }
            var id by remember {
                mutableStateOf(-1)
            }
            val navController = rememberNavController()
            JetPetRescueTheme(isDark) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                   JetPetNavigation(
                       navController = navController,
                       uistate = vieModel.uiState,
                       onThemeChange = {isDark = !isDark},
                       onLoadNextPage = vieModel::loadNextPetsPage,
                       onInfiniteScrollChange = vieModel::onInfiniteScrollChange
                   )
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
    JetPetRescueTheme {
        Greeting("Android")
    }
}