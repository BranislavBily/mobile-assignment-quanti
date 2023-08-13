package com.branislavbily.rocket.features.launch.presentation

import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.branislavbily.rocket.R
import com.branislavbily.rocket.core.presentation.GoBackIconWithTitle

@Composable
fun Launch(
    navController: NavController,
    viewModel: LaunchViewModel,
    navigationTitle: String?,
) {
    val state by viewModel.viewState.collectAsState()
    val lifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(lifecycleOwner.lifecycle.currentState) {
        viewModel.listenToRotationChange()
    }

    LaunchContent(
        state = state,
        navigationTitle = navigationTitle.orEmpty().capitalize(Locale.current),
        onBackPressed = { navController.popBackStack() },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LaunchContent(
    state: LaunchScreenState,
    navigationTitle: String,
    onBackPressed: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    GoBackIconWithTitle(
                        onIconPressed = onBackPressed,
                        title = navigationTitle,
                    )
                },
                title = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        Text(
                            style = MaterialTheme.typography.titleMedium,
                            text = navigationTitle,
                        )
                    }
                },
                // I will create blank actions so that the title is in the center
                actions = {
                    Text(
                        modifier = Modifier.padding(end = 8.dp),
                        style = MaterialTheme.typography.titleMedium,
                        text = navigationTitle,
                        color = MaterialTheme.colorScheme.background,
                    )
                },
            )
        },
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentAlignment = Alignment.Center,
        ) {
            val offsetAnimation: Dp by animateDpAsState(
                animationSpec = tween(
                    durationMillis = 3000,
                    easing = FastOutLinearInEasing,
                ),
                targetValue = if (state.fireRocket) (-800).dp else 0.dp,
                label = "RocketPos",
            )

            val scaleAnimation: Float by animateFloatAsState(
                animationSpec = tween(
                    durationMillis = 3000,
                    easing = FastOutLinearInEasing,
                ),
                targetValue = if (state.fireRocket) 0.5F else 1F,
                label = "RocketScale",
            )

            if (!state.fireRocket) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_rocket_idle),
                        contentDescription = "Idle rocket",
                    )
                    Text(
                        modifier = Modifier.padding(top = 16.dp),
                        text = stringResource(R.string.move_your_phone_up_to_launch_the_rocket),
                        style = MaterialTheme.typography.titleSmall,
                    )
                }
            } else {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    FlyingRocket(
                        modifier = Modifier.offset(y = offsetAnimation).scale(scaleAnimation),
                    )
                    Text(
                        modifier = Modifier.padding(top = 16.dp),
                        text = stringResource(R.string.launch_successful),
                        style = MaterialTheme.typography.titleSmall,
                    )
                }
            }
        }
    }
}

@Composable
fun FlyingRocket(
    modifier: Modifier = Modifier,
) {
    Image(
        modifier = modifier,
        painter = painterResource(id = R.drawable.ic_rocket_flying),
        contentDescription = "Idle rocket",
    )
}

@Preview
@Composable
fun LaunchPreview() {
    LaunchContent(LaunchScreenState(), "Falcon 10", { })
}
