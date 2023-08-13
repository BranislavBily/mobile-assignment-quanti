package com.branislavbily.rocket.features.rocketDetail.data.api

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface RocketDetailApiDescription {

    @GET("rockets/{rocketId}")
    fun getRocketDetail(@Path("rocketId") rocketId: String): Single<RocketDetailAPI>
}
