package com.branislavbily.rocket.features.rockets.data.api

import com.branislavbily.rocket.features.rockets.data.RocketsRemoteDataSource
import io.reactivex.Single

class RocketsRetrofitDataSource(
    private val rocketsApiDescription: RocketsApiDescription,
) : RocketsRemoteDataSource {
    override fun getRockets(): Single<List<RocketAPI>> = rocketsApiDescription.getRockets()
}
