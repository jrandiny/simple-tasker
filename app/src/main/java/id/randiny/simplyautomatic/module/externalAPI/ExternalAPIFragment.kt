package id.randiny.simplyautomatic.module.externalAPI

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.randiny.simplyautomatic.R

/**
 * A simple [Fragment] subclass.
 * Use the [ExternalAPIModule.newInstance] factory method to
 * create an instance of this fragment.
 */
class ExternalAPIFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_external_api, container, false)
    }
}
