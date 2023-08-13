package com.branislavbily.rocket.features.rocketDetail.domain.api

import com.branislavbily.rocket.features.rocketDetail.data.api.RocketDetailAPI
import com.branislavbily.rocket.features.rocketDetail.domain.RocketDetail
import com.branislavbily.rocket.features.rocketDetail.domain.RocketParameters
import com.branislavbily.rocket.features.rocketDetail.domain.Stage

interface RocketDetailAPIConverterUseCase {
    /**
     * Converts RocketDetailAPI into model RocketDetail
     *
     * @param rocketDetailAPI Data class to be converted
     * @return Converted model
     */
    fun toModel(rocketDetailAPI: RocketDetailAPI): RocketDetail
}

class RocketDetailAPIConverterUseCaseImpl : RocketDetailAPIConverterUseCase {

    override fun toModel(rocketDetailAPI: RocketDetailAPI): RocketDetail {
        return RocketDetail(
            id = rocketDetailAPI.id,
            rocketName = rocketDetailAPI.rocketName,
            overview = rocketDetailAPI.overview,
            parameters = RocketParameters(
                height = rocketDetailAPI.height.meters,
                diameter = rocketDetailAPI.diameter.meters,
                mass = rocketDetailAPI.mass.kg,
            ),
            stages = listOf(
                Stage(
                    title = "First stage",
                    reusable = rocketDetailAPI.firstStage.reusable,
                    engines = rocketDetailAPI.firstStage.engines,
                    fuel = rocketDetailAPI.firstStage.fuel,
                    burnTime = rocketDetailAPI.firstStage.burnTime,
                ),
                Stage(
                    title = "Second stage",
                    reusable = rocketDetailAPI.secondStage.reusable,
                    engines = rocketDetailAPI.secondStage.engines,
                    fuel = rocketDetailAPI.secondStage.fuel,
                    burnTime = rocketDetailAPI.secondStage.burnTime,
                ),
            ),
            photos = rocketDetailAPI.imageLinks,
        )
    }
}
