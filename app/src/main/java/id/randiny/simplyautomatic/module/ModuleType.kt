package id.randiny.simplyautomatic.module

enum class ModuleType {
    TIME,
    NOTIFY;

    companion object {
        fun valueOf(value: Int) = values().find { it.ordinal == value }
        fun getName(value: ModuleType): String {
            when (value) {
                TIME -> return "Time"
                NOTIFY -> return "Notification"
            }
        }

        fun getBehaviourType(value: ModuleType): ModuleBehaviourType {
            when (value) {
                TIME -> return ModuleBehaviourType.TRIGGER
                NOTIFY -> return ModuleBehaviourType.ACTION
            }
        }

        fun getDescription(value: ModuleType): String {
            when (value) {
                TIME -> return "Set time for oneshot blablabla"
                NOTIFY -> return "SHow notification blbblblb"
            }
        }
    }
}