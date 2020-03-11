package id.randiny.simplyautomatic.data

import id.randiny.simplyautomatic.module.ModuleType

data class ModuleConfig(
    val type: ModuleType,
    val param: Map<String, Any>
)