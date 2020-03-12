package id.randiny.simplyautomatic

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.randiny.simplyautomatic.module.ModuleBehaviourType
import id.randiny.simplyautomatic.module.ModuleType
import id.randiny.simplyautomatic.ui.ModuleListAdapter

class PickerActivity : AppCompatActivity() {

    companion object {
        val MODULE_IS_ACTION_EXTRA = "is_action"
        val PICKED_MODULE_EXTRA = "picked"
    }

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

        Log.d("My/list", module.toString())

        val recycler = findViewById<RecyclerView>(R.id.module_list)

        val lm = LinearLayoutManager(this)
        val adapter = ModuleListAdapter(module) {
            val intent = Intent()
            intent.putExtra(PICKED_MODULE_EXTRA, it)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
        recycler.layoutManager = lm
        recycler.adapter = adapter
        recycler.addItemDecoration(DividerItemDecoration(this, lm.orientation))

    }


}
