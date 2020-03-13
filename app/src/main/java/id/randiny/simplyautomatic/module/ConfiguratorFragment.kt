package id.randiny.simplyautomatic.module

import androidx.fragment.app.Fragment

abstract class ConfiguratorFragment : Fragment() {
    abstract fun getParam(): HashMap<String, String>
}