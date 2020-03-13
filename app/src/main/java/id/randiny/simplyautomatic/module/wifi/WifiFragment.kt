package id.randiny.simplyautomatic.module.wifi

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import id.randiny.simplyautomatic.R
import id.randiny.simplyautomatic.module.ConfiguratorFragment

class WifiFragment : ConfiguratorFragment(), RadioGroup.OnCheckedChangeListener {

    private var wifiStatus: Boolean = true

    companion object {
        private const val LOG_TAG = "My/WifiFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(LOG_TAG, "Init wifi configurator fragment")

        val root = inflater.inflate(R.layout.fragment_wifi, container, false)
        val radioGroup = root.findViewById<RadioGroup>(R.id.wifi_radio_group)
        radioGroup.setOnCheckedChangeListener(this)
        radioGroup.check(R.id.wifi_radio_on)

        validConfig.postValue(true)

        return root
    }

    override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
        if (checkedId == R.id.wifi_radio_on) {
            wifiStatus = true
        } else if (checkedId == R.id.wifi_radio_off) {
            wifiStatus = false
        }
    }

    override fun getParam(): HashMap<String, String> {
        return hashMapOf(
            WifiModule.PARAM_TOGGLE to if (wifiStatus) "1" else "0"
        )
    }
}
