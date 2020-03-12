package id.randiny.simplyautomatic.module.time

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import id.randiny.simplyautomatic.R
import id.randiny.simplyautomatic.R.id.radioButton5

/**
 * A simple [Fragment] subclass.
 * Use the [TimeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TimeFragment : Fragment(), RadioGroup.OnCheckedChangeListener {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_time, container, false)
    }

    override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
        val checkedRadioButton = group?.findViewById<RadioButton>(group.checkedRadioButtonId)
        if (checkedRadioButton != null) {
            if(checkedRadioButton.id == R.id.radioButton5) {
                val text1 = view!!.findViewById<TextView>(R.id.textView1)
                text1.visibility = View.VISIBLE
                val dayPicker = view!!.findViewById<TextView>(R.id.day_picker)
                dayPicker.visibility = View.VISIBLE
                val text = view!!.findViewById<TextView>(R.id.textView)
                text1.visibility = View.GONE
                val datePicker = view!!.findViewById<TextView>(R.id.datePicker)
                datePicker.visibility = View.GONE
            }
            else if(checkedRadioButton.id == R.id.radioButton6) {
                val text1 = view!!.findViewById<TextView>(R.id.textView1)
                text1.visibility = View.GONE
                val dayPicker = view!!.findViewById<TextView>(R.id.day_picker)
                dayPicker.visibility = View.GONE
                val text = view!!.findViewById<TextView>(R.id.textView)
                text1.visibility = View.VISIBLE
                val datePicker = view!!.findViewById<TextView>(R.id.datePicker)
                datePicker.visibility = View.VISIBLE
            }
        }
    }
}
