package com.branislavbily.rocket

import android.app.Application
import com.branislavbily.rocket.core.di.coreAppModule
import com.branislavbily.rocket.features.rockets.rocketsModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class RocketApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@RocketApplication)
            androidLogger()
            modules(coreAppModule, rocketsModule)
        }
    }
}
