package id.randiny.simplyautomatic

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import id.randiny.simplyautomatic.module.ConfiguratorFragment
import id.randiny.simplyautomatic.module.ModuleFactory
import id.randiny.simplyautomatic.module.ModuleType

class ConfiguratorActivity : AppCompatActivity() {

    companion object {
        const val MODULE_TO_CONFIGURE_EXTRA = "module_configure"
        const val RETURN_CONFIGURED_MODULE_PARAM = "configured_module"
    }

    private lateinit var configuratorFragment: ConfiguratorFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.configurator_activity)
        setSupportActionBar(findViewById(R.id.toolbar))

        val moduleType = intent.getSerializableExtra(MODULE_TO_CONFIGURE_EXTRA) as ModuleType

        configuratorFragment = ModuleFactory.createConfigurator(moduleType)

        val transaction = supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, configuratorFragment)
            addToBackStack(null)
        }

        transaction.commit()

        findViewById<MaterialButton>(R.id.config_btn).setOnClickListener {
            val intent = Intent()

            intent.putExtra(RETURN_CONFIGURED_MODULE_PARAM, configuratorFragment.getParam())
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }

}
