package com.logan.lowesinterview

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.logan.lowesinterview.ui.WeatherViewModel
import com.logan.lowesinterview.ui.composables.CurrentWeatherScreen
import com.logan.lowesinterview.ui.composables.HourlyScreen
import com.logan.lowesinterview.ui.composables.SearchScreen
import com.logan.lowesinterview.ui.theme.LowesInterviewTheme
import com.logan.lowesinterview.utils.Routes
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val weatherViewModel: WeatherViewModel by viewModels()

            LowesInterviewTheme {
                NavHost(navController = navController, startDestination = "search") {
                    composable(Routes.SEARCH) {
                        SearchScreen(
                            navController = navController,
                            weatherViewModel = weatherViewModel
                        )
                    }
                    composable(Routes.HOURLY) {
                        HourlyScreen(
                            navController = navController,
                            weatherViewModel = weatherViewModel
                        )
                    }
                    composable(Routes.CURRENT) {
                        CurrentWeatherScreen(
                            navController = navController,
                            weatherViewModel = weatherViewModel
                        )
                    }
                }
            }
        }
    }
}