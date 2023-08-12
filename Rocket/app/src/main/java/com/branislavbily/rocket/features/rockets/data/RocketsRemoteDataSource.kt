package com.branislavbily.rocket.features.rockets.data

import com.branislavbily.rocket.features.rockets.data.api.RocketAPI
import io.reactivex.Single

interface RocketsRemoteDataSource {
    fun getRockets(): Single<List<RocketAPI>>
}
