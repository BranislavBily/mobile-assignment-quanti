package com.branislavbily.rocket.features.launch.presentation

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class LaunchViewModel(
    private val context: Context,
) : ViewModel() {

    private lateinit var sensorManager: SensorManager
    private lateinit var gyroscop: Sensor

    private val gyroscopSensorListener: SensorEventListener = GyroscopSensorListener(::fireRocket)

    private val _viewState: MutableStateFlow<LaunchScreenState> =
        MutableStateFlow(LaunchScreenState())
    val viewState: StateFlow<LaunchScreenState> = _viewState

    fun listenToRotationChange() {
        sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        gyroscop = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)

        sensorManager.registerListener(
            gyroscopSensorListener,
            gyroscop,
            SensorManager.SENSOR_DELAY_FASTEST,
        )
    }

    private fun fireRocket() {
        Log.i("LaunchViewModel", "Fireaway")
        _viewState.update { it.copy(fireRocket = true) }
    }

    override fun onCleared() {
        sensorManager.unregisterListener(gyroscopSensorListener)
        super.onCleared()
    }
}

data class LaunchScreenState(
    val fireRocket: Boolean = false,
)

class GyroscopSensorListener(
    private val zRotationChanged: () -> Unit,
) : SensorEventListener {
    override fun onSensorChanged(event: SensorEvent?) {
        event?.let {
            // The second value in FloatArray is Z axis
            if (it.values[2] > 1) {
                Log.i("GyropscopSensorListener", "Fireaway")
                zRotationChanged()
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    }
}
