package id.randiny.simplyautomatic.module

enum class ModuleType {
    TIME,
    NOTIFY,
    SENSOR,
    WIFI;

    companion object {
        fun valueOf(value: Int) = values().find { it.ordinal == value }
        fun getName(value: ModuleType): String {
            return when (value) {
                TIME -> "Time"
                NOTIFY -> "Notification"
                SENSOR -> "Sensor"
                WIFI -> "Wifi"
            }
        }

        fun getBehaviourType(value: ModuleType): ModuleBehaviourType {
            return when (value) {
                TIME -> ModuleBehaviourType.TRIGGER
                NOTIFY -> ModuleBehaviourType.ACTION
                SENSOR -> ModuleBehaviourType.TRIGGER
                WIFI -> ModuleBehaviourType.ACTION
            }
        }

        fun getDescription(value: ModuleType): String {
            return when (value) {
                TIME -> "Set time for oneshot blablabla"
                NOTIFY -> "SHow notification blbblblb"
                SENSOR -> "Use your amazing sensor so waw"
                WIFI -> "Toggle your wifi"
            }
        }
    }
}