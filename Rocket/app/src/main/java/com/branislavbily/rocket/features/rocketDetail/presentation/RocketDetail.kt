package com.branislavbily.rocket.features.rocketDetail.presentation

import androidx.annotation.DrawableRes
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.branislavbily.rocket.R
import com.branislavbily.rocket.core.presentation.GoBackIconWithTitle
import com.branislavbily.rocket.features.Screens
import com.branislavbily.rocket.features.rocketDetail.domain.RocketDetail
import com.branislavbily.rocket.features.rocketDetail.domain.RocketParameters
import com.branislavbily.rocket.features.rocketDetail.domain.Stage
import com.branislavbily.rocket.ui.theme.TextOnBlackBackground
import com.branislavbily.rocket.ui.theme.Typography

@Composable
fun RocketDetail(
    navController: NavController,
    viewModel: RocketDetailViewModel,
) {
    val state by viewModel.viewState.collectAsState()
    val lifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(lifecycleOwner.lifecycle.currentState) {
        viewModel.getRocketDetail()
    }
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
            if (state.isLoading) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center,
                ) {
                    CircularProgressIndicator()
                }
            } else {
                RocketDetailView(
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp),
                    rocket = state.rocketDetail,
                )
            }
        }
    }
}

@Composable
fun RocketDetailView(
    modifier: Modifier = Modifier,
    rocket: RocketDetail,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
    ) {
        RocketOverview(
            modifier = Modifier.padding(
                top = 8.dp,
                bottom = 8.dp,
            ),
            overview = rocket.overview,
        )
        RocketParametersView(
            modifier = Modifier.padding(top = 8.dp),
            parameters = rocket.parameters,
        )
        RocketStages(
            modifier = Modifier.padding(top = 16.dp),
            stages = rocket.stages,
        )
        RocketPhotos(rocket.photos)
    }
}

@Composable
fun RocketOverview(
    modifier: Modifier = Modifier,
    overview: String,
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
    ) {
        Text(
            modifier = Modifier
                .padding(bottom = 8.dp),
            text = stringResource(id = R.string.overview),
            fontWeight = FontWeight.Black,
        )
        Text(
            modifier = Modifier
                .padding(),
            text = overview,
        )
    }
}

@Composable
fun RocketParametersView(
    modifier: Modifier = Modifier,
    parameters: RocketParameters,
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
    ) {
        Text(
            text = stringResource(id = R.string.parameters),
            fontWeight = FontWeight.Bold,
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
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
    modifier: Modifier = Modifier,
    value: String,
    unit: String,
    name: String,
) {
    Card(
        modifier = modifier
            .size(100.dp),
        shape = RoundedCornerShape(16.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Magenta),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = value + unit,
                style = Typography.bodyLarge,
                color = TextOnBlackBackground,
            )
            Text(
                text = name,
                style = Typography.bodyLarge,
            )
        }
    }
}

@Composable
fun RocketStages(
    modifier: Modifier = Modifier,
    stages: List<Stage>,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
    ) {
        stages.forEach { stage ->
            RocketStage(
                modifier = Modifier.padding(top = 8.dp, bottom = 8.dp),
                stage = stage,
            )
        }
    }
}

@Composable
fun RocketStage(
    modifier: Modifier = Modifier,
    stage: Stage,
) {
    Card(
        modifier = modifier.fillMaxWidth(),
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
        ) {
            Text(
                text = stage.title,
                style = Typography.bodyLarge,
            )
            RocketStageRow(
                icon = R.drawable.ic_reusable,
                text = stringResource(if (stage.reusable) R.string.reusable else R.string.not_reusable),
            )
            RocketStageRow(
                icon = R.drawable.ic_engine,
                text = pluralStringResource(
                    id = R.plurals.engine,
                    stage.engines,
                    stage.engines,
                ),
            )
            RocketStageRow(
                icon = R.drawable.ic_fuel,
                text = pluralStringResource(
                    id = R.plurals.ton_of_fuel,
                    stage.fuel.toInt(),
                    stage.fuel.toInt(),
                ),
            )
            RocketStageRow(
                icon = R.drawable.ic_burn,
                text = pluralStringResource(
                    id = R.plurals.second_burn_time,
                    stage.burnTime ?: 0,
                    stage.burnTime ?: 0,
                ),
            )
        }
    }
}

@Composable
fun RocketStageRow(
    modifier: Modifier = Modifier,
    @DrawableRes icon: Int,
    text: String,
) {
    Row(
        modifier = modifier.fillMaxWidth().padding(top = 8.dp, bottom = 8.dp),
        horizontalArrangement = Arrangement.Start,
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = "RocketStageRowIcon",
        )
        Text(
            modifier = Modifier.padding(start = 8.dp),
            text = text,
        )
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
    RocketParametersView(parameters = RocketParameters())
}
