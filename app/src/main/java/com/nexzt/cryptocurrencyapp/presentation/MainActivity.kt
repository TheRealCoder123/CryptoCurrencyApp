package com.nexzt.cryptocurrencyapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nexzt.cryptocurrencyapp.common.Constants
import com.nexzt.cryptocurrencyapp.presentation.coin_detail.CoinDetailScreen
import com.nexzt.cryptocurrencyapp.presentation.coin_list.CoinListScreen
import com.nexzt.cryptocurrencyapp.presentation.coin_list.components.CoinListItem
import com.nexzt.cryptocurrencyapp.presentation.ui.theme.CryptoCurrencyAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CryptoCurrencyAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.Black
                ) {
                    
                    val navController = rememberNavController()
                    
                    NavHost(navController = navController, startDestination = Screen.CoinListScreen.route){
                        composable(Screen.CoinListScreen.route){
                            CoinListScreen(navController = navController)
                        }
                        composable(Screen.CoinDetailsScreen.route + "/{${Constants.PARAM_COIN_ID}}"){
                            CoinDetailScreen()
                        }
                    }
                    
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CryptoCurrencyAppTheme {
        Greeting("Android")
    }
}