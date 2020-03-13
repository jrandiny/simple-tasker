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

        const val PARAM_SENSOR_TYPE = "sensor_type"
        const val PARAM_SENSOR_THRESHOLD = "sensor_threshold"
        const val PARAM_SENSOR_THRESHOLD_OPERATOR = "sensor_operator"

        const val PARAM_ENUM_THRESHOLD_LT = "LT"
        const val PARAM_ENUM_THRESHOLD_GT = "GT"
    }

    private lateinit var sensorManager: SensorManager
    private lateinit var thresholdCheck: (event: SensorEvent) -> Boolean
    private lateinit var valueExtractor: (event: SensorEvent) -> Float
    private lateinit var action: Module

    override fun setupListener(action: Module) {
        Log.d(LOG_TAG, "Registering sensor listener with param $param")

        this.action = action

        val threshold = param.get(PARAM_SENSOR_THRESHOLD)?.toInt() ?: 0

        val sensorType = SensorUtil.SensorType.valueOf(param.get(PARAM_SENSOR_TYPE)!!)

        valueExtractor = SensorUtil.getValueExtractor(sensorType)

        if (param.get(PARAM_SENSOR_THRESHOLD_OPERATOR) == PARAM_ENUM_THRESHOLD_GT) {
            thresholdCheck = fun(event): Boolean {
                return valueExtractor(event) > threshold
            }
        } else {
            thresholdCheck = fun(event): Boolean {
                return valueExtractor(event) < threshold
            }
        }

        sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager

        sensorManager.getDefaultSensor(SensorUtil.getSensorType(sensorType))?.let {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    override fun destroyListener() {
        sensorManager.unregisterListener(this)
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
    }

    override fun onSensorChanged(event: SensorEvent) {
        if (thresholdCheck(event)) {
            action.action()
        }
    }
}