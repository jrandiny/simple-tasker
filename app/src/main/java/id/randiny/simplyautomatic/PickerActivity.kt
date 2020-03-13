package id.randiny.simplyautomatic

import android.app.Activity
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.randiny.simplyautomatic.module.ModuleBehaviourType
import id.randiny.simplyautomatic.module.ModuleType
import id.randiny.simplyautomatic.ui.ModuleListAdapter

class PickerActivity : AppCompatActivity() {

    companion object {
        const val MODULE_IS_ACTION_EXTRA = "is_action"
        const val RETURN_PICKED_MODULE_EXTRA = "picked"
        const val RETURN_PICKED_MODULE_CONFIG = "picked_config"
        const val RETURN_PICKED_MODULE_DESCRIPTION = "picked_description"


        const val GET_CONFIGURATION_REQUEST_CODE = 1
    }

    private var selectedModule: ModuleType? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.picker_activity)
        setSupportActionBar(findViewById(R.id.toolbar))

        val action = intent.getBooleanExtra(MODULE_IS_ACTION_EXTRA, true)

        val module = ModuleType.values().filter { type ->
            if (action) {
                ModuleType.getBehaviourType(type) == ModuleBehaviourType.ACTION
            } else {
                ModuleType.getBehaviourType(type) != ModuleBehaviourType.ACTION
            }
        }

        val recycler = findViewById<RecyclerView>(R.id.module_list)

        val orientation = resources.configuration.orientation

        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            val lm = GridLayoutManager(this, 2)
            recycler.addItemDecoration(
                DividerItemDecoration(
                    this,
                    DividerItemDecoration.HORIZONTAL
                )
            )
            recycler.addItemDecoration(
                DividerItemDecoration(
                    this,
                    DividerItemDecoration.VERTICAL
                )
            )
            recycler.layoutManager = lm
        } else {
            val lm = LinearLayoutManager(this)
            recycler.addItemDecoration(DividerItemDecoration(this, lm.orientation))
            recycler.layoutManager = lm
        }


        val adapter = ModuleListAdapter(module) {
            selectedModule = it
            val intent = Intent(this, ConfiguratorActivity::class.java)
            intent.putExtra(ConfiguratorActivity.MODULE_TO_CONFIGURE_EXTRA, it)
            startActivityForResult(intent, GET_CONFIGURATION_REQUEST_CODE)


        }
        recycler.adapter = adapter

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == GET_CONFIGURATION_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                data?.let {
                    val parameter =
                        it.getSerializableExtra(ConfiguratorActivity.RETURN_CONFIGURED_MODULE_PARAM)
                    val description =
                        it.getStringExtra(ConfiguratorActivity.RETURN_CONFIGURED_DESCRIPTION_PARAM)

                    val intent = Intent()
                    intent.putExtra(RETURN_PICKED_MODULE_EXTRA, selectedModule)
                    intent.putExtra(RETURN_PICKED_MODULE_CONFIG, parameter)
                    intent.putExtra(RETURN_PICKED_MODULE_DESCRIPTION, description)

                    setResult(Activity.RESULT_OK, intent)
                    finish()
                }
            }
        }
    }

}
