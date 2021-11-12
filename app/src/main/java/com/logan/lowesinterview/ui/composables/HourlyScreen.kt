package com.logan.lowesinterview.ui.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import com.logan.lowesinterview.R
import com.logan.lowesinterview.models.HourlyWeather
import com.logan.lowesinterview.ui.WeatherViewModel
import com.logan.lowesinterview.utils.Routes

@Composable
fun HourlyScreen(
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
        HourlyList(weatherViewModel = weatherViewModel, navController = navController)
    }
}

@Composable
fun HourlyList(weatherViewModel: WeatherViewModel, navController: NavController) {
    LazyColumn {
        weatherViewModel.weather.value?.let {
            items(items = it.list, itemContent = { item ->
                HourlyItem(hourlyWeather = item, navController = navController, weatherViewModel = weatherViewModel)
            })
        }
    }
}

@Composable
fun HourlyItem(
    hourlyWeather: HourlyWeather,
    navController: NavController,
    weatherViewModel: WeatherViewModel
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .clickable {
                    weatherViewModel.setUserSelectedWeatherItem(hourlyWeather = hourlyWeather)
                    navController.navigate(Routes.CURRENT)
                }
        ) {
            Text(text = hourlyWeather.weather[0].main)
            Text(text = "Temp: ${hourlyWeather.main.temp.toInt()}")
        }
        Divider(
            modifier = Modifier.fillMaxWidth(),
            color = Color.Gray,
            thickness = 0.75.dp
        )
    }

}