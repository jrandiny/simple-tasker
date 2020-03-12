package id.randiny.simplyautomatic

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import id.randiny.simplyautomatic.module.ModuleType

class ConfiguratorActivity : AppCompatActivity() {

    companion object {
        val MODULE_TO_CONFIGURE_EXTRA = "module_configure"
        val RETURN_CONFIGURED_MODULE_PARAM = "configured_module"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.configurator_activity)
        setSupportActionBar(findViewById(R.id.toolbar))

        val moduleType = intent.getSerializableExtra(MODULE_TO_CONFIGURE_EXTRA) as ModuleType

        findViewById<MaterialButton>(R.id.config_btn).setOnClickListener {
            val intent = Intent()

            val out: HashMap<String, String> = hashMapOf()
            out.put("A", "A")
            out.put("B", "B")

            intent.putExtra(RETURN_CONFIGURED_MODULE_PARAM, out)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }

}
