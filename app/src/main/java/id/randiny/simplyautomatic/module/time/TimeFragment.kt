package id.randiny.simplyautomatic.module.time

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.TimePicker
import ca.antonious.materialdaypicker.MaterialDayPicker
import ca.antonious.materialdaypicker.SingleSelectionMode
import id.randiny.simplyautomatic.R
import id.randiny.simplyautomatic.module.ConfiguratorFragment
import java.util.*


class TimeFragment : ConfiguratorFragment(), RadioGroup.OnCheckedChangeListener {

    private var isOneShot: Boolean = true

    private lateinit var dayPicker: MaterialDayPicker
    private lateinit var datePicker: DatePicker
    private lateinit var timePicker: TimePicker

    private lateinit var dayTextView: TextView
    private lateinit var dateTextView: TextView
    private lateinit var timeTextView: TextView

    private val calendar = Calendar.getInstance()

    companion object {
        private const val LOG_TAG = "My/TimeFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(LOG_TAG, "Init Time configurator fragment")
        val root = inflater.inflate(R.layout.fragment_time, container, false)
        val radioGroup = root.findViewById<RadioGroup>(R.id.time_radio_group)
        radioGroup.check(R.id.oneshot_option)

        dayPicker = root.findViewById(R.id.day_picker)
        datePicker = root.findViewById(R.id.date_picker)
        timePicker = root.findViewById(R.id.time_picker)

        dayTextView = root.findViewById(R.id.day_textview)
        dateTextView = root.findViewById(R.id.date_textview)
        timeTextView = root.findViewById(R.id.time_textview)

        dayPicker.selectionMode = SingleSelectionMode.create()
        dayPicker.selectDay(MaterialDayPicker.Weekday.MONDAY)
        datePicker.minDate = System.currentTimeMillis() - 1000;
        timePicker.setIs24HourView(true)

        radioGroup.setOnCheckedChangeListener(this)

        validConfig.postValue(true)

        return root
    }

    override fun getParam(): HashMap<String, String> {
        return hashMapOf(
            TimeModule.PARAM_ALARM_TYPE to if (isOneShot) "1" else "0",
            TimeModule.PARAM_DAYS to getDayString(),
            TimeModule.PARAM_DATE to getDateString(),
            TimeModule.PARAM_TIME to getTimeString()
        )
    }

    override fun getDescription(): String {
        if (isOneShot) {
            return getString(
                R.string.module_time_description_oneshot,
                getDateString(),
                getTimeString()
            )
        } else {
            if (getDayString() == "none") {
                return getString(R.string.module_time_description_repeating, getTimeString())
            } else {
                return getString(
                    R.string.module_time_description_repeating_day,
                    getDayString(),
                    getTimeString()
                )
            }
        }
    }

    override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
        Log.d(LOG_TAG, getTimeString())
        if (checkedId == R.id.oneshot_option) {
            dayTextView.visibility = View.GONE
            dayPicker.visibility = View.GONE
            dateTextView.visibility = View.VISIBLE
            datePicker.visibility = View.VISIBLE
            isOneShot = true
        } else if (checkedId == R.id.repeating_option) {
            dayTextView.visibility = View.VISIBLE
            dayPicker.visibility = View.VISIBLE
            dateTextView.visibility = View.GONE
            datePicker.visibility = View.GONE
            isOneShot = false
        }
    }

    private fun getDateString(): String {
        return "${datePicker.dayOfMonth}-${datePicker.month + 1}-${datePicker.year}"
    }

    private fun getDayString(): String {
        if (dayPicker.selectedDays.size > 0) {
            return dayPicker.selectedDays[0].toString()
        } else {
            return "none"
        }
    }

    private fun getTimeString(): String {
        return "${timePicker.hour}:${timePicker.minute}"
    }
}
