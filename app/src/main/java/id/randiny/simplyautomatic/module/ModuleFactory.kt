package id.randiny.simplyautomatic.module

import android.content.Context
import androidx.fragment.app.Fragment
import id.randiny.simplyautomatic.data.Routine
import id.randiny.simplyautomatic.module.notify.NotifyModule
import id.randiny.simplyautomatic.module.sensor.SensorModule
import id.randiny.simplyautomatic.module.sonoff.SonoffModule
import id.randiny.simplyautomatic.module.time.TimeModule
import id.randiny.simplyautomatic.module.wifi.WifiModule

object ModuleFactory {
    fun createModule(routine: Routine, condition: Boolean, context: Context): Module {
        val param = if (condition) routine.conditionParam else routine.actionParam
        return when (if (condition) routine.conditionType else routine.actionType) {
            ModuleType.NOTIFY -> NotifyModule(routine.id, param, context)
            ModuleType.TIME -> TimeModule(routine.id, param, context)
            ModuleType.SENSOR -> SensorModule(routine.id, param, context)
            ModuleType.WIFI -> WifiModule(routine.id, param, context)
            ModuleType.SONOFF -> SonoffModule(routine.id, param, context)
        }
    }

    fun createConfigurator(type: ModuleType): Fragment {
        when (type) {
            ModuleType.TIME -> TODO()
            ModuleType.NOTIFY -> TODO()
            ModuleType.SENSOR -> TODO()
            ModuleType.WIFI -> TODO()
            ModuleType.SONOFF -> TODO()
        }
    }
}