package id.randiny.simplyautomatic.module

import androidx.fragment.app.Fragment

interface Module {

    val name: String
    val behaviourType: ModuleBehaviourType
    val configId: Int

    fun init(configId: Int)

    fun getConfigurator(vm: ConfiguratorViewModel): Fragment

    fun poll(): Boolean
    fun action()
    fun setupListener(action: Module)
    fun destroyListener()
}