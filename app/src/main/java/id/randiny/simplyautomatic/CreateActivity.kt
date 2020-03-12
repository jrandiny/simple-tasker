package id.randiny.simplyautomatic

import android.os.Bundle
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
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
    private lateinit var nameEditText: EditText

    private val routineViewModel: RoutineViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_activity)
        setSupportActionBar(findViewById(R.id.toolbar))

        createButton = findViewById(R.id.create_btn)
        conditionButton = findViewById(R.id.condition_btn)
        actionButton = findViewById(R.id.action_btn)
        nameEditText = findViewById(R.id.routine_name)

        routineListViewModel =
            ViewModelProvider(
                this,
                RoutineListViewModelFactory(this)
            ).get(RoutineListViewModel::class.java)


        createButton.isEnabled = false

        createButton.setOnClickListener {
            val trigger = routineViewModel.getCondition().value
            val action = routineViewModel.getAction().value
            val name = routineViewModel.getName().value
            routineListViewModel.addItem(name!!, trigger!!, action!!)
            finish()
        }

        routineViewModel.getCondition().observe(this, Observer {
            conditionButton.text = it.name
        })

        conditionButton.setOnClickListener {
            val condition = ModuleConfig("nama kondisi", ModuleType.API, mapOf())
            routineViewModel.setCondition(condition)
        }

        routineViewModel.getAction().observe(this, Observer {
            actionButton.text = it.name

        })

        actionButton.setOnClickListener {
            val action = ModuleConfig("name aksi", ModuleType.TIME, mapOf())
            routineViewModel.setAction(action)
        }

        routineViewModel.validConfig.observe(this, Observer {
            createButton.isEnabled = it
        })

        nameEditText.addTextChangedListener {
            routineViewModel.setName(it.toString())
        }
    }

}
