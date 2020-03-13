package id.randiny.simplyautomatic.module

import android.content.Context
import id.randiny.simplyautomatic.R

enum class ModuleType {
    TIME,
    NOTIFY,
    SENSOR,
    WIFI,
    SONOFF;

    companion object {
        fun valueOf(value: Int) = values().find { it.ordinal == value }
        fun getName(context: Context, value: ModuleType): String {
            return when (value) {
                TIME -> context.getText(R.string.module_time_picker_name)
                NOTIFY -> context.getText(R.string.module_notify_picker_name)
                SENSOR -> context.getText(R.string.module_sensor_picker_name)
                WIFI -> context.getText(R.string.module_wifi_picker_name)
                SONOFF -> context.getText(R.string.module_sonoff_picker_name)
            }.toString()
        }

        fun getBehaviourType(value: ModuleType): ModuleBehaviourType {
            return when (value) {
                TIME -> ModuleBehaviourType.TRIGGER
                NOTIFY -> ModuleBehaviourType.ACTION
                SENSOR -> ModuleBehaviourType.TRIGGER
                WIFI -> ModuleBehaviourType.ACTION
                SONOFF -> ModuleBehaviourType.ACTION
            }
        }

        fun getDescription(context: Context, value: ModuleType): String {
            return when (value) {
                TIME -> context.getText(R.string.module_time_picker_description)
                NOTIFY -> context.getText(R.string.module_notify_picker_description)
                SENSOR -> context.getText(R.string.module_sensor_picker_description)
                WIFI -> context.getText(R.string.module_wifi_picker_description)
                SONOFF -> context.getText(R.string.module_sonoff_picker_description)
            }.toString()
        }
    }
}