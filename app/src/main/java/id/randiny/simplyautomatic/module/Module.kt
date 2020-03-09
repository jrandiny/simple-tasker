package id.randiny.simplyautomatic.module

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel

interface Module {

    val name: String
    val type: ModuleType
    val configId: Int

    fun init(configId: Int)

    fun getConfigurator(vm: ConfiguratorViewModel): Fragment

    fun poll(): Boolean
    fun action()
    fun setupListener(action: Module)
    fun destroyListener()
}