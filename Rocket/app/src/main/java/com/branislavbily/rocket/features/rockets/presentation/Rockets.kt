package com.branislavbily.rocket.features.rockets.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import com.branislavbily.rocket.R
import com.branislavbily.rocket.core.presentation.rememberLifecycleEvent
import com.branislavbily.rocket.features.Screens
import com.branislavbily.rocket.features.rockets.domain.Rocket
import com.branislavbily.rocket.features.rockets.presentation.components.RocketListItem

@Composable
fun Rockets(
    navController: NavController,
    viewModel: RocketsViewModel,
) {
    val state by viewModel.viewState.collectAsState()
    val lifecycleEvent = rememberLifecycleEvent()
    LaunchedEffect(lifecycleEvent) {
        if (lifecycleEvent == Lifecycle.Event.ON_RESUME) {
            viewModel.getRockets()
        }
    }
    RocketsContent(
        state = state,
        onRocketClicked = { rocketId ->
            navController.navigate(Screens.RocketDetail.route + "/$rocketId")
        },
    )
}

@Composable
fun RocketsContent(
    state: RocketsScreenState,
    onRocketClicked: (String) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surfaceVariant),
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
        ) {
            Text(
                modifier = Modifier.padding(top = 16.dp),
                text = stringResource(id = R.string.rockets),
                style = MaterialTheme.typography.displayMedium,
                fontWeight = FontWeight.Bold,
            )
            if (state.isLoading) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center,
                ) {
                    CircularProgressIndicator()
                }
            } else {
                Column(
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .clip(RoundedCornerShape(16.dp)),
                ) {
                    LazyColumn(
                        modifier = Modifier.background(MaterialTheme.colorScheme.background),
                    ) {
                        items(
                            items = state.rockets,
                            key = { rocket ->
                                rocket.id
                            },
                        ) { rocket ->
                            RocketListItem(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(60.dp)
                                    .background(color = Color.White)
                                    .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp),
                                rocket = rocket,
                                onRocketClicked = onRocketClicked,
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun RocketsPreview() {
    RocketsContent(
        RocketsScreenState(
            rockets = listOf(
                Rocket(),
            ),
        ),
        { },
    )
}
