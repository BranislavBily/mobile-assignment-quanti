package com.branislavbily.rocket

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.branislavbily.rocket.features.AppNavigation
import com.branislavbily.rocket.features.rockets.presentation.Rockets
import com.branislavbily.rocket.ui.theme.RocketTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RocketTheme {
                AppNavigation()
            }
        }
    }
}
