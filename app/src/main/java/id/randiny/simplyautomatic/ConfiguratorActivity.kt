package id.randiny.simplyautomatic

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.google.android.material.button.MaterialButton
import id.randiny.simplyautomatic.module.ConfiguratorFragment
import id.randiny.simplyautomatic.module.ModuleFactory
import id.randiny.simplyautomatic.module.ModuleType

class ConfiguratorActivity : AppCompatActivity() {

    companion object {
        const val MODULE_TO_CONFIGURE_EXTRA = "module_configure"
        const val RETURN_CONFIGURED_MODULE_PARAM = "configured_module"

        private const val FRAGMENT_TAG = "fragment_tag"
    }

    private lateinit var configuratorFragment: ConfiguratorFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.configurator_activity)
        setSupportActionBar(findViewById(R.id.toolbar))

        val moduleType = intent.getSerializableExtra(MODULE_TO_CONFIGURE_EXTRA) as ModuleType


        if (savedInstanceState == null) {
            configuratorFragment = ModuleFactory.createConfigurator(moduleType)
        } else {
            configuratorFragment =
                supportFragmentManager.findFragmentByTag(FRAGMENT_TAG) as ConfiguratorFragment
        }

        val transaction = supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, configuratorFragment, FRAGMENT_TAG)
        }
        transaction.commit()

        val confirmButton = findViewById<MaterialButton>(R.id.config_btn)

        configuratorFragment.validConfig.observe(this, Observer {
            confirmButton.isEnabled = it
        })

        confirmButton.setOnClickListener {
            val intent = Intent()

            intent.putExtra(RETURN_CONFIGURED_MODULE_PARAM, configuratorFragment.getParam())
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }

}
