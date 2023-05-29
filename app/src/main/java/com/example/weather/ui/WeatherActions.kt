package com.example.weather.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weather.R
import com.example.weather.WeatherVM

@Composable
fun WeatherActions(
    modifier: Modifier,
    state: State<WeatherVM.State>,
    locationChangeAction: (String) -> Unit,
    openOtherActivityAction: () -> Unit,
    locationRequestAction: () -> Unit
) {
    var location: String by remember(key1 = state.value) {
        mutableStateOf((state.value as? WeatherVM.State.Loaded)?.location ?: "Manchester")
    }

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        Card(
            modifier = Modifier.padding(16.dp),
            elevation = 4.dp,
            shape = RoundedCornerShape(size = 12.dp)
        ) {
            Row(
                modifier = Modifier
                    .padding(all = 12.dp)
                    .clickable { locationRequestAction() },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    modifier = Modifier
                        .clip(shape = CircleShape)
                        .size(size = 36.dp),
                    painter = painterResource(id = R.drawable.location),
                    contentDescription = " location ",
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(width = 8.dp)) // gap between image and text
                Text(
                    text = " Location ",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
        Spacer(modifier = Modifier.size(10.dp))

        OutlinedTextField(
            modifier = Modifier
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
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp), Arrangement.SpaceEvenly
        ) {
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

