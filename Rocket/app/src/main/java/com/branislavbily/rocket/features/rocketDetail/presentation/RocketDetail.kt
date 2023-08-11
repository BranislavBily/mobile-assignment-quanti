package com.branislavbily.rocket.features.rocketDetail.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.branislavbily.rocket.R
import com.branislavbily.rocket.core.presentation.GoBackIconWithTitle
import com.branislavbily.rocket.features.Screens
import com.branislavbily.rocket.features.rocketDetail.domain.RocketDetail
import com.branislavbily.rocket.features.rocketDetail.domain.RocketParameters
import com.branislavbily.rocket.features.rocketDetail.domain.RocketStage

@Composable
fun RocketDetail(
    navController: NavController,
    viewModel: RocketDetailViewModel,
) {
    val state by viewModel.viewState.collectAsState()
    RocketDetailContent(
        state = state,
        onBackPressed = { navController.popBackStack() },
        onLaunchPressed = { navController.navigate(Screens.Launch.route) },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RocketDetailContent(
    state: RocketDetailScreenState,
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
                    Text(text = state.rocketDetail.rocketName)
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
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
        ) {
            RocketDetailView(rocket = state.rocketDetail)
        }
    }
}

@Composable
fun RocketDetailView(
    rocket: RocketDetail,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
    ) {
        RocketOverview(rocket.overview)
        RocketParametersView(rocket.parameters)
        RocketStages(rocket.stages)
        RocketPhotos(rocket.photos)
    }
}

@Composable
fun RocketOverview(overview: String) {
    Column {
        Text(text = stringResource(id = R.string.overview))
        Text(text = overview)
    }
}

@Composable
fun RocketParametersView(parameters: RocketParameters) {
    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Text(text = stringResource(id = R.string.parameters))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            RocketParameter(
                value = parameters.height.toString(),
                unit = "m",
                name = stringResource(id = R.string.height),
            )
            RocketParameter(
                value = parameters.diameter.toString(),
                unit = "m",
                name = stringResource(id = R.string.diameter),
            )
            RocketParameter(
                value = parameters.mass.toString(),
                unit = "t",
                name = stringResource(id = R.string.mass),
            )
        }
    }
}

@Composable
fun RocketParameter(
    value: String,
    unit: String,
    name: String,
) {
    Box(
        modifier = Modifier
            .size(100.dp)
            .background(Color.Magenta)
            .clip(RoundedCornerShape(16.dp))
            .padding(16.dp),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(text = value + unit)
            Text(text = name)
        }
    }
}

@Composable
fun RocketStages(stages: List<RocketStage>) {
    stages.forEach { stage ->
        RocketStage(stage)
    }
}

@Composable
fun RocketStage(stage: RocketStage) {
    Card {
        Column {
            Text(text = stage.reusable.toString())
            Text(text = stage.engines.toString())
            Text(text = stage.fuel.toString())
            Text(text = stage.burnTime.toString())
        }
    }
}

@Composable
fun RocketPhotos(photos: List<String>) {
    Text(text = stringResource(id = R.string.photos))
    photos.forEach { photo ->
        AsyncImage(
            model = photo,
            contentDescription = "Image of Rocket",
        )
    }
}

@Preview
@Composable
fun RocketDetailPreview() {
    RocketDetailContent(RocketDetailScreenState(), {}, {})
}

@Preview
@Composable
fun RocketDetailViewPreview() {
    RocketDetailView(rocket = RocketDetail())
}

@Preview
@Composable
fun RocketParametersPreview() {
    RocketParametersView(RocketParameters())
}
