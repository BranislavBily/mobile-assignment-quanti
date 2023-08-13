package com.branislavbily.rocket.features.rocketDetail.domain

data class RocketDetail(
    val id: Int = 0,
    val rocketName: String = "",
    val overview: String = "",
    val parameters: RocketParameters = RocketParameters(),
    val stages: List<Stage> = listOf(),
    val photos: List<String> = listOf(),
)

data class RocketParameters(
    val height: Double = .0,
    val diameter: Double = .0,
    val mass: Int = 0,
)

data class Stage(
    val title: String = "",
    val reusable: Boolean = false,
    val engines: Int = 0,
    val fuel: Double = .0,
    val burnTime: Int? = 0,
)
