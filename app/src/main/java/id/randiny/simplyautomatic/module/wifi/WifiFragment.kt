package id.randiny.simplyautomatic.module.wifi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.randiny.simplyautomatic.R

/**
 * A simple [Fragment] subclass.
 * Use the [WifiFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class WifiFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_wifi, container, false)
    }
}
