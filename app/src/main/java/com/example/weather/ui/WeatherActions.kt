package com.example.weather.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp

@Composable
fun WeatherActions(modifier: Modifier, locationChangeAction: (String) -> Unit, openOtherActivityAction: () -> Unit) {
    var location: String by remember {
        mutableStateOf("Manchester")
    }


    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField( modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
            label = {
                    Text(text = "location")
            },
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
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp), Arrangement.SpaceEvenly) {
            Button(
                modifier = Modifier.weight(1f),
                onClick = { locationChangeAction(location) },
            ) {
                Text(text = "Update weather")
            }
            Spacer(modifier = Modifier.size(16.dp))
            Button(modifier = Modifier.weight(1f), onClick = openOtherActivityAction) {
                Text(text = "Open other activity")
            }
        }
    }
}

