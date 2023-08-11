package com.branislavbily.rocket.features.rocketDetail.domain.api

import com.branislavbily.rocket.features.rocketDetail.data.api.RocketDetailAPI
import com.branislavbily.rocket.features.rocketDetail.domain.RocketDetail
import com.branislavbily.rocket.features.rocketDetail.domain.RocketParameters
import com.branislavbily.rocket.features.rocketDetail.domain.RocketStage

interface RocketDetailAPIConverterUseCase {
    fun toModel(rocketDetailAPI: RocketDetailAPI): RocketDetail
}

class RocketDetailAPIConverterUseCaseImpl : RocketDetailAPIConverterUseCase {
    override fun toModel(rocketDetailAPI: RocketDetailAPI): RocketDetail = RocketDetail(
        id = rocketDetailAPI.id,
        overview = rocketDetailAPI.overview,
        parameters = RocketParameters(
            height = rocketDetailAPI.height.meters,
            diameter = rocketDetailAPI.diameter.meters,
            mass = rocketDetailAPI.mass.kg,
        ),
        states = listOf(
            RocketStage(
                reusable = rocketDetailAPI.firstStage.reusable,
                engines = rocketDetailAPI.firstStage.engines,
                fuel = rocketDetailAPI.firstStage.fuel,
                burnTime = rocketDetailAPI.firstStage.burnTime,
            ),
            RocketStage(
                reusable = rocketDetailAPI.secondStage.reusable,
                engines = rocketDetailAPI.secondStage.engines,
                fuel = rocketDetailAPI.secondStage.fuel,
                burnTime = rocketDetailAPI.secondStage.burnTime,
            ),
        ),
    )
}
