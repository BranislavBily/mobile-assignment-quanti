package com.branislavbily.rocket.features.rocketDetail.domain

import java.net.URL

data class RocketDetail(
    val id: Int = 0,
    val overview: String = "No overview here",
    val parameters: RocketParameters = RocketParameters(),
    val states: List<RocketStage> = listOf(),
    val photos: List<URL> = listOf(),
)

data class RocketParameters(
    val height: Double = .0,
    val diameter: Double = .0,
    val mass: Int = 0,
)

data class RocketStage(
    val reusable: Boolean = false,
    val engines: Int = 0,
    val fuel: Int = 0,
    val burnTime: Int? = 0,
)
