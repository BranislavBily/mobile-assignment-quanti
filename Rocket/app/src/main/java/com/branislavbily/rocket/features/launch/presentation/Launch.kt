package com.branislavbily.rocket.features.launch.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.branislavbily.rocket.R
import com.branislavbily.rocket.core.presentation.GoBackIconWithTitle

@Composable
fun Launch(
    navController: NavController,
    viewModel: LaunchViewModel,
) {
    val state by viewModel.viewState.collectAsState()
    val lifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(lifecycleOwner.lifecycle.currentState) {
        viewModel.listenToRotationChange()
    }

    LaunchContent(
        state = state,
        onBackPressed = { navController.popBackStack() },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LaunchContent(
    state: LaunchScreenState,
    onBackPressed: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    GoBackIconWithTitle(
                        onIconPressed = onBackPressed,
                        title = stringResource(id = R.string.RocketDetail),
                    )
                }
            })
        },
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
        ) {
            if (state.fireRocket) {
                Text(text = "Fire rocket")
            }
        }
    }
}

@Preview
@Composable
fun LaunchPreview() {
    LaunchContent(LaunchScreenState(), { })
}
