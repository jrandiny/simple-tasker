package id.randiny.simplyautomatic.data.routine

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Routine(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val active: Boolean,
    val conditionId: Int,
    val actionId: Int
)