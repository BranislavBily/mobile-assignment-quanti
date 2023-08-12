package com.branislavbily.rocket.features

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.IntOffset
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.branislavbily.rocket.features.launch.presentation.Launch
import com.branislavbily.rocket.features.rocketDetail.presentation.RocketDetail
import com.branislavbily.rocket.features.rocketDetail.presentation.RocketDetailViewModel
import com.branislavbily.rocket.features.rockets.presentation.Rockets
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import org.koin.androidx.compose.koinViewModel

val springSpec = spring<IntOffset>(dampingRatio = Spring.DampingRatioNoBouncy)

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppNavigation() {
    val navController = rememberAnimatedNavController()
    AnimatedNavHost(
        navController = navController,
        startDestination = Screens.Rockets.route,
    ) {
        composable(
            route = Screens.Rockets.route,
            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { 1000 },
                    animationSpec = springSpec,
                )
            },
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { -1000 },
                    animationSpec = springSpec,
                )
            },
            popEnterTransition = {
                slideInHorizontally(
                    initialOffsetX = { -1000 },
                    animationSpec = springSpec,
                )
            },
            popExitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { 1000 },
                    animationSpec = springSpec,
                )
            },
        ) {
            Rockets(
                navController = navController,
                viewModel = koinViewModel(),
            )
        }
        val rocketIdArgument = "rocketId"
        composable(
            route = Screens.RocketDetail.route + "/{$rocketIdArgument}",
            arguments = listOf(
                navArgument(rocketIdArgument) {
                    type = NavType.StringType
                },
            ),
            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { 1000 },
                    animationSpec = springSpec,
                )
            },
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { -1000 },
                    animationSpec = springSpec,
                )
            },
            popEnterTransition = {
                slideInHorizontally(
                    initialOffsetX = { -1000 },
                    animationSpec = springSpec,
                )
            },
            popExitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { 1000 },
                    animationSpec = springSpec,
                )
            },
        ) { entry ->
            RocketDetail(
                navController,
                viewModel = koinViewModel<RocketDetailViewModel>().apply {
                    setRocketId(
                        entry.arguments?.getString(rocketIdArgument),
                    )
                },
            )
        }
        composable(
            route = Screens.Launch.route,
            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { 1000 },
                    animationSpec = springSpec,
                )
            },
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { -1000 },
                    animationSpec = springSpec,
                )
            },
            popEnterTransition = {
                slideInHorizontally(
                    initialOffsetX = { -1000 },
                    animationSpec = springSpec,
                )
            },
            popExitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { 1000 },
                    animationSpec = springSpec,
                )
            },
        ) {
            Launch(navController)
        }
    }
}

sealed class Screens(val route: String) {
    object Rockets : Screens("rockets")
    object RocketDetail : Screens("rocketDetail")
    object Launch : Screens("launch")
}
