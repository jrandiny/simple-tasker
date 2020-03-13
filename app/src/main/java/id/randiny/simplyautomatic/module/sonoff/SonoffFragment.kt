package id.randiny.simplyautomatic.module.sonoff

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.RadioGroup
import androidx.core.widget.addTextChangedListener
import id.randiny.simplyautomatic.R
import id.randiny.simplyautomatic.module.ConfiguratorFragment

class SonoffFragment : ConfiguratorFragment(), RadioGroup.OnCheckedChangeListener {

    private var sonoffStatus: Boolean = true
    private lateinit var username: EditText
    private lateinit var password: EditText
    private lateinit var ipAddress: EditText

    companion object {
        private const val LOG_TAG = "My/SonoffFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(LOG_TAG, "Init Sonoff configurator fragment")
        val root = inflater.inflate(R.layout.fragment_sonoff, container, false)
        username = root.findViewById(R.id.username)
        password = root.findViewById(R.id.password)
        ipAddress = root.findViewById(R.id.ipAddress)
        username.addTextChangedListener {
            update()
        }
        password.addTextChangedListener {
            update()
        }
        ipAddress.addTextChangedListener {
            update()
        }
        val radioGroup = root.findViewById<RadioGroup>(R.id.sonoff_radio_group)
        radioGroup.setOnCheckedChangeListener(this)
        radioGroup.check(R.id.toggleRadioOn)

        validConfig.postValue(true)

        return root
    }

    override fun getParam(): HashMap<String, String> {
        return hashMapOf(
            SonoffModule.PARAM_TOGGLE to if (sonoffStatus) "1" else "0",
            SonoffModule.PARAM_USERNAME to username.text.toString(),
            SonoffModule.PARAM_PASSWORD to password.text.toString(),
            SonoffModule.PARAM_IPADDRESS to ipAddress.text.toString()
        )
    }

    override fun getDescription(): String {
        return getString(
            R.string.module_sonoff_description,
            if (sonoffStatus) "on" else "off",
            ipAddress.text.toString()
        )
    }

    override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
        if (checkedId == R.id.toggleRadioOn) {
            sonoffStatus = true
        } else if (checkedId == R.id.toggleRadioOff) {
            sonoffStatus = false
        }
    }

    private fun update() {
        validConfig.postValue(
            username.text.toString().isNotBlank() &&
                    password.text.toString().isNotBlank() &&
                    ipAddress.text.toString().isNotBlank()
        )
    }
}
