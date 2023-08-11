package com.branislavbily.rocket.features.rockets.data.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RocketAPI(
    val id: Int,
    @SerialName("rocket_name")
    val name: String,
    @SerialName("first_flight")
    val firstFlight: String,
)
