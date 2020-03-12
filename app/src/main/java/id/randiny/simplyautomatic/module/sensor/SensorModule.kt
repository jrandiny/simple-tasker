package id.randiny.simplyautomatic.module.sensor

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.util.Log
import id.randiny.simplyautomatic.module.Module

class SensorModule(
    identifier: Int,
    private val param: Map<String, String>,
    private val context: Context
) : Module(
    identifier, param, context
), SensorEventListener {

    companion object {
        private const val LOG_TAG = "My/SensorModule"
    }

    private lateinit var sensorManager: SensorManager
    private lateinit var thresholdCheck: (event: SensorEvent) -> Boolean
    private lateinit var action: Module

    override fun setupListener(action: Module) {
        Log.d(LOG_TAG, "Registering sensor listener with param $param")

        this.action = action

        thresholdCheck = fun(event): Boolean {
            return event.values[0] > 4
        }

        sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager

        sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)?.let {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    override fun destroyListener() {
        sensorManager.unregisterListener(this)
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
        TODO("Not yet implemented")
    }

    override fun onSensorChanged(event: SensorEvent) {
        if (thresholdCheck(event)) {
            Log.d(LOG_TAG, "Triggering action ${event.values[0]}")
            action.action()
        }
    }
}