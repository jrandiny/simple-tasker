package id.randiny.simplyautomatic.module.time

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import ca.antonious.materialdaypicker.MaterialDayPicker
import id.randiny.simplyautomatic.R
import id.randiny.simplyautomatic.module.ConfiguratorFragment
import java.text.DateFormat
import java.util.*
import kotlin.collections.HashMap


class TimeFragment : ConfiguratorFragment(), RadioGroup.OnCheckedChangeListener {

    private var timeStatus: Boolean = true
    private lateinit var days: MaterialDayPicker
    private lateinit var date: DatePicker
    private lateinit var time: TimePicker
    private lateinit var calendar: Calendar

    companion object {
        private const val LOG_TAG = "My/TimeFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(LOG_TAG, "Init Time configurator fragment")
        val root = inflater.inflate(R.layout.fragment_time, container, false)
        val radioGroup = root.findViewById<RadioGroup>(R.id.radio_group)
        val days = root.findViewById<MaterialDayPicker>(R.id.day_picker)
        val date = root.findViewById<DatePicker>(R.id.datePicker)
        val time = root.findViewById<TimePicker>(R.id.alarmTimePicker)
        // Selected Days
        val selectedDays = days.selectedDays
        // Selected Date
        calendar = Calendar.getInstance()
        val selectedDate = formatDate(date.year,date.month,date.dayOfMonth)
        // Selected Time
        val selectedTime = getPickerTime(time)
        radioGroup.setOnCheckedChangeListener(this)

        validConfig.postValue(true)

        return root
    }

    override fun getParam(): HashMap<String, String> {
        return hashMapOf(
            TimeModule.PARAM_TIMESTATUS to if (timeStatus) "1" else "0",
            TimeModule.PARAM_DAYS to days,
            TimeModule.PARAM_DATE to date,
            TimeModule.PARAM_TIME to time
        )
    }

    override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
        val checkedRadioButton = group?.findViewById<RadioButton>(group.checkedRadioButtonId)
        if (checkedRadioButton != null) {
            if(checkedRadioButton.id == R.id.oneshot) {
                val text1 = view!!.findViewById<TextView>(R.id.textView1)
                text1.visibility = View.VISIBLE
                val dayPicker = view!!.findViewById<TextView>(R.id.day_picker)
                dayPicker.visibility = View.VISIBLE
                val text = view!!.findViewById<TextView>(R.id.textView)
                text1.visibility = View.GONE
                val datePicker = view!!.findViewById<TextView>(R.id.datePicker)
                datePicker.visibility = View.GONE
                timeStatus = true
            }
            else if(checkedRadioButton.id == R.id.repeating) {
                val text1 = view!!.findViewById<TextView>(R.id.textView1)
                text1.visibility = View.GONE
                val dayPicker = view!!.findViewById<TextView>(R.id.day_picker)
                dayPicker.visibility = View.GONE
                val text = view!!.findViewById<TextView>(R.id.textView)
                text.visibility = View.VISIBLE
                val datePicker = view!!.findViewById<TextView>(R.id.datePicker)
                datePicker.visibility = View.VISIBLE
                timeStatus = false
            }
        }
    }

    // Custom method to format date
    private fun formatDate(year:Int, month:Int, day:Int):String{
        // Create a Date variable/object with user chosen date
        calendar.set(year, month, day, 0, 0, 0)
        val chosenDate = calendar.time

        // Format the date picker selected date
        val df = DateFormat.getDateInstance(DateFormat.MEDIUM)
        return df.format(chosenDate)
    }

    // Custom method to get time picker current time as string
    private fun getPickerTime(timePicker: TimePicker):String{
        val hour = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            timePicker.hour
        } else {
            timePicker.currentHour
        }

        val minute = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            timePicker.minute
        }else{
            timePicker.currentMinute
        }

        return "${getHourAMPM(hour)} : $minute ${getAMPM(hour)}"
    }


    // Custom method to get AM PM value from provided hour
    private fun getAMPM(hour:Int):String{
        return if(hour>11)"PM" else "AM"
    }


    // Custom method to get hour for AM PM time format
    private fun getHourAMPM(hour:Int):Int{
        // Return the hour value for AM PM time format
        var modifiedHour = if (hour>11)hour-12 else hour
        if (modifiedHour == 0){modifiedHour = 12}
        return modifiedHour
    }
}
