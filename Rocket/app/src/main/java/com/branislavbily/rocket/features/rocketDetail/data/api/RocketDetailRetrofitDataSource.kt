package com.branislavbily.rocket.features.rocketDetail.data.api

import com.branislavbily.rocket.features.rocketDetail.data.RocketDetailRemoteDataSource
import io.reactivex.Single

class RocketDetailRetrofitDataSource(
    private val rocketDetailApiDescription: RocketDetailApiDescription,
) : RocketDetailRemoteDataSource {
    override fun getRocketDetail(id: String): Single<RocketDetailAPI> =
        rocketDetailApiDescription.getRocketDetail(id)
}
