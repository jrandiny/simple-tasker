package id.randiny.simplyautomatic.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import id.randiny.simplyautomatic.module.ModuleType

@Entity
data class Routine(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val active: Boolean,
    val conditionType: ModuleType,
    val conditionParam: Map<String, Any>,
    val actionType: ModuleType,
    val actionParam: Map<String, Any>
)