package id.randiny.simplyautomatic

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.button.MaterialButton
import id.randiny.simplyautomatic.data.ModuleConfig
import id.randiny.simplyautomatic.module.ModuleType
import id.randiny.simplyautomatic.viewmodel.RoutineListViewModel
import id.randiny.simplyautomatic.viewmodel.RoutineListViewModelFactory
import id.randiny.simplyautomatic.viewmodel.RoutineViewModel


class CreateActivity : AppCompatActivity() {

    private lateinit var routineListViewModel: RoutineListViewModel
    private lateinit var createButton: MaterialButton
    private lateinit var conditionButton: MaterialButton
    private lateinit var actionButton: MaterialButton

    private val routineViewModel: RoutineViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_activity)
        setSupportActionBar(findViewById(R.id.toolbar))

        createButton = findViewById(R.id.create_btn)
        conditionButton = findViewById(R.id.condition_btn)
        actionButton = findViewById(R.id.action_btn)

        routineListViewModel =
            ViewModelProvider(
                this,
                RoutineListViewModelFactory(this)
            ).get(RoutineListViewModel::class.java)


        createButton.isEnabled = false

        createButton.setOnClickListener {
            val trigger = routineViewModel.getAction().value
            val action = routineViewModel.getAction().value
            routineListViewModel.addItem(trigger!!, action!!)
            finish()
        }

        routineViewModel.getCondition().observe(this, Observer {
            if (it != null) {
                conditionButton.text = it.type.toString()
            }
        })

        conditionButton.setOnClickListener {
            val action = ModuleConfig(ModuleType.API, mapOf())
            routineViewModel.setCondition(action)
        }

        routineViewModel.getAction().observe(this, Observer {
            if (it != null) {
                actionButton.text = it.type.toString()
            }
        })

        actionButton.setOnClickListener {
            val action = ModuleConfig(ModuleType.TIME, mapOf())
            routineViewModel.setAction(action)
        }

        routineViewModel.validConfig.observe(this, Observer {
            if (it) {
                createButton.isEnabled = true
            }
        })
    }

}
