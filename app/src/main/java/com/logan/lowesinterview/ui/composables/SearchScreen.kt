package com.logan.lowesinterview.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.toUpperCase
import androidx.hilt.navigation.compose.hiltViewModel

import androidx.navigation.NavController
import com.logan.lowesinterview.R
import com.logan.lowesinterview.ui.WeatherViewModel
import com.logan.lowesinterview.utils.Routes

@Composable
fun SearchScreen(
    navController: NavController,
    weatherViewModel: WeatherViewModel
) {
    var searchState by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = searchState,
            onValueChange = { searchState = it },
            label = { Text(stringResource(R.string.city_name)) }
        )

        Button(onClick = {
            weatherViewModel.getWeather(searchState.toUpperCase())
            weatherViewModel.cityName = searchState.toUpperCase()
            if (weatherViewModel.weather.value != null) {
                navController.navigate(Routes.HOURLY)
            }
        }
        ) {
            Text(text = stringResource(R.string.lookup))
        }
    }
}