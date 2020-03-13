package id.randiny.simplyautomatic.module

import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData

abstract class ConfiguratorFragment : Fragment() {
    val validConfig: MutableLiveData<Boolean> = MutableLiveData(false)

    abstract fun getParam(): HashMap<String, String>
}