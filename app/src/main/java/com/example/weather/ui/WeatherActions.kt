package com.example.weather.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction

@Composable
fun WeatherActions(locationChangeAction: (String) -> Unit) {
    var location: String by remember {
        mutableStateOf("Manchester")
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(1f))
        TextField(
            value = location,
            onValueChange = { location = it },
            singleLine = true,
            maxLines = 1,
            keyboardActions = KeyboardActions(onDone = {
                locationChangeAction(
                    location
                )
            }),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done)
        )
        Button(
            onClick = { locationChangeAction(location) },
        ) {
            Text(text = "Update weather")
        }
    }
}
