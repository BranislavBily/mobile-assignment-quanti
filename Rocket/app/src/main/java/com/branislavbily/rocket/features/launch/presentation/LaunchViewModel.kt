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

    private val gyroscopeSensorListener: SensorEventListener = GyroscopeSensorListener(::fireRocket)
    private val accelerometerChangeListener: SensorEventListener =
        AccelerometerChangeListener(::fireRocket)

    private val _viewState: MutableStateFlow<LaunchScreenState> =
        MutableStateFlow(LaunchScreenState())
    val viewState: StateFlow<LaunchScreenState> = _viewState

    override fun onCleared() {
        sensorManager.unregisterListener(gyroscopeSensorListener)
        sensorManager.unregisterListener(accelerometerChangeListener)
        super.onCleared()
    }

    /**
     * Registers listeners for the gyroscope and accelerometer sensors
     */
    fun listenToRotationChange() {
        sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val gyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)
        val accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        sensorManager.registerListener(
            gyroscopeSensorListener,
            gyroscope,
            SensorManager.SENSOR_DELAY_NORMAL,
        )

        sensorManager.registerListener(
            accelerometerChangeListener,
            accelerometer,
            SensorManager.SENSOR_DELAY_NORMAL,
        )
    }

    /**
     * Changes the screen state to fire rocket
     *
     */
    private fun fireRocket() {
        Log.i(TAG, "Fire away")
        _viewState.update { it.copy(fireRocket = true) }
    }

    companion object {
        private const val TAG = "LaunchViewModel"
    }
}

data class LaunchScreenState(
    val fireRocket: Boolean = false,
)

class GyroscopeSensorListener(
    private val zRotationChanged: () -> Unit,
) : SensorEventListener {

    private val sensitivity = 1

    // The second value in FloatArray is Z axis
    private val zAxisIndex = 2

    override fun onSensorChanged(event: SensorEvent?) {
        event?.let {
            Log.i(TAG, "Current value of z-rot: ${it.values[zAxisIndex]}")
            if (it.values[zAxisIndex] > sensitivity || it.values[zAxisIndex] < (-sensitivity)) {
                Log.i(TAG, "Fire away")
                zRotationChanged()
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}

    companion object {
        private const val TAG = "GyroscopeSensorListener"
    }
}

class AccelerometerChangeListener(
    private val yRotationChanged: () -> Unit,
) : SensorEventListener {

    private var firstValue: Float? = null
    private val sensitivity = 1

    override fun onSensorChanged(event: SensorEvent?) {
        event?.let {
            // Logging the first value, so that only the change fires the rocket
            if (firstValue == null) {
                firstValue = it.values[1]
            } else {
                firstValue?.let { value ->
                    Log.i(
                        TAG,
                        "First value: $firstValue current value of x-rot: ${it.values[1]}",
                    )
                    if (it.values[1] > (value + sensitivity) || it.values[1] < (value - sensitivity)) {
                        Log.i(TAG, "Fireaway")
                        yRotationChanged()
                    }
                }
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}

    companion object {
        private const val TAG = "AccelerometerSensorListener"
    }
}
