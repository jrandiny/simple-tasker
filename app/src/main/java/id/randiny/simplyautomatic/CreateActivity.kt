package id.randiny.simplyautomatic

import android.app.Activity
import android.content.Intent
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

    private val GET_CONDITION_REQUEST_CODE = 0
    private val GET_ACTION_REQUEST_CODE = 1

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

        routineViewModel.getAction().observe(this, Observer {
            actionButton.text = it.name

        })

        routineViewModel.validConfig.observe(this, Observer {
            createButton.isEnabled = it
        })

        conditionButton.setOnClickListener {
            val intent = Intent(this, PickerActivity::class.java)
            intent.putExtra(PickerActivity.MODULE_IS_ACTION_EXTRA, false)
            startActivityForResult(intent, GET_CONDITION_REQUEST_CODE)

        }

        actionButton.setOnClickListener {
            val intent = Intent(this, PickerActivity::class.java)
            intent.putExtra(PickerActivity.MODULE_IS_ACTION_EXTRA, true)
            startActivityForResult(intent, GET_ACTION_REQUEST_CODE)
        }

        nameEditText.addTextChangedListener {
            routineViewModel.setName(it.toString())
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)


        if (requestCode == GET_CONDITION_REQUEST_CODE || requestCode == GET_ACTION_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                if (data != null) {
                    val type = data.getSerializableExtra(PickerActivity.RETURN_PICKED_MODULE_EXTRA)
                    val parameter =
                        data.getSerializableExtra(PickerActivity.RETURN_PICKED_MODULE_CONFIG)
                    val description =
                        data.getStringExtra(PickerActivity.RETURN_PICKED_MODULE_DESCRIPTION)

                    if (type != null && parameter != null && description != null) {
                        val moduleType = type as ModuleType
                        val moduleParam = parameter as HashMap<String, String>

                        if (requestCode == GET_CONDITION_REQUEST_CODE) {
                            val condition = ModuleConfig(description, moduleType, moduleParam)
                            routineViewModel.setCondition(condition)
                        } else {
                            val action = ModuleConfig(description, moduleType, moduleParam)
                            routineViewModel.setAction(action)
                        }
                    }
                }
            }
        }
    }

}
