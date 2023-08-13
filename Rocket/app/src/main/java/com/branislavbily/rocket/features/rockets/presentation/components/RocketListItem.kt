package com.branislavbily.rocket.features.rockets.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.branislavbily.rocket.R
import com.branislavbily.rocket.features.rockets.domain.Rocket

@Composable
fun RocketListItem(
    modifier: Modifier = Modifier,
    rocket: Rocket,
    onRocketClicked: (String) -> Unit,
) {
    Column {
        Row(
            modifier = Modifier
                .clickable {
                    onRocketClicked(rocket.rocketID)
                },
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.Center,

            ) {
                Image(
                    modifier = Modifier.padding(end = 16.dp),
                    painter = painterResource(id = R.drawable.ic_rocket_logo),
                    contentDescription = "Rocket",
                )
            }
            Column(
                modifier = Modifier.padding(),
            ) {
                Text(
                    text = rocket.name,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = "First flight: ${rocket.firstFlight}",
                    style = MaterialTheme.typography.labelLarge,
                    color = Color.Gray,
                    fontWeight = FontWeight.Light,
                )
            }
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    modifier = Modifier
                        .padding()
                        .scale(1.5F),
                    imageVector = Icons.Default.KeyboardArrowRight,
                    tint = Color.LightGray,
                    contentDescription = "Arrow right",
                )
            }
        }
        Spacer(
            modifier = Modifier
                .padding(start = 16.dp)
                .fillMaxWidth()
                .height(1.dp)
                .background(MaterialTheme.colorScheme.surfaceVariant),
        )
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
