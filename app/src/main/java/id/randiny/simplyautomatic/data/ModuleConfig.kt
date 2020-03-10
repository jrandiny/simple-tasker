package id.randiny.simplyautomatic.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import id.randiny.simplyautomatic.module.ModuleType

data class ModuleConfig(
    val type: ModuleType,
    val param: Map<String, Any>
)