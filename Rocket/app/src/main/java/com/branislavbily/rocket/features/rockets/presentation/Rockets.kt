package com.branislavbily.rocket.features.rockets.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import com.branislavbily.rocket.R
import com.branislavbily.rocket.core.presentation.rememberLifecycleEvent
import com.branislavbily.rocket.features.Screens
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RocketsContent(
    state: RocketsScreenState,
    onRocketClicked: (String) -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Text(text = stringResource(id = R.string.Rockets))
                    }
                },
            )
        },
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
        ) {
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
                        .padding(16.dp)
                        .clip(RoundedCornerShape(16.dp)),
                ) {
                    LazyColumn {
                        items(
                            items = state.rockets,
                            key = { rocket ->
                                rocket.id
                            },
                        ) { rocket ->
                            RocketListItem(
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
    RocketsContent(RocketsScreenState(), { })
}
