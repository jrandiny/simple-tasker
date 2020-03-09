package id.randiny.simplyautomatic.module

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel

interface Module {
    val config: ModuleConfig

    fun init(config: ModuleConfig)

    fun getConfigurator(vm: ConfiguratorViewModel): Fragment

    fun poll(): Boolean
    fun action()
    fun setupListener(action: Module)
    fun destroyListener()
}