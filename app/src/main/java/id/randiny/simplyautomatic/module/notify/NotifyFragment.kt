package id.randiny.simplyautomatic.module.notify

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.randiny.simplyautomatic.R

/**
 * A simple [Fragment] subclass.
 * Use the [NotifyModule.newInstance] factory method to
 * create an instance of this fragment.
 */
class NotifyFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_notify, container, false)
    }
}
