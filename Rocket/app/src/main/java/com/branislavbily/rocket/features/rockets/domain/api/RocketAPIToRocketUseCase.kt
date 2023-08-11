package com.branislavbily.rocket.features.rockets.domain.api

import com.branislavbily.rocket.features.rockets.data.api.RocketAPI
import com.branislavbily.rocket.features.rockets.domain.Rocket

interface RocketAPIConverterUseCase {
    fun toModel(rocketAPI: RocketAPI): Rocket
}

class RocketAPIConverterUseCaseImpl : RocketAPIConverterUseCase {
    override fun toModel(rocketAPI: RocketAPI): Rocket = Rocket(
        id = rocketAPI.id,
        name = rocketAPI.name,
        firstFlight = rocketAPI.firstFlight,
    )
}
