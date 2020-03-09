package id.randiny.simplyautomatic.module

data class ModuleConfig(
    val name: String,
    val type: ModuleType,
    val param: Map<String, Any>
)