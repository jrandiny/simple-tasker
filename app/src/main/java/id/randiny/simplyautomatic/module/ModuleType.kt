package id.randiny.simplyautomatic.module

enum class ModuleType {
    TIME,
    API;

    companion object {
        fun valueOf(value: Int) = ModuleType.values().find { it.ordinal == value }
    }
}