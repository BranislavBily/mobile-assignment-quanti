package com.branislavbily.rocket.features.rocketDetail.presentation

import androidx.compose.foundation.clickable
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.branislavbily.rocket.R
import com.branislavbily.rocket.core.presentation.GoBackIconWithTitle
import com.branislavbily.rocket.features.Screens

@Composable
fun RocketDetail(navController: NavController) {
    RocketDetailContent(
        onBackPressed = { navController.popBackStack() },
        onLaunchPressed = { navController.navigate(Screens.Launch.route) },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RocketDetailContent(
    onBackPressed: () -> Unit,
    onLaunchPressed: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    GoBackIconWithTitle(
                        onIconPressed = onBackPressed,
                        title = stringResource(id = R.string.Rockets),
                    )
                    Text(text = stringResource(id = R.string.RocketDetail))
                    Text(
                        modifier = Modifier.clickable {
                            onLaunchPressed()
                        },
                        text = stringResource(id = R.string.Launch),
                    )
                }
            })
        },
    ) { padding ->
        Column(
            modifier = Modifier.fillMaxSize()
                .padding(padding),
        ) {
        }
    }
}

@Preview
@Composable
fun RocketDetailPreview() {
    RocketDetailContent({}, {})
}
