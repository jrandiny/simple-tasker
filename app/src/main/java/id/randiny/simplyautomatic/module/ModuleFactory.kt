package id.randiny.simplyautomatic.module

import android.content.Context
import id.randiny.simplyautomatic.data.Routine
import id.randiny.simplyautomatic.module.notify.NotifyModule
import id.randiny.simplyautomatic.module.time.TimeModule

object ModuleFactory {
    fun createModule(routine: Routine, condition: Boolean, context: Context) {
        val type = if (condition) routine.conditionType else routine.actionType
        when (type) {
            ModuleType.NOTIFY -> NotifyModule(routine.id, context)
            ModuleType.TIME -> TimeModule(routine.id, context)
        }
    }

    fun createConfigurator(type: ModuleType) {
        when (type) {
            ModuleType.TIME -> TODO()
            ModuleType.NOTIFY -> TODO()
        }
    }
}