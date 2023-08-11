package com.branislavbily.rocket.features.rockets.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.branislavbily.rocket.R
import com.branislavbily.rocket.features.rockets.domain.Rocket

@Composable
fun RocketListItem(
    rocket: Rocket,
    onRocketClicked: (Int) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White)
            .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp)
            .clickable {
                onRocketClicked(rocket.id)
            },
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Column(
            modifier = Modifier.padding(),
        ) {
            Image(
                painter = painterResource(id = R.drawable.rocket),
                contentDescription = "Rocket",
            )
        }
        Column(
            modifier = Modifier.padding(),
        ) {
            Text(text = rocket.name)
            Text(text = "First flight: ${rocket.firstFlight}", fontWeight = FontWeight.Light)
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
        ) {
            Icon(
                modifier = Modifier
                    .padding(),
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = "Arrow right",
            )
        }
    }
}

@Preview
@Composable
fun RocketListItemPreview() {
    RocketListItem(
        rocket = Rocket(),
        onRocketClicked = { _ -> },
    )
}
