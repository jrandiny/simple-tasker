package id.randiny.simplyautomatic.module.sensor

import android.hardware.Sensor
import android.hardware.SensorEvent

object SensorUtil {
    enum class SensorType {
        ACCELEROMETER_Z,
        ACCELEROMETER_Y,
        ACCELEROMETER_X,
        MAGNET;
    }


    fun getSensorType(enum: SensorType): Int {
        return when (enum) {
            SensorType.ACCELEROMETER_Z -> Sensor.TYPE_ACCELEROMETER
            SensorType.ACCELEROMETER_Y -> Sensor.TYPE_ACCELEROMETER
            SensorType.ACCELEROMETER_X -> Sensor.TYPE_ACCELEROMETER
            SensorType.MAGNET -> Sensor.TYPE_MAGNETIC_FIELD
        }
    }

    fun getValueExtractor(enum: SensorType): (event: SensorEvent) -> Float {
        return when (enum) {
            SensorType.ACCELEROMETER_Z -> fun(event: SensorEvent): Float {
                return event.values[0]
            }
            SensorType.ACCELEROMETER_Y -> fun(event: SensorEvent): Float {
                return event.values[1]
            }
            SensorType.ACCELEROMETER_X -> fun(event: SensorEvent): Float {
                return event.values[2]
            }
            SensorType.MAGNET -> fun(event: SensorEvent): Float {
                return event.values[0]
            }
        }
    }

}