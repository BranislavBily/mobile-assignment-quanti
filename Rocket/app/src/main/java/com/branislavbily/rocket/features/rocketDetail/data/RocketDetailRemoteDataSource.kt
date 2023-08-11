package com.branislavbily.rocket.features.rocketDetail.data

import com.branislavbily.rocket.features.rocketDetail.data.api.RocketDetailAPI
import io.reactivex.Single

interface RocketDetailRemoteDataSource {
    fun getRocket(id: String): Single<RocketDetailAPI>
}