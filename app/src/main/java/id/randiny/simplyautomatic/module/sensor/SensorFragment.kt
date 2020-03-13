package id.randiny.simplyautomatic.module.sensor

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.widget.addTextChangedListener
import id.randiny.simplyautomatic.R
import id.randiny.simplyautomatic.module.ConfiguratorFragment

class SensorFragment :
    ConfiguratorFragment(),
    AdapterView.OnItemSelectedListener,
    RadioGroup.OnCheckedChangeListener,
    SensorEventListener {

    companion object {
        private const val LOG_TAG = "My/SensorFragment"
    }

    private lateinit var spinner: Spinner
    private lateinit var editText: EditText
    private lateinit var sensorGroup: RadioGroup
    private lateinit var currentValue: TextView

    private lateinit var sensorManager: SensorManager


    private val spinnerContent: List<String> = listOf(
        SensorModule.PARAM_ENUM_THRESHOLD_LT,
        SensorModule.PARAM_ENUM_THRESHOLD_GT
    )
    private var spinnerSelected: String = spinnerContent[0]

    private val radioContent: List<SensorUtil.SensorType> = listOf(
        SensorUtil.SensorType.ACCELEROMETER_X,
        SensorUtil.SensorType.ACCELEROMETER_Y,
        SensorUtil.SensorType.ACCELEROMETER_Z,
        SensorUtil.SensorType.MAGNET
    )
    private var radioSelected: SensorUtil.SensorType = radioContent[0]

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_sensor, container, false)

        spinner = root.findViewById(R.id.operator_spinner)
        editText = root.findViewById(R.id.threshold_edit_text)
        sensorGroup = root.findViewById(R.id.sensor_type_group)
        currentValue = root.findViewById(R.id.sensor_current_value)

        editText.addTextChangedListener {
            update()
        }

        sensorGroup.setOnCheckedChangeListener(this)
        sensorGroup.check(R.id.radio_sensor_accelerometer_x)

        validConfig.postValue(false)

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        ArrayAdapter.createFromResource(
            spinner.context,
            R.array.operator_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
            spinner.setSelection(0)
            spinner.onItemSelectedListener = this
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    }

    override fun getParam(): HashMap<String, String> {
        return hashMapOf(
            SensorModule.PARAM_SENSOR_TYPE to radioSelected.toString(),
            SensorModule.PARAM_SENSOR_THRESHOLD_OPERATOR to spinnerSelected,
            SensorModule.PARAM_SENSOR_THRESHOLD to editText.text.toString()
        )
    }

    override fun getDescription(): String {
        val operatorSymbol =
            if (spinnerSelected == SensorModule.PARAM_ENUM_THRESHOLD_GT) ">" else "<"
        return getString(
            R.string.module_sensor_description,
            radioSelected.toString(),
            operatorSymbol,
            editText.text.toString()
        )
    }

    override fun onResume() {
        super.onResume()
        val sensorType = SensorUtil.getSensorType(radioSelected)

        sensorManager.getDefaultSensor(sensorType)?.let {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }


    private fun update() {
        validConfig.postValue(
            editText.text.toString().isNotBlank()
        )
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event != null) {
            val sensorValue = SensorUtil.getValueExtractor(radioSelected)(event)
            currentValue.text = getString(
                R.string.module_sensor_value_filled,
                sensorValue
            )
        } else {
            currentValue.text = getText(R.string.module_sensor_value)
        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {}

    override fun onCheckedChanged(radio: RadioGroup?, checkedId: Int) {
        radioSelected = when (checkedId) {
            R.id.radio_sensor_accelerometer_x -> SensorUtil.SensorType.ACCELEROMETER_X
            R.id.radio_sensor_accelerometer_y -> SensorUtil.SensorType.ACCELEROMETER_Y
            R.id.radio_sensor_accelerometer_z -> SensorUtil.SensorType.ACCELEROMETER_Z
            R.id.radio_sensor_magnet -> SensorUtil.SensorType.MAGNET
            else -> SensorUtil.SensorType.ACCELEROMETER_X
        }
        Log.d(LOG_TAG, "Selected radio id $radioSelected")
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, pos: Int, id: Long) {
        Log.d(LOG_TAG, "Selected spinner id $pos")
        spinnerSelected = spinnerContent[pos]
    }
}
