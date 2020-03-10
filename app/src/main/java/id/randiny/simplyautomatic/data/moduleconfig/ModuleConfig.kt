package id.randiny.simplyautomatic.data.moduleconfig

import androidx.room.Entity
import androidx.room.PrimaryKey
import id.randiny.simplyautomatic.module.ModuleType

@Entity()
data class ModuleConfig(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val type: ModuleType,
    val param: Map<String, Any>
)