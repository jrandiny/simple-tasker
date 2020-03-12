package id.randiny.simplyautomatic

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * A simple [Fragment] subclass.
 * Use the [ExternalAPIModule.newInstance] factory method to
 * create an instance of this fragment.
 */
class ExternalAPIModule : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.module_external_api, container, false)
    }
}
