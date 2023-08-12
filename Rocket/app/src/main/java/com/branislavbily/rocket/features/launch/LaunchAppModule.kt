package com.branislavbily.rocket.features.launch

import com.branislavbily.rocket.features.launch.presentation.LaunchViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val launchAppModule = module {
    viewModel {
        LaunchViewModel(
            context = androidContext(),
        )
    }
}
