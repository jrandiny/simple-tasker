package id.randiny.simplyautomatic.data.routine

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Relation
import id.randiny.simplyautomatic.data.moduleconfig.ModuleConfig

@Entity
data class RoutineAndModule(
    @Embedded
    val routine: Routine,
    @Relation(
        parentColumn = "conditionId",
        entityColumn = "id"
    )
    val condition: ModuleConfig,
    @Relation(
        parentColumn = "actionId",
        entityColumn = "id"
    )
    val action: ModuleConfig
)