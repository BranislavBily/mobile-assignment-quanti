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
    private lateinit var accelometer: Sensor

    private val gyroscopSensorListener: SensorEventListener = GyroscopSensorListener(::fireRocket)
    private val accelerometerChangeListener: SensorEventListener =
        AccelerometerChangeListener(::fireRocket)

    private val _viewState: MutableStateFlow<LaunchScreenState> =
        MutableStateFlow(LaunchScreenState())
    val viewState: StateFlow<LaunchScreenState> = _viewState

    fun listenToRotationChange() {
        sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        gyroscop = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)
        accelometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        sensorManager.registerListener(
            gyroscopSensorListener,
            gyroscop,
            SensorManager.SENSOR_DELAY_FASTEST,
        )

        sensorManager.registerListener(
            accelerometerChangeListener,
            accelometer,
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
            if (it.values[2] > 2 || it.values[2] < -2) {
                Log.i("GyropscopSensorListener", "Fireaway")
//                zRotationChanged()
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
}

class AccelerometerChangeListener(
    private val yRotationChanged: () -> Unit,
) : SensorEventListener {

    private var firstValue: Float? = null

    override fun onSensorChanged(event: SensorEvent?) {
        event?.let {
            if (firstValue == null) {
                firstValue = it.values[1]
            } else {
                firstValue?.let { value ->
                    if (it.values[1] < value - 2) {
                        Log.i("AccelerometerSensorListener", "Fireaway")
                        yRotationChanged()
                    }
                }
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
}
