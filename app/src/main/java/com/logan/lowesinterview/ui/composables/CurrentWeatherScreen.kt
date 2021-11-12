package com.logan.lowesinterview.ui.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.logan.lowesinterview.R
import com.logan.lowesinterview.ui.WeatherViewModel

@Composable
fun CurrentWeatherScreen(
    navController: NavController,
    weatherViewModel: WeatherViewModel
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = weatherViewModel.cityName) },
                navigationIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_baseline_arrow_back_24),
                        contentDescription = stringResource(id = R.string.back_button),
                        modifier = Modifier.clickable {
                            navController.popBackStack()
                        }
                    )
                },
            )
        }
    ) {
        CurrentWeather(weatherViewModel = weatherViewModel)
    }
}

@Composable
fun CurrentWeather(weatherViewModel: WeatherViewModel) {
    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.6f)
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = weatherViewModel.selectedWeatherItm.value?.main?.temp?.toInt().toString(),
                fontSize = 30.sp
            )
        }
        Row(
            horizontalArrangement = Arrangement.End,
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 20.dp)
        ) {
            Text(
                text = "Feels like: ${
                    weatherViewModel.selectedWeatherItm.value?.main?.feelsLike?.toInt().toString()
                }"
            )
        }
        Row(
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.fillMaxWidth().padding(start = 20.dp)
        ) {
            Column() {
                weatherViewModel.selectedWeatherItm.value?.weather?.get(0)
                    ?.let { Text(
                        text = it.main,
                        fontSize = 25.sp,
                        modifier = Modifier.padding(bottom = 10.dp)
                    ) }
                weatherViewModel.selectedWeatherItm.value?.weather?.get(0)
                    ?.let { Text(text = it.description) }
            }
        }
    }
}