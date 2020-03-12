package id.randiny.simplyautomatic.module.time

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import id.randiny.simplyautomatic.R

/**
 * A simple [Fragment] subclass.
 * Use the [TimeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TimeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_time, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
            if(view is RadioButton) {
                val checked = view.isChecked
                when(view.getId()){
                    R.id.radioButton5 ->
                        if(checked) {
                            when(view.getId()){
                                R.id.textView1 ->
                                    view.visibility = View.VISIBLE
                            }
                            when(view.getId()){
                                R.id.day_picker ->
                                    view.visibility = View.VISIBLE
                            }
                            when(view.getId()){
                                R.id.textView ->
                                    view.visibility = View.GONE
                            }
                            when(view.getId()){
                                R.id.datePicker ->
                                    view.visibility = View.GONE
                            }
                        }
                    R.id.radioButton6 ->
                        if(checked) {
                            when(view.getId()){
                                R.id.textView1 ->
                                    view.visibility = View.GONE
                            }
                            when(view.getId()){
                                R.id.day_picker ->
                                    view.visibility = View.GONE
                            }
                            when(view.getId()){
                                R.id.textView ->
                                    view.visibility = View.VISIBLE
                            }
                            when(view.getId()){
                                R.id.datePicker ->
                                    view.visibility = View.VISIBLE
                            }
                        }
                }
            }
    }
}
