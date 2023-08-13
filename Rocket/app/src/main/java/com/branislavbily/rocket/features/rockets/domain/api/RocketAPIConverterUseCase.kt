package com.branislavbily.rocket.features.rockets.domain.api

import com.branislavbily.rocket.core.domain.DateFormatter
import com.branislavbily.rocket.features.rockets.data.api.RocketAPI
import com.branislavbily.rocket.features.rockets.domain.Rocket

interface RocketAPIConverterUseCase {

    /**
     * Converts RocketAPI into model Rocket
     *
     * @param rocketAPI Data class to be converted
     * @return Converted model
     */
    fun toModel(rocketAPI: RocketAPI): Rocket
}

class RocketAPIConverterUseCaseImpl(
    private val dateFormatter: DateFormatter,
) : RocketAPIConverterUseCase {
    override fun toModel(rocketAPI: RocketAPI): Rocket = Rocket(
        id = rocketAPI.id,
        name = rocketAPI.name,
        firstFlight = dateFormatter.format(rocketAPI.firstFlight),
        rocketID = rocketAPI.rocketID,
    )
}
