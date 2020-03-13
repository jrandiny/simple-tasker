package id.randiny.simplyautomatic.module

import android.content.Context
import id.randiny.simplyautomatic.data.Routine
import id.randiny.simplyautomatic.module.notify.NotifyFragment
import id.randiny.simplyautomatic.module.notify.NotifyModule
import id.randiny.simplyautomatic.module.sensor.SensorFragment
import id.randiny.simplyautomatic.module.sensor.SensorModule
import id.randiny.simplyautomatic.module.sonoff.SonoffModule
import id.randiny.simplyautomatic.module.time.TimeModule
import id.randiny.simplyautomatic.module.wifi.WifiFragment
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

    fun createConfigurator(type: ModuleType): ConfiguratorFragment {
        return when (type) {
            ModuleType.TIME -> WifiFragment()
            ModuleType.NOTIFY -> NotifyFragment()
            ModuleType.SENSOR -> SensorFragment()
            ModuleType.WIFI -> WifiFragment()
            ModuleType.SONOFF -> WifiFragment()
        }
    }
}