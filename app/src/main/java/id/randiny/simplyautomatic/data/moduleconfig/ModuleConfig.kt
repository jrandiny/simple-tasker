package id.randiny.simplyautomatic.data.moduleconfig

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity()
data class ModuleConfig(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val param: Map<String, Any>
)