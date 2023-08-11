package com.branislavbily.rocket.features.rocketDetail.data.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RocketDetailAPI(
    val id: Int,
    @SerialName("description")
    val overview: String,
    val height: HeightAPI,
    val diameter: DiameterAPI,
    val mass: MassAPI,

    // TODO Here I'm making assumption that there are always two stages. Not good
    @SerialName("first_stage")
    val firstStage: StageAPI,
    @SerialName("second_stage")
    val secondStage: StageAPI,

    @SerialName("flickr_images")
    val imageLinks: List<String>,
    @SerialName("rocket_name")
    val rocketName: String
)

@Serializable
data class HeightAPI(
    val meters: Double,
    val feet: Double,
)

@Serializable
data class DiameterAPI(
    val meters: Double,
    val feet: Double,
)

@Serializable
data class MassAPI(
    val kg: Int,
    val lb: Int,
)

@Serializable
data class StageAPI(
    val reusable: Boolean,
    val engines: Int,
    @SerialName("fuel_amount_tons")
    val fuel: Int,
    @SerialName("burn_time_sec")
    val burnTime: Int?,
)
