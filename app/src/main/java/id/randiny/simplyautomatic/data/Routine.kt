package id.randiny.simplyautomatic.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import id.randiny.simplyautomatic.module.ModuleType

@Entity
data class Routine(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val active: Boolean,
    val name: String,
    val conditionName: String,
    val conditionType: ModuleType,
    val conditionParam: Map<String, String>,
    val actionName: String,
    val actionType: ModuleType,
    val actionParam: Map<String, String>
)